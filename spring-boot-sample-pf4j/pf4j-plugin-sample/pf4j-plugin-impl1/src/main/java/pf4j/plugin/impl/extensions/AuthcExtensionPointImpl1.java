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
package pf4j.plugin.impl.extensions;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import pf4j.plugin.api.AuthcExtensionPointAdepter;
import pf4j.plugin.api.annotation.ExtensionMapping;
import pf4j.plugin.impl.utils.Constant;
import ro.fortsoft.pf4j.Extension;

@Extension
@ExtensionMapping(id = Constant.STANDARD_EXTENSION_ID, title = "认证接口实现1")
public class AuthcExtensionPointImpl1 extends AuthcExtensionPointAdepter {

	private final String appKey = "";
	private final String appSecret = "";
	private final String version = "1.0";
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public void handleRequestParams(Map<String, Object> par, HttpServletRequest req) {

		par.put("appKey", appKey);
		par.put("appSecret", appSecret);
		par.put("version", version);
		par.put("time", sdf.format(System.currentTimeMillis()));

	}

}
