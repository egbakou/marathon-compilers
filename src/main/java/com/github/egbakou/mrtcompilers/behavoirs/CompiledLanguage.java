/*
 * Copyright (C) 2018 Egbakou <laurent@dorkenooconsulting.com>
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
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.github.egbakou.mrtcompilers.util.CmdFileReader.loadPropertiesFile;

/**
 * Entity using Compiler strategy algorithms.
 * Used by compiled programming lanaguages.
 *
 * @author Laurent Egbakou
 * @see MarathonCompiler
 * @since 1.0
 */
public class CompiledLanguage extends MarathonCompiler {

    private static final boolean IS_OS_WINDOWS = System.getProperty("os.name").startsWith("Windows");
    protected Compiler compiler = new CompilerTool();
    protected Interpreter interpreter = new InterpreterTool();

    /**
     * Default Constructor.
     */
    public CompiledLanguage() {

    }

    public CompiledLanguage(File directory, String command) {
        super(directory, command);
    }

    public CompiledLanguage(File directory, String command, Compiler compiler, Interpreter interpreter) {
        super(directory, command);
        this.compiler = compiler;
        this.interpreter = interpreter;
    }

    /**
     * Compile file without timing constraints.
     *
     * @return the compilation result.
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     *                              and the thread is interrupted, either before or during the activity.
     * @throws TimeoutException     exception thrown when a blocking operation times out.
     * @throws IOException          signals that an I/O exception of some sort has occurred.
     */
    protected String compileWithoutTiming() throws InterruptedException, TimeoutException, IOException {
        return this.compiler.compileWithoutTiming(this);
    }

    /**
     * Compile file with timing constraints.
     *
     * @param timeUnit a represents time durations at a given unit of
     *                 granularity and provides utility methods to convert across units,
     *                 and to perform timing and delay operations in these units.
     * @param timeOut  imeout for running a process. If the process is running too
     *                 long a {@link TimeoutException} is thrown and the process is destroyed.
     * @return the compilation result.
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     *                              and the thread is interrupted, either before or during the activity.
     * @throws TimeoutException     exception thrown when a blocking operation times out.
     * @throws IOException          signals that an I/O exception of some sort has occurred.
     */
    protected String compileInTiming(TimeUnit timeUnit, Long timeOut)
            throws InterruptedException, TimeoutException, IOException {
        return this.compiler.compileInTiming(this, timeUnit, timeOut);
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
    protected String executeWithoutTiming()
            throws InterruptedException, TimeoutException, IOException {
        return interpreter.executeWithoutTiming(this);
    }

    /**
     * Run file with timing constraints.
     *
     * @param timeUnit a represents time durations at a given unit of
     *                 granularity and provides utility methods to convert across units,
     *                 and to perform timing and delay operations in these units.
     * @param timeOut  imeout for running a process. If the process is running too
     *                 long a {@link TimeoutException} is thrown and the process is destroyed.
     * @return the output result.
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     *                              and the thread is interrupted, either before or during the activity.
     * @throws TimeoutException     exception thrown when a blocking operation times out.
     * @throws IOException          signals that an I/O exception of some sort has occurred.
     */
    protected String executeInTiming(TimeUnit timeUnit, Long timeOut)
            throws InterruptedException, TimeoutException, IOException {
        return interpreter.executeInTiming(this, timeUnit, timeOut);
    }


    /**
     * Compile and Run file simultaneously without timing constraints.
     * Errors can occur if there are compilation or execution issues.
     *
     * @param compileCommand shell command used to compile file first.
     * @param executecommand shell command used to excute  file after compilation.
     * @return the output result.
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     *                              and the thread is interrupted, either before or during the activity.
     * @throws TimeoutException     exception thrown when a blocking operation times out.
     * @throws IOException          signals that an I/O exception of some sort has occurred.
     */
    protected String compileAndExecuteWithoutTiming(String compileCommand, String executecommand)
            throws InterruptedException, IOException, TimeoutException {
        this.command(compileCommand);
        compiler.compileWithoutTiming(this);

        this.command(executecommand);
        return interpreter.executeWithoutTiming(this);
    }


    /**
     * Compile and Run file simultaneously with timing constraints.
     * Errors can occur if there are compilation or execution issues.
     *
     * @param compileCommand shell command used to compile file first.
     * @param executecommand shell command used to excute  file after compilation.
     * @param timeUnit       a represents time durations at a given unit of
     *                       granularity and provides utility methods to convert across units,
     *                       and to perform timing and delay operations in these units.
     * @param timeOut        imeout for running a process. If the process is running too
     *                       long a {@link TimeoutException} is thrown and the process is destroyed.
     * @return the execution result.
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     *                              and the thread is interrupted, either before or during the activity.
     * @throws TimeoutException     exception thrown when a blocking operation times out.
     * @throws IOException          signals that an I/O exception of some sort has occurred.
     */
    protected String compileAndExecuteIntiming(String compileCommand, String executecommand, TimeUnit timeUnit, Long timeOut)
            throws InterruptedException, IOException, TimeoutException {
        this.command(compileCommand);
        compiler.compileWithoutTiming(this);

        this.command(executecommand);
        return interpreter.executeInTiming(this, timeUnit, timeOut);
    }

    /**
     * check OS type (Windows, Unixn Mac, ...) to build execution command.
     *
     * @param fileName filename to execute.
     * @return execution command containing the file name
     */
    protected String checkOsExecutableFile(String fileName) {
        // command example is: euler1.exe
        String command = null;
        if (IS_OS_WINDOWS) command = fileName.split("\\.|/")[0].concat(".exe");
        else try {
            // command example is: ./euler1
            command = loadPropertiesFile().getString("langage.run.linux").concat(fileName.split("\\.|/")[0]);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        return command;
    }

    protected Compiler getCompiler() {
        return compiler;
    }

    protected void setCompiler(Compiler compiler) {
        this.compiler = compiler;
    }

    protected Interpreter getInterpreter() {
        return interpreter;
    }

    protected void setInterpreter(Interpreter interpreter) {
        this.interpreter = interpreter;
    }
}
