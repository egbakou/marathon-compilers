/*
 * Copyright (C) 2018 strategy <laurent@dorkenooconsulting.com>
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
 * Used by interpreted programming lanaguages.
 *
 * @author Laurent Egbakou
 * @since 1.0
 * @see MarathonCompiler
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

    /**
     * Run file without timing constraints.
     *
     * @return the output result.
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     *                              and the thread is interrupted, either before or during the activity.
     * @throws TimeoutException     exception thrown when a blocking operation times out.
     * @throws IOException          signals that an I/O exception of some sort has occurred.
     */
    protected String runWithoutTiming() throws InterruptedException, TimeoutException, IOException {
        return interpreter.runWithoutTiming(this);
    }

    /**
     * Run file with timing constraints.
     *
     * @param timeUnit a represents time durations at a given unit of
     *                 granularity and provides utility methods to convert across units,
     *                 and to perform timing and delay operations in these units.
     * @param timeOut  timeout for running a process. If the process is running too
     *                 long a {@link TimeoutException} is thrown and the process is destroyed.
     * @return the output result.
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     *                              and the thread is interrupted, either before or during the activity.
     * @throws TimeoutException     exception thrown when a blocking operation times out.
     * @throws IOException          signals that an I/O exception of some sort has occurred.
     */
    protected String runInTiming(TimeUnit timeUnit, Long timeOut)
            throws InterruptedException, TimeoutException, IOException {
        return interpreter.runInTiming(this, timeUnit, timeOut);
    }

    protected Interpreter getInterpreter() {
        return interpreter;
    }

    protected void setInterpreter(Interpreter interpreter) {
        this.interpreter = interpreter;
    }
}
