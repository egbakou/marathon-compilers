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

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Compiler strategy.
 *
 * @author Laurent Egbakou
 * @since 1.0
 */
public interface Compiler {

    /**
     * Compile file with timing constraints.
     *
     * @param compiler the compiler used to compile the files.
     * @param timeUnit a represents time durations at a given unit of
     *                 granularity and provides utility methods to convert across units,
     *                 and to perform timing and delay operations in these units.
     * @param timeOut  timeout for running a process. If the process is running too
     *                 long a {@link TimeoutException} is thrown and the process is destroyed.
     * @return the compilation result.
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     *                              and the thread is interrupted, either before or during the activity.
     * @throws TimeoutException     exception thrown when a blocking operation times out.
     * @throws IOException          signals that an I/O exception of some sort has occurred.
     */
    String compileInTiming(MarathonCompiler compiler, TimeUnit timeUnit, Long timeOut)
            throws InterruptedException, TimeoutException, IOException;

    /**
     * Compile file without timing constraints.
     *
     * @param compiler the compiler used to compile the files.
     * @return the compilation result.
     * @throws InterruptedException thrown when a thread is waiting, sleeping, or otherwise occupied,
     *                              and the thread is interrupted, either before or during the activity.
     * @throws TimeoutException     exception thrown when a blocking operation times out.
     * @throws IOException          signals that an I/O exception of some sort has occurred.
     */
    String compileWithoutTiming(MarathonCompiler compiler)
            throws InterruptedException, TimeoutException, IOException;

}
