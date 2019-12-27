/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
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
package pf4j2.plugin.api;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.pf4j.PluginException;

public class AuthcExtensionPointAdepter implements AuthcExtensionPoint{

	@Override
	public String getToken(Map<String, Object> par, HttpServletRequest req) throws PluginException{
		return null;
	}

	@Override
	public void handleHeaderParams(Map<String, Object> par, HttpServletRequest req) throws PluginException {
	}

	@Override
	public void handleRequestParams(Map<String, Object> par, HttpServletRequest req) throws PluginException {
	}

	@Override
	public Object handleResult(Object res) throws PluginException {
		return res;
	}

}
