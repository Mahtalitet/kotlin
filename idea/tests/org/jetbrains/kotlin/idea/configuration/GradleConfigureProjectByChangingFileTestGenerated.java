/*
 * Copyright 2010-2016 JetBrains s.r.o.
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

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TargetBackend;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("idea/testData/configuration/gradle")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class GradleConfigureProjectByChangingFileTestGenerated extends AbstractGradleConfigureProjectByChangingFileTest {
    public void testAllFilesPresentInGradle() throws Exception {
        KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("idea/testData/configuration/gradle"), Pattern.compile("(\\w+)_before\\.gradle$"), TargetBackend.ANY, true);
    }

    @TestMetadata("default_before.gradle")
    public void testDefault() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("idea/testData/configuration/gradle/default_before.gradle");
        doTestGradle(fileName);
    }

    @TestMetadata("eapVersion_before.gradle")
    public void testEapVersion() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("idea/testData/configuration/gradle/eapVersion_before.gradle");
        doTestGradle(fileName);
    }

    @TestMetadata("missedLibrary_before.gradle")
    public void testMissedLibrary() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("idea/testData/configuration/gradle/missedLibrary_before.gradle");
        doTestGradle(fileName);
    }

    @TestMetadata("plugin_present_before.gradle")
    public void testPlugin_present() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("idea/testData/configuration/gradle/plugin_present_before.gradle");
        doTestGradle(fileName);
    }

    @TestMetadata("rcVersion_before.gradle")
    public void testRcVersion() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("idea/testData/configuration/gradle/rcVersion_before.gradle");
        doTestGradle(fileName);
    }
}
