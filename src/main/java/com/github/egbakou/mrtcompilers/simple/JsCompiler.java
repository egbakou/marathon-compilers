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
package com.github.egbakou.mrtcompilers.simple;

import com.github.egbakou.mrtcompilers.behavoirs.InterpretedLanguage;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.github.egbakou.mrtcompilers.util.CmdFileReader.loadPropertiesFile;

/**
 * Java Script Compiler (Nodejs).
 *
 * @author Laurent Egbakou
 * @see InterpretedLanguage
 * @since 1.0
 */
public class JsCompiler extends InterpretedLanguage implements CommonInterpreterActions {

    /**
     * Default Constructor.
     */
    public JsCompiler() {
    }

    @Override
    public String executeWithoutTiming(String fileName)
            throws InterruptedException, IOException, TimeoutException {
        try {
            this.command(loadPropertiesFile().getString("js.run") + " " + fileName);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        return super.executeWithoutTiming().trim();
    }


    @Override
    public String executeInTiming(String fileName, TimeUnit timeUnit, Long timeOut)
            throws InterruptedException, IOException, TimeoutException {
        try {
            this.command(loadPropertiesFile().getString("js.run") + " " + fileName);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        return super.executeInTiming(timeUnit, timeOut);
    }

    /**
     * Set directory where file are stored.
     *
     * @param directory a directory.
     * @return current Js compiler with new directory value.
     */
    @Override
    public JsCompiler directory(File directory) {
        return (JsCompiler) super.directory(directory);
    }
}
