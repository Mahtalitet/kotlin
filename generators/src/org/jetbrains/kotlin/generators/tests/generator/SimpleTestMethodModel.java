/*
 * Copyright 2010-2015 JetBrains s.r.o.
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

package org.jetbrains.kotlin.generators.tests.generator;

import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.kotlin.test.InTextDirectivesUtils;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.utils.Printer;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.jetbrains.kotlin.generators.tests.generator.TestGenerator.TargetBackend;

public class SimpleTestMethodModel implements TestMethodModel {
    private static final String DIRECTIVES_FILE_NAME = "directives.txt";

    @NotNull
    private final File rootDir;
    @NotNull
    private final File file;
    @NotNull
    private final String doTestMethodName;
    @NotNull
    private final Pattern filenamePattern;
    @NotNull
    private final TargetBackend targetBackend;

    public SimpleTestMethodModel(
            @NotNull File rootDir,
            @NotNull File file,
            @NotNull String doTestMethodName,
            @NotNull Pattern filenamePattern,
            @Nullable Boolean checkFilenameStartsLowerCase,
            @NotNull TargetBackend targetBackend
    ) {
        this.rootDir = rootDir;
        this.file = file;
        this.doTestMethodName = doTestMethodName;
        this.filenamePattern = filenamePattern;
        this.targetBackend = targetBackend;

        if (checkFilenameStartsLowerCase != null) {
            char c = file.getName().charAt(0);
            if (checkFilenameStartsLowerCase) {
                assert Character.isLowerCase(c) : "Invalid file name '" + file + "', file name should start with lower-case letter";
            }
            else {
                assert Character.isUpperCase(c) : "Invalid file name '" + file + "', file name should start with upper-case letter";
            }
        }
    }

    @Override
    public void generateBody(@NotNull Printer p) {
        String filePath = KotlinTestUtils.getFilePath(file) + (file.isDirectory() ? "/" : "");
        p.println("String fileName = KotlinTestUtils.navigationMetadata(\"", filePath, "\");");

        if (isIgnored()) {
            p.println("try {");
            p.pushIndent();
        }

        p.println(doTestMethodName, "(fileName);");

        if (isIgnored()) {
            p.println("throw new AssertionError(\"Looks like this test can be unmuted. Remove IGNORE_BACKEND directive for that.\");");
            p.popIndent();
            p.println("}");
            p.println("catch (Throwable ignore) {");
            p.println("}");
        }
    }

    @Override
    public String getDataString() {
        String path = FileUtil.getRelativePath(rootDir, file);
        assert path != null;
        return KotlinTestUtils.getFilePath(new File(path));
    }

    private String textWithDirectives() {
        try {
            String fileText;
            if (file.isDirectory()) {
                File directivesFile = new File(file, DIRECTIVES_FILE_NAME);
                if (!directivesFile.exists()) return "";

                fileText = FileUtil.loadFile(directivesFile);
            }
            else {
                fileText = FileUtil.loadFile(file);
            }
            return fileText;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean shouldBeGenerated() {
        if (targetBackend == TargetBackend.ANY) return true;

        List<String> backends = InTextDirectivesUtils.findLinesWithPrefixesRemoved(textWithDirectives(), "// TARGET_BACKEND: ");
        return backends.isEmpty() || backends.contains(targetBackend.name());
    }

    private boolean isIgnored() {
        if (targetBackend == TargetBackend.ANY) return false;

        List<String> ignoredBackends = InTextDirectivesUtils.findLinesWithPrefixesRemoved(textWithDirectives(), "// IGNORE_BACKEND: ");
        return ignoredBackends.contains(targetBackend.name());
    }

    @NotNull
    @Override
    public String getName() {
        Matcher matcher = filenamePattern.matcher(file.getName());
        boolean found = matcher.find();
        assert found : file.getName() + " isn't matched by regex " + filenamePattern.pattern();
        assert matcher.groupCount() >= 1 : filenamePattern.pattern();
        String extractedName = matcher.group(1);
        assert extractedName != null : "extractedName should not be null: "  + filenamePattern.pattern();

        String unescapedName;
        if (rootDir.equals(file.getParentFile())) {
            unescapedName = extractedName;
        }
        else {
            String relativePath = FileUtil.getRelativePath(rootDir, file.getParentFile());
            unescapedName = relativePath + "-" + StringUtil.capitalize(extractedName);
        }
        return "test" + StringUtil.capitalize(TestGeneratorUtil.escapeForJavaIdentifier(unescapedName));
    }

    @Override
    public void generateSignature(@NotNull Printer p) {
        TestMethodModel.DefaultImpls.generateSignature(this, p);
    }
}
