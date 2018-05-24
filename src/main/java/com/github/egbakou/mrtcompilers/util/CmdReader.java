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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Reader of cmd map which contain specific compilation
 * and runnning commands.
 *
 * @author Laurent Egbakou
 * @since 1.0
 */
public class CmdReader {

    public static Map<String, String> loadAllCommands() {

        Map<String, String> cmdMap = new HashMap<>();
        cmdMap.put("java.compile", "javac");
        cmdMap.put("java.run", "java");
        cmdMap.put("kotlin.compile", "kotlinc * -include-runtime -d #");
        cmdMap.put("kotlin.run", "java -jar #");
        cmdMap.put("python.run", "python");
        cmdMap.put("cc++.compile", "g++ * -o #");
        cmdMap.put("langage.run.linux", "./");
        cmdMap.put("cSharp.compile", "csc");
        cmdMap.put("cSharp.run", "mono");
        cmdMap.put("php.run", "php");
        cmdMap.put("pascal.compile", "fpc");
        cmdMap.put("js.run", "node");

        return cmdMap;

    }
}
