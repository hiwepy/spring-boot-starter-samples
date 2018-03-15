/*
 * Copyright (c) 2010-2020, vindell (https://github.com/vindell).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package pf4j2.plugin.impl;


import org.apache.commons.lang.StringUtils;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;
import org.pf4j.RuntimeMode;

import pf4j2.plugin.api.annotation.PluginMapping;

@PluginMapping
public class AuthcPluginImpl2 extends Plugin {

    public AuthcPluginImpl2(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public void start() {
    	 System.out.println("AuthcPluginImpl1.start()");
         // for testing the development mode
         if (RuntimeMode.DEVELOPMENT.equals(wrapper.getRuntimeMode())) {
         	System.out.println(StringUtils.upperCase("AuthcPluginImpl1"));
         }
    }
    
    @Override
    public void stop() {
    	System.out.println("AuthcPluginImpl1.stop()");
    }
   
}