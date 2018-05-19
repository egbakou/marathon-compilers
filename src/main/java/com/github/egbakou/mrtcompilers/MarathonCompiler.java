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
package com.github.egbakou.mrtcompilers;

import java.io.File;

/**
 * Abstract compiler.
 *
 * @author Laurent Egbakou
 * @since 1.0
 */
public class MarathonCompiler {

    /* Directory where file is stored */
    protected File directory;

    /* Command to compile or run file */
    protected String command;


    /**
     * Default constructor.
     */
    public MarathonCompiler() {
    }

    /**
     * @param directory directory where file is stored.
     * @param command   command to compile or run file.
     */
    public MarathonCompiler(File directory, String command) {
        this.directory = directory;
        this.command = command;
    }

    /**
     * Set directory where file are stored.
     *
     * @param directory a directory.
     * @return the compiler with new directory value.
     */
    public MarathonCompiler directory(File directory) {
        this.directory = directory;
        return this;
    }

    protected MarathonCompiler command(String command) {
        this.command = command;
        return this;
    }


    public File getDirectory() {
        return directory;
    }

    public String getCommand() {
        return command;
    }


    @Override
    public String toString() {
        return "MarathonCompiler{" +
                "directory=" + directory +
                ", command='" + command + '\'' +
                '}';
    }
}
