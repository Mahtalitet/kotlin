/*
 * Copyright 2010-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.idea.configuration;

import com.intellij.jarRepository.RepositoryLibraryType;
import com.intellij.openapi.externalSystem.service.project.IdeModifiableModelsProviderImpl;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.LibraryOrderEntry;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.roots.RootPolicy;
import com.intellij.openapi.roots.impl.libraries.LibraryEx;
import com.intellij.openapi.roots.libraries.Library;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiJavaModule;
import com.intellij.psi.PsiRequiresStatement;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.kotlin.cli.common.arguments.K2JSCompilerArguments;
import org.jetbrains.kotlin.cli.common.arguments.K2JVMCompilerArguments;
import org.jetbrains.kotlin.config.*;
import org.jetbrains.kotlin.idea.compiler.configuration.KotlinCommonCompilerArgumentsHolder;
import org.jetbrains.kotlin.idea.facet.FacetUtilsKt;
import org.jetbrains.kotlin.idea.facet.KotlinFacet;
import org.jetbrains.kotlin.idea.framework.JSLibraryKind;
import org.jetbrains.kotlin.idea.framework.JsLibraryStdDetectionUtil;
import org.jetbrains.kotlin.idea.framework.LibraryEffectiveKindProviderKt;
import org.jetbrains.kotlin.idea.project.PlatformKt;
import org.jetbrains.kotlin.idea.util.Java9StructureUtilKt;
import org.jetbrains.kotlin.idea.versions.KotlinRuntimeLibraryUtilKt;
import org.jetbrains.kotlin.resolve.jvm.modules.JavaModuleKt;
import org.jetbrains.kotlin.utils.PathUtil;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.StreamSupport;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

public class ConfigureKotlinTest extends AbstractConfigureKotlinTest {
    public void testNewLibrary_copyJar() {
        doTestOneJavaModule(KotlinWithLibraryConfigurator.FileState.COPY);

        ModuleRootManager.getInstance(getModule()).orderEntries().forEachLibrary(library -> {
            assertSameElements(
                    Arrays.stream(library.getRootProvider().getFiles(OrderRootType.CLASSES)).map(VirtualFile::getName).toArray(),
                    PathUtil.KOTLIN_JAVA_STDLIB_JAR, PathUtil.KOTLIN_JAVA_REFLECT_JAR, PathUtil.KOTLIN_TEST_JAR);

            assertSameElements(
                    Arrays.stream(library.getRootProvider().getFiles(OrderRootType.SOURCES)).map(VirtualFile::getName).toArray(),
                    PathUtil.KOTLIN_JAVA_STDLIB_SRC_JAR, PathUtil.KOTLIN_REFLECT_SRC_JAR, PathUtil.KOTLIN_TEST_SRC_JAR);

            return true;
        });
    }

    public void testNewLibrary_doNotCopyJar() {
        doTestOneJavaModule(KotlinWithLibraryConfigurator.FileState.DO_NOT_COPY);
    }

    public void testLibraryWithoutPaths_jarExists() {
        doTestOneJavaModule(KotlinWithLibraryConfigurator.FileState.EXISTS);
    }

    public void testNewLibrary_jarExists() {
        doTestOneJavaModule(KotlinWithLibraryConfigurator.FileState.EXISTS);
    }

    public void testLibraryWithoutPaths_copyJar() {
        doTestOneJavaModule(KotlinWithLibraryConfigurator.FileState.COPY);
    }

    public void testLibraryWithoutPaths_doNotCopyJar() {
        doTestOneJavaModule(KotlinWithLibraryConfigurator.FileState.DO_NOT_COPY);
    }

    @SuppressWarnings("ConstantConditions")
    public void testTwoModules_exists() {
        Module[] modules = getModules();
        for (Module module : modules) {
            if (module.getName().equals("module1")) {
                configure(module, KotlinWithLibraryConfigurator.FileState.DO_NOT_COPY, Companion.getJAVA_CONFIGURATOR());
                Companion.assertConfigured(module, Companion.getJAVA_CONFIGURATOR());
            }
            else if (module.getName().equals("module2")) {
                Companion.assertNotConfigured(module, Companion.getJAVA_CONFIGURATOR());
                configure(module, KotlinWithLibraryConfigurator.FileState.EXISTS, Companion.getJAVA_CONFIGURATOR());
                Companion.assertConfigured(module, Companion.getJAVA_CONFIGURATOR());
            }
        }
    }

    public void testLibraryNonDefault_libExistInDefault() throws IOException {
        Module module = getModule();

        // Move fake runtime jar to default library path to pretend library is already configured
        FileUtil.copy(
                new File(getProject().getBasePath() + "/lib/kotlin-runtime.jar"),
                new File(Companion.getJAVA_CONFIGURATOR().getDefaultPathToJarFile(getProject()) + "/kotlin-runtime.jar"));

        Companion.assertNotConfigured(module, Companion.getJAVA_CONFIGURATOR());
        Companion.getJAVA_CONFIGURATOR().configure(myProject, Collections.<Module>emptyList());
        Companion.assertProperlyConfigured(module, Companion.getJAVA_CONFIGURATOR());
    }

    public void testTwoModulesWithNonDefaultPath_doNotCopyInDefault() throws IOException {
        doTestConfigureModulesWithNonDefaultSetup(Companion.getJAVA_CONFIGURATOR());
        assertEmpty(ConfigureKotlinInProjectUtilsKt.getCanBeConfiguredModules(myProject, Companion.getJS_CONFIGURATOR()));
    }

    public void testTwoModulesWithJSNonDefaultPath_doNotCopyInDefault() throws IOException {
        doTestConfigureModulesWithNonDefaultSetup(Companion.getJS_CONFIGURATOR());
        assertEmpty(ConfigureKotlinInProjectUtilsKt.getCanBeConfiguredModules(myProject, Companion.getJAVA_CONFIGURATOR()));
    }

    public void testNewLibrary_jarExists_js() {
        doTestOneJsModule(KotlinWithLibraryConfigurator.FileState.EXISTS);
    }

    public void testNewLibrary_copyJar_js() {
        doTestOneJsModule(KotlinWithLibraryConfigurator.FileState.COPY);
    }

    public void testNewLibrary_doNotCopyJar_js() {
        doTestOneJsModule(KotlinWithLibraryConfigurator.FileState.DO_NOT_COPY);
    }

    public void testJsLibraryWithoutPaths_jarExists() {
        doTestOneJsModule(KotlinWithLibraryConfigurator.FileState.EXISTS);
    }

    public void testJsLibraryWithoutPaths_copyJar() {
        doTestOneJsModule(KotlinWithLibraryConfigurator.FileState.COPY);
    }

    public void testJsLibraryWithoutPaths_doNotCopyJar() {
        doTestOneJsModule(KotlinWithLibraryConfigurator.FileState.DO_NOT_COPY);
    }

    public void testJsLibraryWrongKind() {
        doTestOneJsModule(KotlinWithLibraryConfigurator.FileState.EXISTS);
        assertEquals(1, ModuleRootManager.getInstance(getModule()).orderEntries().process(new LibraryCountingRootPolicy(), 0).intValue());
    }

    public void testProjectWithoutFacetWithRuntime106WithoutLanguageLevel() {
        assertEquals(LanguageVersion.KOTLIN_1_0, PlatformKt.getLanguageVersionSettings(myProject, null).getLanguageVersion());
        assertEquals(LanguageVersion.KOTLIN_1_0, PlatformKt.getLanguageVersionSettings(getModule()).getLanguageVersion());
    }

    public void testProjectWithoutFacetWithRuntime11WithoutLanguageLevel() {
        assertEquals(LanguageVersion.LATEST_STABLE, PlatformKt.getLanguageVersionSettings(myProject, null).getLanguageVersion());
        assertEquals(LanguageVersion.LATEST_STABLE, PlatformKt.getLanguageVersionSettings(getModule()).getLanguageVersion());
    }

    public void testProjectWithoutFacetWithRuntime11WithLanguageLevel10() {
        assertEquals(LanguageVersion.KOTLIN_1_0, PlatformKt.getLanguageVersionSettings(myProject, null).getLanguageVersion());
        assertEquals(LanguageVersion.KOTLIN_1_0, PlatformKt.getLanguageVersionSettings(getModule()).getLanguageVersion());
    }

    public void testProjectWithFacetWithRuntime11WithLanguageLevel10() {
        assertEquals(LanguageVersion.LATEST_STABLE, PlatformKt.getLanguageVersionSettings(myProject, null).getLanguageVersion());
        assertEquals(LanguageVersion.KOTLIN_1_0, PlatformKt.getLanguageVersionSettings(getModule()).getLanguageVersion());
    }

    public void testJsLibraryVersion11() {
        Library jsRuntime = KotlinRuntimeLibraryUtilKt.findAllUsedLibraries(myProject).keySet().iterator().next();
        String version = JsLibraryStdDetectionUtil.INSTANCE.getJsLibraryStdVersion(jsRuntime, myProject);
        assertEquals("1.1.0", version);
    }

    public void testJsLibraryVersion106() {
        Library jsRuntime = KotlinRuntimeLibraryUtilKt.findAllUsedLibraries(myProject).keySet().iterator().next();
        String version = JsLibraryStdDetectionUtil.INSTANCE.getJsLibraryStdVersion(jsRuntime, myProject);
        assertEquals("1.0.6", version);
    }

    @SuppressWarnings("ConstantConditions")
    public void testMavenProvidedTestJsKind() {
        LibraryEx jsTest = (LibraryEx) ContainerUtil.find(
                KotlinRuntimeLibraryUtilKt.findAllUsedLibraries(myProject).keySet(),
                (library) -> library.getName().contains("kotlin-test-js")
        );
        assertEquals(RepositoryLibraryType.REPOSITORY_LIBRARY_KIND, jsTest.getKind());
        assertEquals(JSLibraryKind.INSTANCE, LibraryEffectiveKindProviderKt.effectiveKind(jsTest, myProject));
    }

    @SuppressWarnings("ConstantConditions")
    public void testJvmProjectWithV1FacetConfig() {
        KotlinFacetSettings settings = KotlinFacetSettingsProvider.Companion.getInstance(myProject).getInitializedSettings(getModule());
        K2JVMCompilerArguments arguments = (K2JVMCompilerArguments) settings.getCompilerArguments();
        assertEquals(false, settings.getUseProjectSettings());
        assertEquals("1.1", settings.getLanguageLevel().getDescription());
        assertEquals("1.0", settings.getApiLevel().getDescription());
        assertEquals(TargetPlatformKind.Jvm.Companion.get(JvmTarget.JVM_1_8), settings.getTargetPlatformKind());
        assertEquals("1.1", arguments.getLanguageVersion());
        assertEquals("1.0", arguments.getApiVersion());
        assertEquals(LanguageFeature.State.ENABLED_WITH_WARNING, CoroutineSupport.byCompilerArguments(arguments));
        assertEquals("1.7", arguments.getJvmTarget());
        assertEquals("-version -Xallow-kotlin-package -Xskip-metadata-version-check", settings.getCompilerSettings().getAdditionalArguments());
    }

    @SuppressWarnings("ConstantConditions")
    public void testJsProjectWithV1FacetConfig() {
        KotlinFacetSettings settings = KotlinFacetSettingsProvider.Companion.getInstance(myProject).getInitializedSettings(getModule());
        K2JSCompilerArguments arguments = (K2JSCompilerArguments) settings.getCompilerArguments();
        assertEquals(false, settings.getUseProjectSettings());
        assertEquals("1.1", settings.getLanguageLevel().getDescription());
        assertEquals("1.0", settings.getApiLevel().getDescription());
        assertEquals(TargetPlatformKind.JavaScript.INSTANCE, settings.getTargetPlatformKind());
        assertEquals("1.1", arguments.getLanguageVersion());
        assertEquals("1.0", arguments.getApiVersion());
        assertEquals(LanguageFeature.State.ENABLED_WITH_WARNING, CoroutineSupport.byCompilerArguments(arguments));
        assertEquals("amd", arguments.getModuleKind());
        assertEquals("-version -meta-info", settings.getCompilerSettings().getAdditionalArguments());
    }

    @SuppressWarnings("ConstantConditions")
    public void testJvmProjectWithV2FacetConfig() {
        KotlinFacetSettings settings = KotlinFacetSettingsProvider.Companion.getInstance(myProject).getInitializedSettings(getModule());
        K2JVMCompilerArguments arguments = (K2JVMCompilerArguments) settings.getCompilerArguments();
        assertEquals(false, settings.getUseProjectSettings());
        assertEquals("1.1", settings.getLanguageLevel().getDescription());
        assertEquals("1.0", settings.getApiLevel().getDescription());
        assertEquals(TargetPlatformKind.Jvm.Companion.get(JvmTarget.JVM_1_8), settings.getTargetPlatformKind());
        assertEquals("1.1", arguments.getLanguageVersion());
        assertEquals("1.0", arguments.getApiVersion());
        assertEquals(LanguageFeature.State.ENABLED, CoroutineSupport.byCompilerArguments(arguments));
        assertEquals("1.7", arguments.getJvmTarget());
        assertEquals("-version -Xallow-kotlin-package -Xskip-metadata-version-check", settings.getCompilerSettings().getAdditionalArguments());
    }

    @SuppressWarnings("ConstantConditions")
    public void testJsProjectWithV2FacetConfig() {
        KotlinFacetSettings settings = KotlinFacetSettingsProvider.Companion.getInstance(myProject).getInitializedSettings(getModule());
        K2JSCompilerArguments arguments = (K2JSCompilerArguments) settings.getCompilerArguments();
        assertEquals(false, settings.getUseProjectSettings());
        assertEquals("1.1", settings.getLanguageLevel().getDescription());
        assertEquals("1.0", settings.getApiLevel().getDescription());
        assertEquals(TargetPlatformKind.JavaScript.INSTANCE, settings.getTargetPlatformKind());
        assertEquals("1.1", arguments.getLanguageVersion());
        assertEquals("1.0", arguments.getApiVersion());
        assertEquals(LanguageFeature.State.ENABLED_WITH_ERROR, CoroutineSupport.byCompilerArguments(arguments));
        assertEquals("amd", arguments.getModuleKind());
        assertEquals("-version -meta-info", settings.getCompilerSettings().getAdditionalArguments());
    }

    @SuppressWarnings("ConstantConditions")
    public void testJvmProjectWithV3FacetConfig() {
        KotlinFacetSettings settings = KotlinFacetSettingsProvider.Companion.getInstance(myProject).getInitializedSettings(getModule());
        K2JVMCompilerArguments arguments = (K2JVMCompilerArguments) settings.getCompilerArguments();
        assertEquals(false, settings.getUseProjectSettings());
        assertEquals("1.1", settings.getLanguageLevel().getDescription());
        assertEquals("1.0", settings.getApiLevel().getDescription());
        assertEquals(TargetPlatformKind.Jvm.Companion.get(JvmTarget.JVM_1_8), settings.getTargetPlatformKind());
        assertEquals("1.1", arguments.getLanguageVersion());
        assertEquals("1.0", arguments.getApiVersion());
        assertEquals(LanguageFeature.State.ENABLED, CoroutineSupport.byCompilerArguments(arguments));
        assertEquals("1.7", arguments.getJvmTarget());
        assertEquals("-version -Xallow-kotlin-package -Xskip-metadata-version-check", settings.getCompilerSettings().getAdditionalArguments());
    }

    public void testImplementsDependency() {
        ModuleManager moduleManager = ModuleManager.getInstance(myProject);

        Module module1 = moduleManager.findModuleByName("module1");
        assert module1 != null;

        Module module2 = moduleManager.findModuleByName("module2");
        assert module2 != null;

        assertEquals(KotlinFacet.Companion.get(module1).getConfiguration().getSettings().getImplementedModuleNames(), emptyList());
        assertEquals(KotlinFacet.Companion.get(module2).getConfiguration().getSettings().getImplementedModuleNames(), singletonList("module1"));
    }

    public void testJava9WithModuleInfo() {
        checkAddStdlibModule();
    }

    public void testJava9WithModuleInfoWithStdlibAlready() {
        checkAddStdlibModule();
    }

    public void testProjectWithFreeArgs() {
        assertEquals(
                Collections.singletonList("true"),
                KotlinCommonCompilerArgumentsHolder.Companion.getInstance(myProject).getSettings().getFreeArgs()
        );
    }

    private void checkAddStdlibModule() {
        doTestOneJavaModule(KotlinWithLibraryConfigurator.FileState.COPY);

        Module module = getModule();
        Sdk moduleSdk = ModuleRootManager.getInstance(getModule()).getSdk();
        assertNotNull("Module SDK is not defined", moduleSdk);

        PsiJavaModule javaModule = Java9StructureUtilKt.findFirstPsiJavaModule(module);
        assertNotNull(javaModule);

        PsiRequiresStatement stdlibDirective =
                Java9StructureUtilKt.findRequireDirective(javaModule, JavaModuleKt.KOTLIN_STDLIB_MODULE_NAME);
        assertNotNull("Require directive for " + JavaModuleKt.KOTLIN_STDLIB_MODULE_NAME + " is expected",
                      stdlibDirective);

        long numberOfStdlib = StreamSupport.stream(javaModule.getRequires().spliterator(), false)
                .filter((statement) -> JavaModuleKt.KOTLIN_STDLIB_MODULE_NAME.equals(statement.getModuleName()))
                .count();

        assertTrue("Only one standard library directive is expected", numberOfStdlib == 1);
    }

    private void configureFacetAndCheckJvm(JvmTarget jvmTarget) {
        IdeModifiableModelsProviderImpl modelsProvider = new IdeModifiableModelsProviderImpl(getProject());
        try {
            KotlinFacet facet = FacetUtilsKt.getOrCreateFacet(getModule(), modelsProvider, false, false);
            TargetPlatformKind.Jvm platformKind = TargetPlatformKind.Jvm.Companion.get(jvmTarget);
            FacetUtilsKt.configureFacet(
                    facet,
                    "1.1",
                    LanguageFeature.State.ENABLED,
                    platformKind,
                    modelsProvider
            );
            assertEquals(platformKind, facet.getConfiguration().getSettings().getTargetPlatformKind());
            assertEquals(jvmTarget.getDescription(),
                         ((K2JVMCompilerArguments) facet.getConfiguration().getSettings().getCompilerArguments()).getJvmTarget());
        }
        finally {
            modelsProvider.dispose();
        }
    }

    @SuppressWarnings("ConstantConditions")
    public void testJvm8InProjectJvm6InModule() {
        configureFacetAndCheckJvm(JvmTarget.JVM_1_6);
    }

    @SuppressWarnings("ConstantConditions")
    public void testJvm6InProjectJvm8InModule() {
        configureFacetAndCheckJvm(JvmTarget.JVM_1_8);
    }

    public void testProjectWithoutFacetWithJvmTarget18() {
        assertEquals(TargetPlatformKind.Jvm.Companion.get(JvmTarget.JVM_1_8), PlatformKt.getTargetPlatform(getModule()));
    }

    private static class LibraryCountingRootPolicy extends RootPolicy<Integer> {
        @Override
        public Integer visitLibraryOrderEntry(LibraryOrderEntry libraryOrderEntry, Integer value) {
            return value + 1;
        }
    }
}
