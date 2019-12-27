package net.jeebiz.boot.demo.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import net.jeebiz.boot.api.dao.BaseDao;
import net.jeebiz.boot.demo.dao.entities.AuthzLoginModel;

/**
 * 登录查询Dao
 * 
 * @author hiwepy
 */
@Mapper
public interface IAuthzLoginDao extends BaseDao<AuthzLoginModel> {

	/**
	 * 根据用户ID和密码查询用户可否登录，角色数量等信息
	 * @param username : 用户名
	 * @param password : 密码，可不填
	 * @return 用户账号状态信息
	 */
	public Map<String, String> getAccountStatus(@Param(value = "username") String username,
			@Param(value = "password") String password);

	/***
	 *  根据用户ID和密码查询用户信息
	 * @param username : 用户名
	 * @param password : 密码，可不填
	 * @return 用户登录信息
	 */
	public AuthzLoginModel getAccount(@Param(value = "username") String username,
			@Param(value = "password") String password);

	/***
	 * 根据用户ID无密码查询用户信息；用于单点登录
	 * @param username : 用户名
	 * @return 用户登录信息
	 */
	public AuthzLoginModel getAccountWithoutPwd(@Param(value = "username") String username);

}
