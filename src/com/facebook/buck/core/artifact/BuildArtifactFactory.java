/*
 * Copyright 2019-present Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.facebook.buck.core.artifact;

import com.facebook.buck.core.exceptions.HumanReadableException;
import com.facebook.buck.core.model.BuildTarget;
import com.facebook.buck.core.model.impl.BuildPaths;
import com.facebook.buck.core.rules.analysis.action.ActionAnalysisDataKey;
import com.facebook.buck.io.filesystem.ProjectFilesystem;
import com.google.common.base.Verify;
import com.google.devtools.build.lib.events.Location;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Factory for managing and creating the various {@link Artifact}s for a specific {@link
 * BuildTarget}. All of the artifacts created by this instance of the factory will be rooted at the
 * package path as generated by {@link BuildPaths}.
 */
public class BuildArtifactFactory {

  protected final BuildTarget target;
  private final Path basePath;
  private final Path genDir;

  private final Set<ArtifactImpl> declaredArtifacts;

  protected BuildArtifactFactory(BuildTarget target, ProjectFilesystem filesystem) {
    this.target = target;
    this.genDir = filesystem.getBuckPaths().getGenDir();
    this.basePath = BuildPaths.getBaseDir(target);
    this.declaredArtifacts = new HashSet<>();
  }

  private DeclaredArtifact declareArtifact(ArtifactImpl artifact) {
    if (!declaredArtifacts.add(artifact)) {
      // Grab the existing element, and its location if it has one, to make it easier to
      // figure out where a file was declared.
      Optional<String> existingDeclaredLocation =
          declaredArtifacts.stream()
              .filter(artifact::equals)
              .findFirst()
              .map(ArtifactImpl::getDeclaredLocation)
              .filter(a -> !a.equals(Location.BUILTIN))
              .map(Location::print);

      if (existingDeclaredLocation.isPresent()) {
        throw new HumanReadableException(
            "Artifact with output %s cannot be declared again in %s. Originally declared at %s",
            artifact.getOutputPath(), target, existingDeclaredLocation.get());
      } else {
        throw new HumanReadableException(
            "Artifact with output %s cannot be declared again in %s",
            artifact.getOutputPath(), target);
      }
    }
    return artifact;
  }

  /**
   * @param output the output {@link Path} relative to the package path for the current rule that
   *     the {@link com.facebook.buck.core.rules.actions.Action}s are being created for
   * @param location the location within the extension file where this artifact was declared. {@link
   *     Location.BUILTIN} may be used if not applicable.
   * @return a {@link DeclaredArtifact} for the given path
   * @throws ArtifactDeclarationException if the provided output path is invalid in some way
   */
  protected DeclaredArtifact createDeclaredArtifact(Path output, Location location)
      throws ArtifactDeclarationException {
    return declareArtifact(ArtifactImpl.of(target, genDir, basePath, output, location));
  }

  /**
   * Simple wrapper that also validates that a raw string is indeed a valid path
   *
   * @param output the output path relative to the package path for the current rule that the {@link
   *     com.facebook.buck.core.rules.actions.Action}s are being created for
   * @param location the location within the extension file where this artifact was declared. {@link
   *     Location.BUILTIN} may be used if not applicable.
   * @return a {@link DeclaredArtifact} for the given path
   * @throws ArtifactDeclarationException if the provided output path is invalid in some way
   */
  protected DeclaredArtifact createDeclaredArtifact(String output, Location location)
      throws ArtifactDeclarationException {
    return declareArtifact(ArtifactImpl.of(target, genDir, basePath, output, location));
  }

  /**
   * @param key the {@link ActionAnalysisDataKey} corresponding to an {@link
   *     com.facebook.buck.core.rules.actions.Action} to bind the given {@link Artifact} to.
   * @param artifact the {@link Artifact} to bind
   * @return a {@link BuildArtifact} by binding the action to the given artifact
   */
  protected BuildArtifact bindtoBuildArtifact(ActionAnalysisDataKey key, Artifact artifact) {
    Verify.verify(declaredArtifacts.contains(artifact), "%s was never declared", artifact);
    BuildArtifact buildArtifact = artifact.asDeclared().materialize(key);
    return buildArtifact;
  }

  /**
   * Validates that all {@link DeclaredArtifact}s have been bound to an {@link
   * ActionAnalysisDataKey}
   */
  protected void verifyAllArtifactsBound() {
    for (ArtifactImpl artifact : declaredArtifacts) {
      if (!artifact.isBound()) {
        if (artifact.getDeclaredLocation().equals(Location.BUILTIN)) {
          throw new HumanReadableException(
              "Artifact %s declared by %s is not bound to an action",
              artifact.getOutputPath(), artifact.getBuildTarget().getFullyQualifiedName());
        } else {
          throw new HumanReadableException(
              "Artifact %s declared by %s is not bound to an action. Originally declared at %s",
              artifact.getOutputPath(),
              artifact.getBuildTarget().getFullyQualifiedName(),
              artifact.getDeclaredLocation().print());
        }
      }
    }
  }
}
