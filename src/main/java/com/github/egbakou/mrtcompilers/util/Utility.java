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
package com.github.egbakou.mrtcompilers.util;

/**
 * Utility, Contain common method used by many class.
 *
 * @author Laurent Egbakou
 * @since 1.0
 */
public class Utility {

    private static final boolean IS_OS_WINDOWS = System.getProperty("os.name").startsWith("Windows");

    /**
     * Compilefile output builder.
     *
     * @param fileName the filename.
     * @return the output filename.
     */
    public static String outputFileName(String fileName) {
        if (IS_OS_WINDOWS)
            return fileName.split("\\.|/")[0].concat(".exe");
        else
            return fileName.split("\\.|/")[0];
    }
}
