/*
 * Copyright (c) 2018, vindell (https://github.com/vindell).
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
package io.swagger2.spring.boot.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger2.spring.boot.dao.UserDto;
import io.swagger2.spring.boot.setup.config.HttpStatus;

@RestController
@RequestMapping(value = "/users/") // 通过这里配置使下面的映射都在/users下，可去除
public class UserController {

	static Map<Long, UserDto> users = Collections.synchronizedMap(new HashMap<Long, UserDto>());

	@ApiOperation(value = "获取用户列表", notes = "")
	@RequestMapping(value = { "list" }, method = RequestMethod.GET)
	public List<UserDto> getUserList() {
		List<UserDto> r = new ArrayList<UserDto>(users.values());
		return r;
	}

	@ApiOperation(value = "创建用户", notes = "根据User对象创建用户", httpMethod = "POST")
	@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "UserDto")
	@RequestMapping(value = "new", method = RequestMethod.POST)
	public String postUser(@RequestBody UserDto user) {
		users.put(user.getId(), user);
		return "success";
	}

	@ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public UserDto getUser(@PathVariable Long id) {
		return users.get(id);
	}

	@ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "UserDto") 
	})
	@RequestMapping(value = "put/{id}", method = RequestMethod.PUT)
	public String putUser(@PathVariable Long id, @RequestBody UserDto user) {
		UserDto u = users.get(id);
		u.setName(user.getName());
		u.setAge(user.getAge());
		users.put(id, u);
		return "success";
	}

	@ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
	@RequestMapping(value = "del/{id}", method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable Long id) {
		users.remove(id);
		return "success";
	}

	@ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
	@ApiResponses({ 
		@ApiResponse(code = HttpStatus.SC_OK, message = "操作成功"),
		@ApiResponse(code = HttpStatus.SC_UNAUTHORIZED, message = "请求要求身份验证"),
		@ApiResponse(code = HttpStatus.SC_NOT_FOUND, message = "请求资源不存在"),
		@ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "服务器内部异常"),
		@ApiResponse(code = HttpStatus.SC_FORBIDDEN, message = "权限不足") 
	})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", dataType = "Long", name = "id", value = "信息id", required = true) 
	})
	@RequestMapping(value = "/remove.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object remove(Long id) {

		return null;
	}

}