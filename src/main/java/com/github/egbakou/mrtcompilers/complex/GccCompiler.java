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

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.github.egbakou.mrtcompilers.util.CmdFileReader.loadPropertiesFile;

/**
 * C and C++ compiler.
 *
 * @author Laurent Egbakou
 * @since 1.0
 */
public class GccCompiler extends CompiledLanguage implements CommonCompilerActions {

    private static final boolean IS_OS_WINDOWS = System.getProperty("os.name").startsWith("Windows");

    /**
     * Default constructor.
     */
    public GccCompiler() {
    }


    @Override
    public String compileWithoutTiming(String fileName) throws InterruptedException, TimeoutException, IOException {
        try {
            String command = loadPropertiesFile()
                    .getString("cc++.compile")
                    .replace("*", fileName)
                    .replace("#", outpoutFileName(fileName));
            this.command(command);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        return super.compileWithoutTiming().trim();
    }


    @Override
    public String compileInTiming(String fileName, TimeUnit timeUnit, Long timeOut) throws InterruptedException, TimeoutException, IOException {
        try {
            String command = loadPropertiesFile()
                    .getString("cc++.compile")
                    .replace("*", fileName)
                    .replace("#", outpoutFileName(fileName));
            this.command(command);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        return super.compileInTiming(timeUnit, timeOut).trim();
    }


    @Override
    public String executeWithoutTiming(String fileName) throws InterruptedException, IOException, TimeoutException {
        this.command(checkOsExecutableFile(fileName));
        return super.executeWithoutTiming().trim();
    }


    @Override
    public String executeInTiming(String fileName, TimeUnit timeUnit, Long timeOut) throws InterruptedException, IOException, TimeoutException {
        this.command(checkOsExecutableFile(fileName));
        return super.executeInTiming(timeUnit, timeOut).trim();
    }


    @Override
    public String compileAndExecuteWithoutTiming(String fileName) throws InterruptedException, TimeoutException, IOException {
        String compileCommand;
        String executeCommand = super.checkOsExecutableFile(fileName);
        String executeResullt = null;
        try {
            compileCommand = loadPropertiesFile()
                    .getString("cc++.compile")
                    .replace("*", fileName)
                    .replace("#", outpoutFileName(fileName));
            executeResullt = super.compileAndExecuteWithoutTiming(compileCommand, executeCommand).trim();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

        return executeResullt;
    }


    @Override
    public String compileAndExecuteIntiming(String fileName, TimeUnit timeUnit, Long timeOut) throws InterruptedException, TimeoutException, IOException {
        String compileCommand;
        String executeCommand = super.checkOsExecutableFile(fileName);
        String executeResullt = null;
        try {
            compileCommand = loadPropertiesFile()
                    .getString("cc++.compile")
                    .replace("*", fileName)
                    .replace("#", outpoutFileName(fileName));
            executeResullt = super.compileAndExecuteIntiming(compileCommand, executeCommand, timeUnit, timeOut).trim();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

        return executeResullt;
    }


    /**
     * Compilefile output builder.
     *
     * @param fileName the filename.
     * @return the output filename.
     */
    private String outpoutFileName(String fileName) {
        if (IS_OS_WINDOWS)
            return fileName.split("\\.|/")[0].concat(".exe");
        else
            return fileName.split("\\.|/")[0];
    }

    /**
     * Set directory where file are stored.
     *
     * @param directory a directory.
     * @return current C or C++ compiler with new directory value.
     */
    @Override
    public GccCompiler directory(File directory) {
        return (GccCompiler) super.directory(directory);
    }
}
