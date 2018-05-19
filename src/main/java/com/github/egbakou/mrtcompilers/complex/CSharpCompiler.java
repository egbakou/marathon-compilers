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
package com.github.egbakou.mrtcompilers.complex;

import com.github.egbakou.mrtcompilers.behavoirs.CompiledLanguage;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.github.egbakou.mrtcompilers.util.CmdFileReader.loadPropertiesFile;

/**
 * C# compiler.
 *
 * @author Laurent Egbakou
 * @since 1.0
 */
public class CSharpCompiler extends CompiledLanguage implements CommonCompilerActions {


    /**
     * Default Constructor.
     */
    public CSharpCompiler() {
    }

    /**
     * Compile file without timing.
     *
     * @param fileName file name with extension.
     * @return the result of the execution.
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     *                              and the thread is interrupted, either before or during the activity.
     * @throws IOException          signals that an I/O exception of some sort has occurred.
     * @throws TimeoutException     exception thrown when a blocking operation times out.
     */
    @Override
    public String compileWithoutTiming(String fileName) throws InterruptedException, TimeoutException, IOException {
        try {
            this.command(loadPropertiesFile().getString("cSharp.compile") + " " + fileName);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        return super.compileWithoutTiming().trim();
    }

    /**
     * Compile file with timing constraints.
     *
     * @param fileName file name with extension.
     * @param timeUnit a represents time durations at a given unit of
     *                 granularity and provides utility methods to convert across units,
     *                 and to perform timing and delay operations in these units.
     * @param timeOut  timeout for running a process. If the process is running too
     *                 long a {@link TimeoutException} is thrown and the process is destroyed.
     * @return the result of the execution.
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     *                              and the thread is interrupted, either before or during the activity.
     * @throws IOException          signals that an I/O exception of some sort has occurred.
     * @throws TimeoutException     exception thrown when a blocking operation times out.
     */
    @Override
    public String compileInTiming(String fileName, TimeUnit timeUnit, Long timeOut) throws InterruptedException, TimeoutException, IOException {
        try {
            this.command(loadPropertiesFile().getString("cSharp.compile") + " " + fileName);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        return super.compileInTiming(timeUnit, timeOut).trim();
    }

    /**
     * Execute file without timing. This method should be avoided
     * to prevent threads from blocking the execution of the file.
     *
     * @param fileName file name with extension.
     * @return the result of the execution.
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     *                              and the thread is interrupted, either before or during the activity.
     * @throws IOException          signals that an I/O exception of some sort has occurred.
     * @throws TimeoutException     exception thrown when a blocking operation times out.
     */
    @Override
    public String executeWithoutTiming(String fileName) throws InterruptedException, IOException, TimeoutException {
        this.command(checkOsExecutableFile(fileName));
        return super.executeWithoutTiming().trim();
    }

    /**
     * Execute file with timing constraints. This method is to be recommended.
     *
     * @param fileName file name with extension.
     * @param timeUnit a represents time durations at a given unit of
     *                 granularity and provides utility methods to convert across units,
     *                 and to perform timing and delay operations in these units.
     * @param timeOut  timeout for running a process. If the process is running too
     *                 long a {@link TimeoutException} is thrown and the process is destroyed.
     * @return the result of the execution.
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     *                              and the thread is interrupted, either before or during the activity.
     * @throws IOException          signals that an I/O exception of some sort has occurred.
     * @throws TimeoutException     exception thrown when a blocking operation times out.
     */
    @Override
    public String executeInTiming(String fileName, TimeUnit timeUnit, Long timeOut) throws InterruptedException, IOException, TimeoutException {
        String compileCommand;
        String executeCommand = super.checkOsExecutableFile(fileName);
        String executeResullt = null;
        try {
            compileCommand = loadPropertiesFile().getString("cSharp.run") + " " + fileName;
            executeResullt = super.compileAndExecuteIntiming(compileCommand, executeCommand, timeUnit, timeOut).trim();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

        return executeResullt;
    }

    /**
     * Compile and execute file without timing constraints.
     *
     * @param fileName file name to compile.
     * @return the result of the execution.
     */
    @Override
    public String compileAndExecuteWithoutTiming(String fileName) throws InterruptedException, TimeoutException, IOException {
        String compileCommand;
        String executeCommand = super.checkOsExecutableFile(fileName);
        String executeResullt = null;
        try {
            compileCommand = loadPropertiesFile().getString("cSharp.run") + " " + fileName;
            executeResullt = super.compileAndExecuteWithoutTiming(compileCommand, executeCommand).trim();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }


        return executeResullt;
    }

    /**
     * Compile and execute file with timing constraints.
     *
     * @param fileName file name to compile first.
     * @param timeUnit a represents time durations at a given unit of
     *                 granularity and provides utility methods to convert across units,
     *                 and to perform timing and delay operations in these units.
     * @param timeOut  timeout for running a process. If the process is running too
     *                 long a {@link TimeoutException} is thrown and the process is destroyed.
     * @return the result of the execution.
     */
    @Override
    public String compileAndExecuteIntiming(String fileName, TimeUnit timeUnit, Long timeOut) throws InterruptedException, TimeoutException, IOException {
        return null;
    }
}
