package net.jeebiz.boot.demo.service;

import java.security.GeneralSecurityException;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.jeebiz.boot.api.service.BaseService;
import net.jeebiz.boot.demo.dao.entities.AuthzLoginModel;

public interface IAuthzLoginService extends BaseService<AuthzLoginModel>{
	
	RSAPublicKey genPublicKey(HttpServletRequest request) throws GeneralSecurityException;
	
	/**
	 * 根据用户ID和密码查询用户可否登录，角色数量等信息
	 * @param username : 用户名
	 * @param password : 密码，可不填
	 * @return 用户账号状态信息
	 */
	Map<String, String> getAccountStatus(String username, String password);

	/***
	 *  根据用户ID和密码查询用户信息
	 * @param username : 用户名
	 * @param password : 密码，可不填
	 * @return 用户登录信息
	 */
	AuthzLoginModel getAccount(String username, String password);

	/***
	 * 根据用户ID无密码查询用户信息；用于单点登录
	 * @param username : 用户名
	 * @return 用户登录信息
	 */
	AuthzLoginModel getAccountWithoutPwd(String username);

}
