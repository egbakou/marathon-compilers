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
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.github.egbakou.mrtcompilers.util.CmdFileReader.loadPropertiesFile;

/**
 * Entity using Compiler strategy algorithms.
 *
 * @author Laurent Egbakou
 * @since 1.0
 */
public class CompiledLanguage extends MarathonCompiler {

    protected Compiler compiler = new CompilerTool();

    protected Interpreter interpreter = new InterpreterTool();

    private static final boolean IS_OS_WINDOWS = System.getProperty("os.name").startsWith("Windows");

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

    protected String compileWithoutTiming() throws InterruptedException, TimeoutException, IOException {
        return this.compiler.compileWithoutTiming(this);
    }

    protected String compileInTiming(TimeUnit timeUnit, Long timeOut) throws InterruptedException, TimeoutException, IOException {
        return this.compiler.compileInTiming(this, timeUnit, timeOut);
    }

    protected String executeWithoutTiming() throws InterruptedException, TimeoutException, IOException {
        return interpreter.executeWithoutTiming(this);
    }

    protected String executeInTiming(TimeUnit timeUnit, Long timeOut) throws InterruptedException, TimeoutException, IOException {
        return interpreter.executeInTiming(this, timeUnit, timeOut);
    }


    protected String compileAndExecuteWithoutTiming(String compileCommand, String executecommand) throws InterruptedException, IOException, TimeoutException {
        this.command(compileCommand);
        compiler.compileWithoutTiming(this);

        this.command(executecommand);
        return interpreter.executeWithoutTiming(this);
    }

    protected String compileAndExecuteIntiming(String compileCommand, String executecommand, TimeUnit timeUnit, Long timeOut) throws InterruptedException, IOException, TimeoutException {
        this.command(compileCommand);
        compiler.compileWithoutTiming(this);

        this.command(executecommand);
        return interpreter.executeInTiming(this, timeUnit, timeOut);
    }

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
