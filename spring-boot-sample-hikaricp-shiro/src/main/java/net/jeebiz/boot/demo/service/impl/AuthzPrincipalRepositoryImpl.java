package net.jeebiz.boot.demo.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.biz.authc.exception.InvalidAccountException;
import org.apache.shiro.biz.authc.exception.NoneRoleException;
import org.apache.shiro.biz.authz.principal.ShiroPrincipalRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.common.collect.Sets;

import net.jeebiz.boot.authz.rbac0.dao.IAuthzRoleDao;
import net.jeebiz.boot.authz.rbac0.dao.IAuthzRolePermsDao;
import net.jeebiz.boot.authz.rbac0.dao.IAuthzUserDao;
import net.jeebiz.boot.authz.rbac0.dao.entities.AuthzRoleModel;
import net.jeebiz.boot.demo.dao.IAuthzLoginDao;
import net.jeebiz.boot.demo.dao.entities.AuthzLoginModel;
	
@Service("defRepository")
public class AuthzPrincipalRepositoryImpl extends ShiroPrincipalRepositoryImpl {

	@Autowired
	private IAuthzLoginDao authzLoginDao;
	@Autowired
	private IAuthzUserDao authzUserDao;
	@Autowired
	private IAuthzRoleDao authzRoleDao;
	@Autowired
	private IAuthzRolePermsDao authzRolePermsDao;
	
	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		
		if( !StringUtils.hasText(upToken.getUsername()) || upToken.getPassword() == null ){
			throw new UnknownAccountException("Username or password is required.");
		}
		//密码加密
		String pwd = new String(upToken.getPassword());//Base64.encodeBase64String(new String(upToken.getPassword()).getBytes());
		//账号状态
		Map<String, String> statusMap = getAuthzLoginDao().getAccountStatus(upToken.getUsername(), pwd);
   		//账号不存在 或 用户名或密码不正确
   		if("0".equals(statusMap.get("num_1")) || "0".equals(statusMap.get("num_2"))){
   			throw new InvalidAccountException("Username or password is incorrect, please re-enter.");
   		}
   		// 账号被禁用
		else if ("0".equals(statusMap.get("num_4"))) {
			throw new DisabledAccountException("Account is disabled.");
		}
   		//用户无所属角色
   		else if("0".equals(statusMap.get("num_3"))){
            throw new NoneRoleException();
   		}
   		
   		// 用户主体对象
   		AuthzLoginModel model = getAuthzLoginDao().getAccount(upToken.getUsername(), pwd);
   		// 用户角色ID集合
   		List<String> roles = getAuthzUserDao().getRoles(model.getUserid());
   		model.setRoles(Sets.newHashSet(roles.iterator()));
   		model.setRoleid(roles.get(0));
   		// 用户权限标记集合
   		Set<String> perms =  Sets.newHashSet();
		for (String roleid : model.getRoles()) {
			perms.addAll(getAuthzRolePermsDao().getPermissions(roleid));
		}
		model.setPerms(perms);
   		// 认证信息
		return new SimpleAuthenticationInfo(model, upToken.getPassword(), "login");
	}
	 
	
	@Override
	public Set<String> getRoles(Set<Object> principals) {
		Set<String> set =  Sets.newHashSet();
		for (Object principal : principals) {
			if(principal instanceof AuthzLoginModel) {
				AuthzLoginModel model = (AuthzLoginModel) principal;
				set.addAll(getAuthzUserDao().getRoles(model.getUserid()));
			}
		}
		return set;
	}
	
	 
	
	@Override
	public Set<String> getPermissions(Set<Object> principals) {
		Set<String> set =  Sets.newHashSet();
		for (Object principal : principals) {
			if(principal instanceof AuthzLoginModel) {
				AuthzLoginModel model = (AuthzLoginModel) principal;
				set.addAll(getAuthzRolePermsDao().getPermissions(model.getRoleid()));
				for (AuthzRoleModel roleModel : model.getRoleList()) {
					set.addAll(getAuthzRolePermsDao().getPermissions(roleModel.getId()));
				}
			}
		}
		return set;
	}
	
	@Override
	public void doLock(Object principal) {
		// TODO Auto-generated method stub
		
	}

	public IAuthzLoginDao getAuthzLoginDao() {
		return authzLoginDao;
	}

	public void setAuthzLoginDao(IAuthzLoginDao authzLoginDao) {
		this.authzLoginDao = authzLoginDao;
	}

	public IAuthzUserDao getAuthzUserDao() {
		return authzUserDao;
	}

	public void setAuthzUserDao(IAuthzUserDao authzUserDao) {
		this.authzUserDao = authzUserDao;
	}

	public IAuthzRoleDao getAuthzRoleDao() {
		return authzRoleDao;
	}

	public void setAuthzRoleDao(IAuthzRoleDao authzRoleDao) {
		this.authzRoleDao = authzRoleDao;
	}

	public IAuthzRolePermsDao getAuthzRolePermsDao() {
		return authzRolePermsDao;
	}

	public void setAuthzRolePermsDao(IAuthzRolePermsDao authzRolePermsDao) {
		this.authzRolePermsDao = authzRolePermsDao;
	}
	
}