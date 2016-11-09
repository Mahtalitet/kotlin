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

package org.jetbrains.kotlin.codegen;

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
@TestMetadata("compiler/testData/checkLocalVariablesTable")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class CheckLocalVariablesTableTestGenerated extends AbstractCheckLocalVariablesTableTest {
    public void testAllFilesPresentInCheckLocalVariablesTable() throws Exception {
        KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/testData/checkLocalVariablesTable"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
    }

    @TestMetadata("catchClause.kt")
    public void testCatchClause() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/checkLocalVariablesTable/catchClause.kt");
        doTest(fileName);
    }

    @TestMetadata("copyFunction.kt")
    public void testCopyFunction() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/checkLocalVariablesTable/copyFunction.kt");
        doTest(fileName);
    }

    @TestMetadata("inlineLambdaWithItParam.kt")
    public void testInlineLambdaWithItParam() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/checkLocalVariablesTable/inlineLambdaWithItParam.kt");
        doTest(fileName);
    }

    @TestMetadata("inlineLambdaWithParam.kt")
    public void testInlineLambdaWithParam() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/checkLocalVariablesTable/inlineLambdaWithParam.kt");
        doTest(fileName);
    }

    @TestMetadata("inlineSimple.kt")
    public void testInlineSimple() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/checkLocalVariablesTable/inlineSimple.kt");
        doTest(fileName);
    }

    @TestMetadata("inlineSimpleChain.kt")
    public void testInlineSimpleChain() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/checkLocalVariablesTable/inlineSimpleChain.kt");
        doTest(fileName);
    }

    @TestMetadata("itInLambda.kt")
    public void testItInLambda() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/checkLocalVariablesTable/itInLambda.kt");
        doTest(fileName);
    }

    @TestMetadata("itInReturnedLambda.kt")
    public void testItInReturnedLambda() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/checkLocalVariablesTable/itInReturnedLambda.kt");
        doTest(fileName);
    }

    @TestMetadata("jvmOverloads.kt")
    public void testJvmOverloads() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/checkLocalVariablesTable/jvmOverloads.kt");
        doTest(fileName);
    }

    @TestMetadata("kt11117.kt")
    public void testKt11117() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/checkLocalVariablesTable/kt11117.kt");
        doTest(fileName);
    }

    @TestMetadata("lambdaAsVar.kt")
    public void testLambdaAsVar() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/checkLocalVariablesTable/lambdaAsVar.kt");
        doTest(fileName);
    }

    @TestMetadata("localFun.kt")
    public void testLocalFun() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/checkLocalVariablesTable/localFun.kt");
        doTest(fileName);
    }
}
