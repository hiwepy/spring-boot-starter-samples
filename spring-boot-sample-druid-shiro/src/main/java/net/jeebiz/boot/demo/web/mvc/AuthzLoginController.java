/** 
 * Copyright (C) 2018 Jeebiz (http://jeebiz.net).
 * All Rights Reserved. 
 */
package net.jeebiz.boot.demo.web.mvc;

import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.biz.authc.exception.IncorrectCaptchaException;
import org.apache.shiro.biz.authc.exception.InvalidAccountException;
import org.apache.shiro.biz.authc.exception.InvalidCaptchaException;
import org.apache.shiro.biz.authc.exception.NoneCaptchaException;
import org.apache.shiro.biz.authc.exception.NoneRoleException;
import org.apache.shiro.biz.utils.SubjectUtils;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.jeebiz.boot.api.annotation.BusinessLog;
import net.jeebiz.boot.api.annotation.BusinessType;
import net.jeebiz.boot.api.utils.Constants;
import net.jeebiz.boot.api.utils.ResultUtils;
import net.jeebiz.boot.api.webmvc.BaseController;
import net.jeebiz.boot.demo.dao.entities.AuthzLoginModel;
import net.jeebiz.boot.demo.service.IAuthzLoginService;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 权限管理：用户登录
 */
@Api(tags = "权限管理：用户登录（Ok）")
@Controller
@RequestMapping(value = "/authz/login/")
public class AuthzLoginController extends BaseController {

	private static final String DEFAULT_ERROR_KEY_ATTRIBUTE_NAME = "shiroLoginFailure";

	@Autowired
	private IAuthzLoginService authzLoginService;

