package net.jeebiz.boot.demo.setup.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.biz.realm.AbstractAuthorizingRealm;

/**
 *  Login AuthorizingRealm
 */
public class LoginAuthorizingRealm extends AbstractAuthorizingRealm {

	@Override
	public Class<?> getAuthenticationTokenClass() {
		return UsernamePasswordToken.class;// 此Realm只支持UsernamePasswordToken
	}

}
