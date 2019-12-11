package com.eastwind.backend.controller.api;


import com.eastwind.backend.dto.Result;
import com.eastwind.backend.dto.ResultFactory;
import com.eastwind.backend.model.SysUsers;
import com.eastwind.backend.model.front.VueLoginInfoVo;
import com.eastwind.backend.service.SysUserService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

/**
 * @author uncleY
 * 处理用户注册与登录
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping(value = "/register")
    public Result userRegister(@Valid @RequestBody SysUsers user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            String message = String.format("注册失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            return ResultFactory.buildFailResult(message);
        }

        String password = user.getPassword();

        // 必须加密存储
        String[] saltAndCiphertext = encryptPassword(password);

        user.setSalt(saltAndCiphertext[0]);
        user.setPassword(saltAndCiphertext[1]);

        int register = 0;
        try {
            register = sysUserService.register(user);
        } catch (DuplicateKeyException e) {
            return ResultFactory.buildFailResult("用户名重复");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (register == 0) {
            return ResultFactory.buildFailResult("注册失败，请重试");
        }

        return ResultFactory.buildSuccessResult("注册成功");
    }

    /**
     * 登录控制器
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Result login(@Valid @RequestBody VueLoginInfoVo loginInfoVo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message = String.format("登陆失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            return ResultFactory.buildFailResult(message);
        }


        // 获取加盐加密后的密码
        String encryptPassword = getInputPasswordCiph(loginInfoVo.getPassword(), sysUserService.selectSaltByName(loginInfoVo.getUsername()));

        //登录后存放进shiro token
        UsernamePasswordToken token = new UsernamePasswordToken(loginInfoVo.getUsername(), encryptPassword);
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            log.error("登录失败", e);
            String message = String.format("登陆失败，详细信息[用户名、密码信息不正确]。");
            return ResultFactory.buildFailResult(message);
        } catch (AuthenticationException e) {
            log.error("登录失败", e);
            String message = String.format("登陆失败，详细信息[用户名、密码信息不正确]。");
            return ResultFactory.buildFailResult(message);
        }

        return ResultFactory.buildSuccessResult("登陆成功");
    }


    /**
     * 用户注册时加密用户的密码
     * 输入密码明文 返回密文与盐值
     * @param password
     * @return 第一个是密文  第二个是盐值
     */
    private String[] encryptPassword(String password) {
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex(); //生成盐值
        // hashIterations 是散列的次数，安全起见，最少3次
        String ciphertext = new Md5Hash(password, salt, 3).toString(); //生成的密文

        String[] strings = new String[]{salt, ciphertext};

        return strings;
    }

    /**
     * 获得本次输入的密码的密文
     * @param password
     * @param salt
     * @return
     */
    private String getInputPasswordCiph(String password, String salt) {
        if (salt == null) {
            password = "";
        }

        String ciphertext = new Md5Hash(password, salt, 3).toString(); //生成的密文

        return ciphertext;
    }
}