	/**
	 * 用户登录界面
	 * 
	 * @param request
	 * @return
	 */
	@ApiIgnore
	@RequestMapping(value = "index")
	public String index(HttpServletRequest request) {

		boolean authenticated = SubjectUtils.isAuthenticated();
		/**
		 * 如果用户已登录，直接转发到首页
		 */
		if (authenticated) {
			return "redirect:/index";
		}
		return "html/authz/rbac0/login";
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "获取RSA公钥", notes = "用于登录功能获取RSA公钥")
	@RequestMapping(value = "publicKey", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public JSONObject getPublicKey(@ApiIgnore HttpServletRequest request) throws Exception {

		RSAPublicKey publicKey = getAuthzLoginService().genPublicKey(request);

		byte[] modulus = publicKey.getModulus().toByteArray();
		byte[] exponent = publicKey.getPublicExponent().toByteArray();

		JSONObject json = new JSONObject();
		json.put("modulus", Base64.encodeToString(modulus));
		json.put("exponent", Base64.encodeToString(exponent));
		
		return json;
	}

	@ApiOperation(value = "login:stateful", notes = "用户登录（有状态会话）")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "username", required = true, value = "登录账户", dataType = "String"),
		@ApiImplicitParam(name = "password", required = true, value = "登录密码", dataType = "String"),
		@ApiImplicitParam(name = "captcha", value = "验证码", dataType = "String") 
	})
	@BusinessLog(module = Constants.AUTHZ_LOGIN, business = "用户登录", opt = BusinessType.LOGIN)
	@RequestMapping(value = "stateful", method = {RequestMethod.POST, RequestMethod.GET})
	public String stateful(@RequestParam(required = false) String username, 
			@RequestParam(required = false) String password, @RequestParam(required = false) String captcha,
			@ApiIgnore HttpServletRequest request, @ApiIgnore Model model) {
		
		
		// 如果有请求参数forceLogout表示是管理员强制退出的，在界面上显示相应的信息。
		if(request.getParameter("forceLogout") != null) {  
			model.addAttribute("message", "您已经被管理员强制退出，请重新登录");
			model.addAttribute("forceLogout", "true");
		} 
		
		// 如果用户已登录，直接转发到首页
		if (SubjectUtils.isAuthenticated()) {
			return "redirect:/index";
		}

		String ERROR_VALUE = (String) request.getAttribute(DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		// 已经超出了重试限制，需要进行提醒
		if (StringUtils.equals(NoneCaptchaException.class.getName(), ERROR_VALUE)) {
			model.addAttribute("message", getMessage("login.captcha.required"));
			model.addAttribute("captcha", "required");
		}
		// 验证码错误
		else if (StringUtils.equals(IncorrectCaptchaException.class.getName(), ERROR_VALUE)) {
			model.addAttribute("message", getMessage("login.captcha.incorrect"));
			model.addAttribute("captcha", "required");
		}
		// 验证码失效
		else if (StringUtils.equals(InvalidCaptchaException.class.getName(), ERROR_VALUE)) {
			model.addAttribute("message", getMessage("login.captcha.invalid"));
			model.addAttribute("captcha", "required");
		}
		// 账号或密码为空
		else if (StringUtils.equals(UnknownAccountException.class.getName(), ERROR_VALUE)) {
			model.addAttribute("message", getMessage("login.account.empty"));
		}
		// 账户或密码错误
		else if (StringUtils.equals(InvalidAccountException.class.getName(), ERROR_VALUE)) {
			model.addAttribute("message", getMessage("login.account.invalid"));
		}
		// 账户没有启用
		else if (StringUtils.equals(DisabledAccountException.class.getName(), ERROR_VALUE)) {
			model.addAttribute("message", getMessage("login.account.disabled"));
		}
		// 该用户无所属角色，禁止登录
		else if (StringUtils.equals(NoneRoleException.class.getName(), ERROR_VALUE)) {
			model.addAttribute("message", getMessage("login.account.nonerole"));
		}
		else if(StringUtils.isNotEmpty(ERROR_VALUE)) {
        	model.addAttribute("message", "Authentication Failure.");
        }
		
		/*String kick = request.getParameter("kickout");
		if (StringUtils.equals("1", kick)) {
			model.addAttribute("message", getMessage("login.account.kickout"));
		}*/

		return "html/authz/rbac0/login";
	}
	
	@ApiIgnore
	@GetMapping("forget")
	public String forget() {
		return "html/authz/rbac0/forget";
	}
	
	@ApiOperation(value = "login:stateless", notes = "用户登录（无状态会话）")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "username", required = true, value = "登录账户", dataType = "String"),
		@ApiImplicitParam(name = "password", required = true, value = "登录密码", dataType = "String"),
		@ApiImplicitParam(name = "captcha", value = "验证码", dataType = "String") 
	})
	@BusinessLog(module = Constants.AUTHZ_LOGIN, business = "用户登录", opt = BusinessType.LOGIN)
	@RequestMapping(value = "stateless", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Object stateless(@RequestParam String username, @RequestParam String password, String captcha,
			@ApiIgnore HttpServletRequest request, @ApiIgnore Model model) {
		
		// 直接响应登录成功的提醒
		if (SubjectUtils.isAuthenticated()) {
			// 响应成功状态信息
			return ResultUtils.success("Login Success.");
		}

		// 响应成功状态信息
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("status", STATUS_FAIL);
			
		String ERROR_VALUE = (String) request.getAttribute(DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		
		// 已经超出了重试限制，需要进行提醒
		if (StringUtils.equals(NoneCaptchaException.class.getName(), ERROR_VALUE)) {
			data.put("message", getMessage("login.captcha.required"));
			data.put("captcha", "required");
		}
		// 验证码错误
		else if (StringUtils.equals(IncorrectCaptchaException.class.getName(), ERROR_VALUE)) {
			data.put("message", getMessage("login.captcha.incorrect"));
			data.put("captcha", "required");
		}
		// 验证码失效
		else if (StringUtils.equals(InvalidCaptchaException.class.getName(), ERROR_VALUE)) {
			data.put("message", getMessage("login.captcha.invalid"));
			data.put("captcha", "required");
		}
		// 账号或密码为空
		else if (StringUtils.equals(UnknownAccountException.class.getName(), ERROR_VALUE)) {
			data.put("message", getMessage("login.account.empty"));
		}
		// 账户或密码错误
		else if (StringUtils.equals(InvalidAccountException.class.getName(), ERROR_VALUE)) {
			data.put("message", getMessage("login.account.invalid"));
		}
		// 账户没有启用
		else if (StringUtils.equals(DisabledAccountException.class.getName(), ERROR_VALUE)) {
			data.put("message", getMessage("login.account.disabled"));
		}
		// 该用户无所属角色，禁止登录
		else if (StringUtils.equals(NoneRoleException.class.getName(), ERROR_VALUE)) {
			data.put("message", getMessage("login.account.nonerole"));
		}
		else if(StringUtils.isNotEmpty(ERROR_VALUE)) {
        	data.put("message", "Authentication Failure.");
        }
		
		return data;
	}
	
	@ApiOperation(value = "switchRole", notes = "切换角色")
	@ApiImplicitParams({ @ApiImplicitParam(name = "roleid", value = "角色ID", dataType = "String") })
	//@BusinessLog(module = Constants.Module.LOGIN, business = "切换角色", opt = BusinessType.LOGIN)
	@RequestMapping(value = "switchRole", method = {RequestMethod.POST, RequestMethod.GET})
	public String switchRole(String roleid) {
		try {

			AuthzLoginModel principal = SubjectUtils.getPrincipal(AuthzLoginModel.class);
			Session session = SubjectUtils.getSession();
			
			
			//SubjectUtils.getSubject().runAs(principals);
			
			if (StringUtils.isNotBlank(roleid) && (!StringUtils.equals(roleid, principal.getRoleid()))) {
				/*// 切换当前的角色信息
				getUser().setJsdm(jsdm);

				// 刷新shiro缓存
				AccountRealm shiroRealm = ServiceFactory.getService(DefaultAccountRealm.class);
				shiroRealm.clearAuthorizationCache();*/
				// 刷新shiro缓存
				// 删除用户数据范围标识
				session.removeAttribute("");
			}
		} catch (Exception e) {
			logException(this, e);
		}
		return "redirect:/index";
	}

	public IAuthzLoginService getAuthzLoginService() {
		return authzLoginService;
	}

	public void setAuthzLoginService(IAuthzLoginService authzLoginService) {
		this.authzLoginService = authzLoginService;
	}

}
