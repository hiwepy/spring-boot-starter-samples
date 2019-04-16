package net.jeebiz.boot.demo.dao.entities;

import java.util.List;

import org.apache.ibatis.type.Alias;
import org.apache.shiro.biz.authz.principal.ShiroPrincipal;

import net.jeebiz.boot.authz.rbac0.dao.entities.AuthzRoleModel;
import net.jeebiz.boot.authz.rbac0.dao.entities.AuthzUserDetailModel;

@Alias(value = "AuthzLoginModel")
@SuppressWarnings("serial")
public class AuthzLoginModel extends ShiroPrincipal {

	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 电子邮箱
	 */
	private String email;
	/**
	 * 用户状态(0:不可用|1:正常|2:锁定)
	 */
	private String status;
	/**
	 * 初始化时间
	 */
	private String time24;
	/**
	 * 当前角色ID
	 */
	private String roleid;
	/**
	 * 用户详情
	 */
	private AuthzUserDetailModel detail;
	/**
	 * 用户角色
	 */
	private List<AuthzRoleModel> roleList;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTime24() {
		return time24;
	}

	public void setTime24(String time24) {
		this.time24 = time24;
	}

	public AuthzUserDetailModel getDetail() {
		return detail;
	}

	public void setDetail(AuthzUserDetailModel detail) {
		this.detail = detail;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public List<AuthzRoleModel> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<AuthzRoleModel> roleList) {
		this.roleList = roleList;
	}

}
