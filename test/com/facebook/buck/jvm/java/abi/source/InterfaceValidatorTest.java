/*
 * Copyright 2016-present Facebook, Inc.
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

package com.facebook.buck.jvm.java.abi.source;

import com.facebook.buck.jvm.java.testutil.compiler.CompilerTreeApiTest;
import com.facebook.buck.jvm.java.testutil.compiler.CompilerTreeApiTestRunner;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.sun.source.tree.CompilationUnitTree;
import java.io.IOException;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CompilerTreeApiTestRunner.class)
public class InterfaceValidatorTest extends CompilerTreeApiTest {
  private ValidatingTaskListenerFactory taskListenerFactory;

  @Before
  public void setUp() {
    taskListenerFactory = new ValidatingTaskListenerFactory("//:rule");
  }

  @Test
  public void testSimpleClassPasses() throws IOException {
    compileWithValidation("public class Foo { }");

    assertNoErrors();
  }

  @Test
  public void testImplicitAnnotationSuperclassSucceeds() throws IOException {
    taskListenerFactory.setRuleIsRequiredForSourceAbi(true);
    compileWithValidation("@interface Foo { };");

    assertNoErrors();
  }

  @Test
  public void testAnnotationInNonRequiredRuleFails() throws IOException {
    testCompiler.setAllowCompilationErrors(true);
    compileWithValidation(ImmutableMap.of("Foo.java", "@interface Foo { @interface Inner { } };"));

    assertErrors(
        "Foo.java:1: error: Annotation definitions must be in rules with required_for_source_only_abi = True.\n"
            + "@interface Foo { @interface Inner { } };\n"
            + " ^\n"
            + "  For a quick fix, add required_for_source_only_abi = True to //:rule.\n"
            + "  A better fix is to move Foo to a new rule that contains only\n"
            + "  annotations, and mark that rule required_for_source_only_abi.",
        "Foo.java:1: error: Annotation definitions must be in rules with required_for_source_only_abi = True.\n"
            + "@interface Foo { @interface Inner { } };\n"
            + "                  ^\n"
            + "  For a quick fix, add required_for_source_only_abi = True to //:rule.\n"
            + "  A better fix is to move Inner to a new rule that contains only\n"
            + "  annotations, and mark that rule required_for_source_only_abi.");
  }

  @Test
  public void testMissingGrandSuperFailsButMissingSuperDoesNot() throws IOException {
    withClasspath(
        ImmutableMap.of(
            "com/facebook/bar/Bar.java",
            Joiner.on('\n')
                .join(
                    "package com.facebook.bar;",
                    "import com.facebook.baz.Baz;",
                    "import com.facebook.iface2.Interface2;",
                    "public class Bar extends Baz implements Interface2 { }"),
            "com/facebook/baz/Baz.java",
            Joiner.on('\n').join("package com.facebook.baz;", "public class Baz { }"),
            "com/facebook/iface/Interface.java",
            Joiner.on('\n').join("package com.facebook.iface;", "public interface Interface { }"),
            "com/facebook/iface2/Interface2.java",
            Joiner.on('\n')
                .join("package com.facebook.iface2;", "public interface Interface2 { }")));
    taskListenerFactory.addTargetAvailableForSourceOnlyAbi("//com/facebook/bar:bar");
    testCompiler.setAllowCompilationErrors(true);
    compileWithValidation(
        ImmutableMap.of(
            "com/facebook/foo/Foo.java",
            Joiner.on('\n')
                .join(
                    "package com.facebook.foo;",
                    "import com.facebook.bar.Bar;",
                    "import com.facebook.iface.Interface;",
                    "public class Foo extends Bar implements Interface { }")));

    assertError(
        "Foo.java:4: error: Source-only ABI generation requires that this type be unavailable, or that all of its superclasses/interfaces be available.\n"
            + "public class Foo extends Bar implements Interface { }\n"
            + "                         ^\n"
            + "  To fix, add the following rules to source_only_abi_deps: //com/facebook/baz:baz, //com/facebook/iface2:iface2");
  }

  @Test
  public void testMissingSuperSucceeds() throws IOException {
    withClasspath(
        ImmutableMap.of(
            "com/facebook/bar/Bar.java",
            Joiner.on('\n')
                .join(
                    "package com.facebook.bar;",
                    "import com.facebook.baz.Baz;",
                    "public class Bar extends Baz { }"),
            "com/facebook/baz/Baz.java",
            Joiner.on('\n').join("package com.facebook.baz;", "public class Baz { }"),
            "com/facebook/iface/Interface.java",
            Joiner.on('\n').join("package com.facebook.iface;", "public interface Interface { }")));
    compileWithValidation(
        ImmutableMap.of(
            "com/facebook/foo/Foo.java",
            Joiner.on('\n')
                .join(
                    "package com.facebook.foo;",
                    "import com.facebook.bar.Bar;",
                    "import com.facebook.iface.Interface;",
                    "public class Foo extends Bar implements Interface { }")));

    assertNoErrors();
  }

  @Test
  public void testFullyQualifiedNameFromBootClasspathSucceeds() throws IOException {
    compileWithValidation("abstract class Foo implements java.util.List { }");

    assertNoErrors();
  }

  @Test
  public void testFullyQualifiedNameFromClasspathSucceeds() throws IOException {
    withClasspath(
        ImmutableMap.of(
            "com/facebook/bar/Bar.java",
            Joiner.on('\n').join("package com.facebook.bar;", "public class Bar { }")));
    compileWithValidation("class Foo extends com.facebook.bar.Bar { }");

    assertNoErrors();
  }

  @Test
  public void testSelfSucceeds() throws IOException {
    compileWithValidation(Joiner.on('\n').join("class Foo {", "  Foo f;", "}"));

    assertNoErrors();
  }

  @Test
  public void testSiblingTypeSucceeds() throws IOException {
    compileWithValidation(
        Joiner.on('\n')
            .join("class Foo {", "  class Bar {", "    Baz b;", "  }", "  class Baz { }", "}"));

    assertNoErrors();
  }

  @Test
  public void testSiblingOfParentTypeSucceeds() throws IOException {
    compileWithValidation(
        Joiner.on('\n')
            .join(
                "class Foo {",
                "  class Bar {",
                "    class Baz {",
                "      Waz w;",
                "    }",
                "  }",
                "  class Waz { }",
                "}"));

    assertNoErrors();
  }

  @Test
  public void testPackageReferenceFromInnerTypeSucceeds() throws IOException {
    compileWithValidation(
        ImmutableMap.of(
            "Foo.java",
            Joiner.on('\n')
                .join(
                    "package com.facebook.foo;",
                    "class Foo {",
                    "  class Inner {",
                    "    Bar b;",
                    "  }",
                    "}"),
            "Bar.java",
            Joiner.on('\n').join("package com.facebook.foo;", "class Bar { }")));

    assertNoErrors();
  }

  @Test
  public void testStarImportedTypeFromClasspathFails() throws IOException {
    withClasspath(
        ImmutableMap.of(
            "com/facebook/bar/Bar.java",
            Joiner.on('\n').join("package com.facebook.bar;", "public class Bar { }")));
    testCompiler.setAllowCompilationErrors(true);
    compileWithValidation(
        Joiner.on('\n')
            .join("import com.facebook.bar.*;", "public abstract class Foo extends Bar { }"));

    assertErrors(
        Joiner.on('\n')
            .join(
                "Foo.java:2: error: Must qualify the name: com.facebook.bar.Bar",
                "public abstract class Foo extends Bar { }",
                "                                  ^"));
  }

  @Test
  public void testStarImportedTypeCompiledTogetherSucceeds() throws IOException {
    compileWithValidation(
        ImmutableMap.of(
            "com/facebook/bar/Bar.java",
            Joiner.on('\n').join("package com.facebook.bar;", "public class Bar { }"),
            "Foo.java",
            Joiner.on('\n')
                .join("import com.facebook.bar.*;", "public abstract class Foo extends Bar { }")));

    assertNoErrors();
  }

  @Test
  public void testStarImportedTypeFromBootClasspathSucceeds() throws IOException {
    this.compileWithValidation(
        Joiner.on('\n')
            .join("import java.util.*;", "public abstract class Foo implements List<String> { }"));

    assertNoErrors();
  }

  @Test
  public void testCanonicallyStaticImportedTypeSucceeds() throws IOException {
    withClasspath(
        ImmutableMap.of(
            "com/facebook/bar/Bar.java",
            Joiner.on('\n')
                .join(
                    "package com.facebook.bar;",
                    "import com.facebook.baz.Baz;",
                    "public class Bar extends Baz { }"),
            "com/facebook/baz/Baz.java",
            Joiner.on('\n')
                .join(
                    "package com.facebook.baz;",
                    "public class Baz { public static class Inner { } }")));

    this.compileWithValidation(
        Joiner.on('\n')
            .join(
                "package com.facebook.foo;",
                "import static com.facebook.baz.Baz.Inner;",
                "public class Foo {",
                "  Inner i;",
                "}"));

    assertNoErrors();
  }

  @Test
  public void testNonCanonicallyStaticImportedTypeFails() throws IOException {
    withClasspath(
        ImmutableMap.of(
            "com/facebook/bar/Bar.java",
            Joiner.on('\n')
                .join(
                    "package com.facebook.bar;",
                    "import com.facebook.baz.Baz;",
                    "public class Bar extends Baz { }"),
            "com/facebook/baz/Baz.java",
            Joiner.on('\n')
                .join(
                    "package com.facebook.baz;",
                    "public class Baz { public static class Inner { } }")));
    testCompiler.setAllowCompilationErrors(true);
    this.compileWithValidation(
        Joiner.on('\n')
            .join(
                "package com.facebook.foo;",
                "import static com.facebook.bar.Bar.Inner;",
                "public class Foo {",
                "  Inner i;",
                "}"));

    assertError(
        "Foo.java:4: error: Must qualify the name: com.facebook.baz.Baz.Inner\n"
            + "  Inner i;\n"
            + "  ^");
  }

  @Test
  public void testOwnMemberTypeSucceeds() throws IOException {
    compileWithValidation(
        Joiner.on('\n')
            .join(
                "package com.facebook.foo;",
                "public class Foo {",
                "  Inner i;",
                "  class Inner { }",
                "}"));

    assertNoErrors();
  }

  @Test
  public void testSuperclassMemberTypeFromClasspathFails() throws IOException {
    withClasspath(
        ImmutableMap.of(
            "com/facebook/bar/Bar.java",
            Joiner.on('\n')
                .join(
                    "package com.facebook.bar;",
                    "import com.facebook.foo.Baz;",
                    "public class Bar implements Baz { }"),
            "com/facebook/foo/Baz.java",
            Joiner.on('\n')
                .join(
                    "package com.facebook.foo;", "public interface Baz { interface Inner { } }")));
    testCompiler.setAllowCompilationErrors(true);
    compileWithValidation(
        ImmutableMap.of(
            "Foo.java",
            Joiner.on('\n')
                .join(
                    "package com.facebook.foo;",
                    "import com.facebook.bar.Bar;",
                    "class Foo extends Bar {",
                    "  Inner i;",
                    "}")));

    assertError(
        Joiner.on('\n')
            .join("Foo.java:4: error: Must qualify the name: Baz.Inner", "  Inner i;", "  ^"));
  }

  @Test
  public void testImportedSuperclassMemberTypeFromClasspathSucceeds() throws IOException {
    withClasspath(
        ImmutableMap.of(
            "com/facebook/bar/Bar.java",
            Joiner.on('\n')
                .join(
                    "package com.facebook.bar;",
                    "import com.facebook.foo.Baz;",
                    "public class Bar implements Baz { }"),
            "com/facebook/foo/Baz.java",
            Joiner.on('\n')
                .join(
                    "package com.facebook.foo;", "public interface Baz { interface Inner { } }")));
    compileWithValidation(
        ImmutableMap.of(
            "Foo.java",
            Joiner.on('\n')
                .join(
                    "package com.facebook.foo;",
                    "import com.facebook.bar.Bar;",
                    "import com.facebook.foo.Baz.Inner;",
                    "class Foo extends Bar {",
                    "  Inner i;",
                    "}")));

    assertNoErrors();
  }

  @Test
  public void testPackageMemberTypeCompiledTogetherSucceeds() throws IOException {
    compileWithValidation(
        ImmutableMap.of(
            "Foo.java",
            Joiner.on('\n').join("package com.facebook.foo;", "class Foo extends Bar { }"),
            "Bar.java",
            Joiner.on('\n').join("package com.facebook.foo;", "class Bar { }")));

    assertNoErrors();
  }

  @Test
  public void testPackageMemberTypeFromClasspathSucceeds() throws IOException {
    withClasspath(
        ImmutableMap.of(
            "com/facebook/foo/Bar.java",
            Joiner.on('\n').join("package com.facebook.foo;", "class Bar { }")));
    compileWithValidation(
        ImmutableMap.of(
            "Foo.java",
            Joiner.on('\n').join("package com.facebook.foo;", "class Foo extends Bar { }")));

    assertNoErrors();
  }

  @Test
  public void testQualifiedPackageMemberInnerTypeFromClasspathSucceeds() throws IOException {
    withClasspath(
        ImmutableMap.of(
            "com/facebook/foo/Bar.java",
            Joiner.on('\n')
                .join("package com.facebook.foo;", "class Bar { static class Inner { } }")));
    compileWithValidation(
        ImmutableMap.of(
            "Foo.java",
            Joiner.on('\n').join("package com.facebook.foo;", "class Foo extends Bar.Inner { }")));

    assertNoErrors();
  }

  @Test
  public void testUnqualifiedPackageMemberInnerTypeFromClasspathFails() throws IOException {
    withClasspath(
        ImmutableMap.of(
            "com/facebook/foo/Bar.java",
            Joiner.on('\n')
                .join("package com.facebook.foo;", "class Bar { static class Inner { } }")));
    testCompiler.setAllowCompilationErrors(true);
    compileWithValidation(
        ImmutableMap.of(
            "Foo.java",
            Joiner.on('\n')
                .join("package com.facebook.foo;", "class Foo extends Bar {", "  Inner i;", "}")));

    assertError(
        Joiner.on('\n')
            .join("Foo.java:3: error: Must qualify the name: Bar.Inner", "  Inner i;", "  ^"));
  }

  @Test
  public void testQualifiedNestedGenericSucceeds() throws IOException {
    withClasspath(
        ImmutableMap.of(
            "com/facebook/bar/Bar.java",
            Joiner.on('\n')
                .join(
                    "package com.facebook.bar;",
                    "public class Bar<T> {",
                    "  public class Inner<U> { }",
                    "}")));

    compileWithValidation(
        ImmutableMap.of(
            "Foo.java",
            Joiner.on('\n')
                .join(
                    "package com.facebook.foo;",
                    "public class Foo {",
                    "  com.facebook.bar.Bar<Integer>.Inner<String> i;",
                    "}")));

    assertNoErrors();
  }

  @Test
  public void testQualifiedAnnotatedTypeSucceeds() throws IOException {
    withClasspath(
        ImmutableMap.of(
            "com/facebook/bar/Bar.java",
            Joiner.on('\n')
                .join(
                    "package com.facebook.bar;",
                    "public class Bar {",
                    "  public class Inner { }",
                    "}")));

    taskListenerFactory.setRuleIsRequiredForSourceAbi(true);
    compileWithValidation(
        ImmutableMap.of(
            "Foo.java",
            Joiner.on('\n')
                .join(
                    "package com.facebook.foo;",
                    "import java.lang.annotation.*;",
                    "public class Foo {",
                    "  com.facebook.bar.@Anno Bar.Inner i;",
                    "}",
                    "@Target(ElementType.TYPE_USE)",
                    "@interface Anno { }")));

    assertNoErrors();
  }

  @Test
  public void testConstantCompiledTogetherSucceeds() throws IOException {
    compileWithValidation(
        ImmutableMap.of(
            "Foo.java",
            Joiner.on('\n')
                .join(
                    "class Foo {",
                    "  public static final int CONSTANT = Constants.CONSTANT + 1 + Constants.CONSTANT2;",
                    "}"),
            "Constants.java",
            Joiner.on('\n')
                .join(
                    "class Constants {",
                    "  public static final int CONSTANT = 3 + 5;",
                    "  public static final int CONSTANT2 = 3;",
                    "}")));

    assertNoErrors();
  }

  @Test
  public void testConstantFromUnavailableTargetFails() throws IOException {
    withClasspath(
        ImmutableMap.of(
            "Constants.java",
            Joiner.on('\n')
                .join(
                    "package com.facebook.constants;",
                    "public class Constants {",
                    "  public static final int CONSTANT = 3 + 5;",
                    "  public static final int CONST2 = 3;",
                    "}")));
    testCompiler.setAllowCompilationErrors(true);
    compileWithValidation(
        ImmutableMap.of(
            "Foo.java",
            Joiner.on('\n')
                .join(
                    "import com.facebook.constants.Constants;",
                    "class Foo {",
                    "  public static final int CONSTANT = Constants.CONSTANT + 1 + Constants.CONST2;",
                    "}")));

    assertErrors(
        Joiner.on('\n')
            .join(
                "Foo.java:3: error: This constant will not be available during source-only ABI generation.",
                "  public static final int CONSTANT = Constants.CONSTANT + 1 + Constants.CONST2;",
                "                                              ^",
                "  For a quick fix, add required_for_source_only_abi = True to //com/facebook/constants:constants.",
                "  A better fix is to move Constants to a new rule that contains only",
                "  constants, and mark that rule required_for_source_only_abi."),
        Joiner.on('\n')
            .join(
                "Foo.java:3: error: This constant will not be available during source-only ABI generation.",
                "  public static final int CONSTANT = Constants.CONSTANT + 1 + Constants.CONST2;",
                "                                                                       ^",
                "  For a quick fix, add required_for_source_only_abi = True to //com/facebook/constants:constants.",
                "  A better fix is to move Constants to a new rule that contains only",
                "  constants, and mark that rule required_for_source_only_abi."));
  }

  @Test
  public void testConstantFromAvailableTargetFails() throws IOException {
    withClasspath(
        ImmutableMap.of(
            "Constants.java",
            Joiner.on('\n')
                .join(
                    "package com.facebook.constants;",
                    "public class Constants {",
                    "  public static final int CONSTANT = 3 + 5;",
                    "  public static final int CONST2 = 3;",
                    "}")));
    taskListenerFactory.addTargetAvailableForSourceOnlyAbi("//com/facebook/constants:constants");
    compileWithValidation(
        ImmutableMap.of(
            "Foo.java",
            Joiner.on('\n')
                .join(
                    "import com.facebook.constants.Constants;",
                    "class Foo {",
                    "  public static final int CONSTANT = Constants.CONSTANT + 1 + Constants.CONST2;",
                    "}")));

    assertNoErrors();
  }

  protected Iterable<? extends CompilationUnitTree> compileWithValidation(String source)
      throws IOException {
    return compileWithValidation(ImmutableMap.of("Foo.java", source));
  }

  protected Iterable<? extends CompilationUnitTree> compileWithValidation(
      Map<String, String> sources) throws IOException {
    return compile(sources, taskListenerFactory);
  }
}
