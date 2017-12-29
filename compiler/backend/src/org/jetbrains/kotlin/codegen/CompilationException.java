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

package org.jetbrains.kotlin.codegen;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.kotlin.diagnostics.DiagnosticUtils;
import org.jetbrains.kotlin.util.ExceptionUtilKt;
import org.jetbrains.kotlin.utils.KotlinExceptionWithAttachments;

public class CompilationException extends KotlinExceptionWithAttachments {
    private final PsiElement element;

    public CompilationException(@NotNull String message, @Nullable Throwable cause, @Nullable PsiElement element) {
        super(ExceptionUtilKt.getExceptionMessage("Back-end (JVM)", message, cause,
                                                  element == null ? null : DiagnosticUtils.atLocation(element)),
              cause);
        this.element = element;

        if (element != null) {
            withAttachment("element.kt", element.getText());
        }
    }

    @Nullable
    public PsiElement getElement() {
        return element;
    }
}
