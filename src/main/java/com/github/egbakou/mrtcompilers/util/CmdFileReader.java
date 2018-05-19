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

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

/**
 * Reader of cmd.properties file which contain specific compilation
 * and runnning commands.
 *
 * @author Laurent Egbakou
 * @since 1.0
 */
public class CmdFileReader {

    private static PropertiesConfiguration config;

    /**
     * Load a file which contain specific compilation and runnning commands.
     *
     * @return Properties Configuration.
     * @throws ConfigurationException
     */
    public static PropertiesConfiguration loadPropertiesFile() throws ConfigurationException {
        Configurations configs = new Configurations();

        config = configs.properties(
                "com/github/egbakou/mrtcompilers/util/cmd.properties");

        return config;
    }
}
