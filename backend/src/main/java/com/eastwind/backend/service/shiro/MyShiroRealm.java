package com.eastwind.backend.service.shiro;

import com.eastwind.backend.model.SysUsers;
import com.eastwind.backend.model.SysUsersExample;
import com.eastwind.backend.service.SysUserService;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MyShiroRealm")
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public String getName() {
        return "MyShiroRealm";
    }

    /**
     * 权限检查
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 做权限检查
        return null;
    }

    /**
     * 登录认证举例
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //通过表单接收的用户名
        String username = token.getUsername();

        SysUsersExample usersExample = new SysUsersExample();
        usersExample.createCriteria().andUsernameEqualTo(username);
        List<SysUsers> listByCondation = sysUserService.findListByCondation(usersExample);

        if (null == listByCondation || listByCondation.size() == 0) {
            return null;
        }

        // 登录用户名是不能重复的
        if (listByCondation.size() > 1) {
            throw new AccountException("账户异常，请联系管理员");
        }
        return new SimpleAuthenticationInfo(listByCondation.get(0).getUsername(), listByCondation.get(0).getPassword(), getName());

    }
}