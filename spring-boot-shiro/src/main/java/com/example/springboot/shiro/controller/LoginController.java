package com.example.springboot.shiro.controller;

import com.example.springboot.shiro.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Lucare.Feng on 2017/2/28.
 */
@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String login(User user) {
        if (user.getUsername() != null && user.getPassword() != null) {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword(), "login");
            Subject currentUser = SecurityUtils.getSubject();
            logger.info("对用户[" + user.getUsername() + "]进行登录验证..验证开始");
            try {
                currentUser.login(token);
                //验证是否登录成功
                if (currentUser.isAuthenticated()) {
                    logger.info("用户[" + user.getUsername() + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
                    return "redirect:/main";
                } else {
                    token.clear();
                    return "redirect:/login";
                }
            } catch (UnknownAccountException uae) {
                logger.info("对用户[" + user.getUsername() + "]进行登录验证..验证失败-username wasn't in the system");
            } catch (IncorrectCredentialsException ice) {
                logger.info("对用户[" + user.getUsername() + "]进行登录验证..验证失败-password didn't match");
            } catch (LockedAccountException lae) {
                logger.info("对用户[" + user.getUsername() + "]进行登录验证..验证失败-account is locked in the system");
            } catch (AuthenticationException ae) {
                logger.error("", ae);
            }
        }
        return "login";
    }

    @RequestMapping("/main")
    public String main() {
        return "main";
    }

}
