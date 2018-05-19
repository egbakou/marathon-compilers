/*
 * Copyright (C) 2018 LionCoding <laurent@dorkenooconsulting.com>
 * Contains fragments of code from zt-exec, rights owned
 * by Apache Software Foundation (ASF).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.egbakou.mrtcompilers.behavoirs;

import com.github.egbakou.mrtcompilers.MarathonCompiler;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Entity using Interpreter strategy algorithms.
 *
 * @author Laurent Egbakou
 * @since 1.0
 */
public class InterpretedLanguage extends MarathonCompiler {

    private Interpreter interpreter = new InterpreterTool();

    /**
     * Default Constructor.
     */
    public InterpretedLanguage() {

    }

    public InterpretedLanguage(File directory, String command) {
        super(directory, command);
    }

    protected String executeWithoutTiming() throws InterruptedException, TimeoutException, IOException {
        return interpreter.executeWithoutTiming(this);
    }

    protected String executeInTiming(TimeUnit timeUnit, Long timeOut) throws InterruptedException, TimeoutException, IOException {
        return interpreter.executeInTiming(this, timeUnit, timeOut);
    }

    protected Interpreter getInterpreter() {
        return interpreter;
    }

    protected void setInterpreter(Interpreter interpreter) {
        this.interpreter = interpreter;
    }
}
