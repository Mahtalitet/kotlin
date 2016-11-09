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

package org.jetbrains.kotlin.idea.parameterInfo;

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
@TestMetadata("idea/testData/parameterInfo")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class ParameterInfoTestGenerated extends AbstractParameterInfoTest {
    public void testAllFilesPresentInParameterInfo() throws Exception {
        KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("idea/testData/parameterInfo"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true, "withLib1/sharedLib", "withLib2/sharedLib", "withLib3/sharedLib");
    }

    @TestMetadata("idea/testData/parameterInfo/arrayAccess")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class ArrayAccess extends AbstractParameterInfoTest {
        public void testAllFilesPresentInArrayAccess() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("idea/testData/parameterInfo/arrayAccess"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
        }

        @TestMetadata("Overloads.kt")
        public void testOverloads() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/arrayAccess/Overloads.kt");
            doTest(fileName);
        }

        @TestMetadata("Overloads2.kt")
        public void testOverloads2() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/arrayAccess/Overloads2.kt");
            doTest(fileName);
        }

        @TestMetadata("Set.kt")
        public void testSet() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/arrayAccess/Set.kt");
            doTest(fileName);
        }

        @TestMetadata("SetTooManyArgs.kt")
        public void testSetTooManyArgs() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/arrayAccess/SetTooManyArgs.kt");
            doTest(fileName);
        }

        @TestMetadata("Simple.kt")
        public void testSimple() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/arrayAccess/Simple.kt");
            doTest(fileName);
        }
    }

    @TestMetadata("idea/testData/parameterInfo/functionCall")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class FunctionCall extends AbstractParameterInfoTest {
        public void testAllFilesPresentInFunctionCall() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("idea/testData/parameterInfo/functionCall"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
        }

        @TestMetadata("DefaultValuesFromLib.kt")
        public void testDefaultValuesFromLib() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/DefaultValuesFromLib.kt");
            doTest(fileName);
        }

        @TestMetadata("Deprecated.kt")
        public void testDeprecated() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/Deprecated.kt");
            doTest(fileName);
        }

        @TestMetadata("ExtensionOnClassObject.kt")
        public void testExtensionOnClassObject() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/ExtensionOnClassObject.kt");
            doTest(fileName);
        }

        @TestMetadata("FunctionalValue1.kt")
        public void testFunctionalValue1() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/FunctionalValue1.kt");
            doTest(fileName);
        }

        @TestMetadata("FunctionalValue2.kt")
        public void testFunctionalValue2() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/FunctionalValue2.kt");
            doTest(fileName);
        }

        @TestMetadata("InheritedFunctions.kt")
        public void testInheritedFunctions() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/InheritedFunctions.kt");
            doTest(fileName);
        }

        @TestMetadata("InheritedWithCurrentFunctions.kt")
        public void testInheritedWithCurrentFunctions() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/InheritedWithCurrentFunctions.kt");
            doTest(fileName);
        }

        @TestMetadata("Invoke.kt")
        public void testInvoke() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/Invoke.kt");
            doTest(fileName);
        }

        @TestMetadata("LocalFunctionBug.kt")
        public void testLocalFunctionBug() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/LocalFunctionBug.kt");
            doTest(fileName);
        }

        @TestMetadata("NamedAndDefaultParameter.kt")
        public void testNamedAndDefaultParameter() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/NamedAndDefaultParameter.kt");
            doTest(fileName);
        }

        @TestMetadata("NamedParameter.kt")
        public void testNamedParameter() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/NamedParameter.kt");
            doTest(fileName);
        }

        @TestMetadata("NamedParameter2.kt")
        public void testNamedParameter2() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/NamedParameter2.kt");
            doTest(fileName);
        }

        @TestMetadata("NoAnnotations.kt")
        public void testNoAnnotations() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/NoAnnotations.kt");
            doTest(fileName);
        }

        @TestMetadata("NoSynthesizedParameterNames.kt")
        public void testNoSynthesizedParameterNames() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/NoSynthesizedParameterNames.kt");
            doTest(fileName);
        }

        @TestMetadata("NotAccessible.kt")
        public void testNotAccessible() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/NotAccessible.kt");
            doTest(fileName);
        }

        @TestMetadata("NotGreen.kt")
        public void testNotGreen() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/NotGreen.kt");
            doTest(fileName);
        }

        @TestMetadata("NullableTypeCall.kt")
        public void testNullableTypeCall() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/NullableTypeCall.kt");
            doTest(fileName);
        }

        @TestMetadata("OtherConstructorFromSecondary.kt")
        public void testOtherConstructorFromSecondary() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/OtherConstructorFromSecondary.kt");
            doTest(fileName);
        }

        @TestMetadata("Println.kt")
        public void testPrintln() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/Println.kt");
            doTest(fileName);
        }

        @TestMetadata("PrivateConstructor.kt")
        public void testPrivateConstructor() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/PrivateConstructor.kt");
            doTest(fileName);
        }

        @TestMetadata("Simple.kt")
        public void testSimple() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/Simple.kt");
            doTest(fileName);
        }

        @TestMetadata("SimpleConstructor.kt")
        public void testSimpleConstructor() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/SimpleConstructor.kt");
            doTest(fileName);
        }

        @TestMetadata("SmartCastReceiver.kt")
        public void testSmartCastReceiver() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/SmartCastReceiver.kt");
            doTest(fileName);
        }

        @TestMetadata("SmartCastReceiver2.kt")
        public void testSmartCastReceiver2() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/SmartCastReceiver2.kt");
            doTest(fileName);
        }

        @TestMetadata("SubstituteExpectedType.kt")
        public void testSubstituteExpectedType() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/SubstituteExpectedType.kt");
            doTest(fileName);
        }

        @TestMetadata("SubstituteExplicitTypeArgs.kt")
        public void testSubstituteExplicitTypeArgs() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/SubstituteExplicitTypeArgs.kt");
            doTest(fileName);
        }

        @TestMetadata("SubstituteFromArguments1.kt")
        public void testSubstituteFromArguments1() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/SubstituteFromArguments1.kt");
            doTest(fileName);
        }

        @TestMetadata("SubstituteFromArguments2.kt")
        public void testSubstituteFromArguments2() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/SubstituteFromArguments2.kt");
            doTest(fileName);
        }

        @TestMetadata("SubstituteFromArguments3.kt")
        public void testSubstituteFromArguments3() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/SubstituteFromArguments3.kt");
            doTest(fileName);
        }

        @TestMetadata("SubstituteFromArguments4.kt")
        public void testSubstituteFromArguments4() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/SubstituteFromArguments4.kt");
            doTest(fileName);
        }

        @TestMetadata("SubstituteFromArgumentsOnTyping.kt")
        public void testSubstituteFromArgumentsOnTyping() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/SubstituteFromArgumentsOnTyping.kt");
            doTest(fileName);
        }

        @TestMetadata("SuperConstructorCall.kt")
        public void testSuperConstructorCall() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/SuperConstructorCall.kt");
            doTest(fileName);
        }

        @TestMetadata("SuperConstructorFromSecondary.kt")
        public void testSuperConstructorFromSecondary() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/SuperConstructorFromSecondary.kt");
            doTest(fileName);
        }

        @TestMetadata("TooManyArgs.kt")
        public void testTooManyArgs() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/TooManyArgs.kt");
            doTest(fileName);
        }

        @TestMetadata("TwoFunctions.kt")
        public void testTwoFunctions() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/TwoFunctions.kt");
            doTest(fileName);
        }

        @TestMetadata("TwoFunctionsGrey.kt")
        public void testTwoFunctionsGrey() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/TwoFunctionsGrey.kt");
            doTest(fileName);
        }

        @TestMetadata("UpdateOnTyping.kt")
        public void testUpdateOnTyping() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/functionCall/UpdateOnTyping.kt");
            doTest(fileName);
        }
    }

    @TestMetadata("idea/testData/parameterInfo/typeArguments")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class TypeArguments extends AbstractParameterInfoTest {
        public void testAllFilesPresentInTypeArguments() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("idea/testData/parameterInfo/typeArguments"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
        }

        @TestMetadata("BaseClass.kt")
        public void testBaseClass() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/typeArguments/BaseClass.kt");
            doTest(fileName);
        }

        @TestMetadata("Constraints.kt")
        public void testConstraints() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/typeArguments/Constraints.kt");
            doTest(fileName);
        }

        @TestMetadata("ConstructorCall.kt")
        public void testConstructorCall() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/typeArguments/ConstructorCall.kt");
            doTest(fileName);
        }

        @TestMetadata("FunctionCall.kt")
        public void testFunctionCall() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/typeArguments/FunctionCall.kt");
            doTest(fileName);
        }

        @TestMetadata("JavaClass.kt")
        public void testJavaClass() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/typeArguments/JavaClass.kt");
            doTest(fileName);
        }

        @TestMetadata("Overloads.kt")
        public void testOverloads() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/typeArguments/Overloads.kt");
            doTest(fileName);
        }

        @TestMetadata("Reified.kt")
        public void testReified() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/typeArguments/Reified.kt");
            doTest(fileName);
        }

        @TestMetadata("VariableType.kt")
        public void testVariableType() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/typeArguments/VariableType.kt");
            doTest(fileName);
        }
    }

    @TestMetadata("idea/testData/parameterInfo/withLib1")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class WithLib1 extends AbstractParameterInfoTest {
        public void testAllFilesPresentInWithLib1() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("idea/testData/parameterInfo/withLib1"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true, "sharedLib");
        }

        @TestMetadata("useJavaFromLib.kt")
        public void testUseJavaFromLib() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/withLib1/useJavaFromLib.kt");
            doTest(fileName);
        }
    }

    @TestMetadata("idea/testData/parameterInfo/withLib2")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class WithLib2 extends AbstractParameterInfoTest {
        public void testAllFilesPresentInWithLib2() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("idea/testData/parameterInfo/withLib2"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true, "sharedLib");
        }

        @TestMetadata("useJavaSAMFromLib.kt")
        public void testUseJavaSAMFromLib() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/withLib2/useJavaSAMFromLib.kt");
            doTest(fileName);
        }
    }

    @TestMetadata("idea/testData/parameterInfo/withLib3")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class WithLib3 extends AbstractParameterInfoTest {
        public void testAllFilesPresentInWithLib3() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("idea/testData/parameterInfo/withLib3"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true, "sharedLib");
        }

        @TestMetadata("useJavaSAMFromLib.kt")
        public void testUseJavaSAMFromLib() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("idea/testData/parameterInfo/withLib3/useJavaSAMFromLib.kt");
            doTest(fileName);
        }
    }
}
