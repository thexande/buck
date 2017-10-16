/*
 * Copyright 2017-present Facebook, Inc.
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

package com.facebook.buck.distributed;

import com.facebook.buck.distributed.thrift.BuildStatus;

/** Util class for BuildStatus. */
public class BuildStatusUtil {
  private BuildStatusUtil() {
    // not to be instantiated.
  }

  public static BuildStatus exitCodeToBuildStatus(int exitCode) {
    return exitCode == 0 ? BuildStatus.FINISHED_SUCCESSFULLY : BuildStatus.FAILED;
  }

  public static boolean isBuildComplete(BuildStatus buildStatus) {
    return buildStatus == BuildStatus.FINISHED_SUCCESSFULLY || buildStatus == BuildStatus.FAILED;
  }
}
