package com.netease.auth;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

public class LoginTest extends BaseAuthTest {

    @Test
    public void loginTest()
    {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager manager = factory.getInstance();
        SecurityUtils.setSecurityManager(manager);

        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
        Subject subject=SecurityUtils.getSubject();
        try {
            subject.login(token);
        }catch (AuthenticationException e){
            e.printStackTrace();
        }



        Assert.assertEquals(true,subject.isAuthenticated());
        subject.logout();
    }
}
