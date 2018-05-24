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
package com.github.egbakou.mrtcompilers.simple;

import com.github.egbakou.mrtcompilers.complex.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Test file.
 *
 * @author Laurent Egbakou
 * @since 1.0
 */
public class AllTest {
    /*String out = new PythonCompiler()
                .directory(new File("/home/laurent/Documents"))
                .runWithoutTiming("p001.py");

        System.out.println(out);/
    /*String out = new JsCompiler()
                .directory(new File("/home/laurent/Documents"))
                .runWithoutTiming("index.js");

        System.out.println(out);*/
    /*String out = new PascalCompiler()
                .directory(new File("/home/laurent/Documents"))
                .compileAndRunWithoutTiming("euler1.pas");

        System.out.println(out);*/
    /*String out = new JavaCompiler()
                .directory(new File("/home/laurent/Documents"))
                .compileAndRunWithoutTiming("Main.java");

        System.out.println(out);*/
    /*String out = new GccCompiler()
                .directory(new File("/home/laurent/Documents"))
                .compileAndRunWithoutTiming("problem1.c");

        System.out.println(out);*/
    /*
    String out = new CSharpCompiler()
                .directory(new File("/home/laurent/Documents"))
                .compileAndRunInTiming("euler1.cs",TimeUnit.SECONDS,3L);

        System.out.println(out);
     */
    /*
    String out = new KotlinCompiler()
                .directory(new File("/home/laurent/Documents"))
                .compileAndRunWithoutTiming("Problem001.kt");

        System.out.println(out);
     */

    public static void main(String[] args) throws
            InterruptedException, IOException, TimeoutException {

        String out = new KotlinCompiler()
                .directory(new File("/home/laurent/Documents"))
                .compileAndRunInTiming("Problem001.kt",TimeUnit.SECONDS,3L);

        System.out.println(out);
    }
}
