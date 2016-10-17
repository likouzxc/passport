package com.likou.passport.web.controller.user;

import com.likou.common.net.CookieUtils;
import com.likou.core.annotation.ParamAnnotation;
import com.likou.core.web.AbstractController;
import com.likou.core.web.Contents;
import com.likou.passport.piping.result.user.UserResult;
import com.likou.passport.service.self.CaptchaService;
import com.likou.passport.service.self.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by jiangli on 16/9/18.
 */
@Controller
@RequestMapping(value = "/")
public class LoginController  extends AbstractController {

    @Autowired
    CaptchaService captchaService;

    @Autowired
    UserService userService;

    /**
     *
     * @param model
     * @param userName 用户名
     * @param errorMsg 错误消息
     * @return
     */
    @RequestMapping(value = "login" , method = RequestMethod.GET)
    @ParamAnnotation(values = {"model","userName","errorMsg","okMsg"})
    public String loginToPage(Model model , String userName , String errorMsg , String okMsg){

        model.addAttribute("userName",userName);
        model.addAttribute("errorMsg",errorMsg);
        model.addAttribute("okMsg",okMsg);

        return "login";

    }

    /**
     *
     * @param model
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    @RequestMapping(value = "loginAction" , method = RequestMethod.POST)
    @ParamAnnotation(values = {"model","userName","password","captcha","url"})
    public String login(Model model , String userName , String password ,String captcha , String url){

        String errorMsg = "登录名不存在或用户名密码错误!";
        String sessionID = CookieUtils.getCookieByName(this.getRequest(),Contents.SESSIONID);

        if(StringUtils.isNotBlank(captchaService.getCode(sessionID))
                &&!captchaService.getCode(sessionID).equalsIgnoreCase(captcha)){
            errorMsg = "验证码错误!";
        }else if(StringUtils.isNotBlank(userName)
                &&StringUtils.isNotBlank(password)){
            int type = userService.getTypeForLogin(userName);
            if(type < 0){
                errorMsg = "登录名格式错误!";
            }else{
                UserResult userResult = userService.login(userName , password);
                if(userResult != null){
                    try {
                        userService.addCookieForLogin(getResponse(), Contents.getCookieHost() , userResult.getId());
                        getResponse().sendRedirect("http://passport.mydning.com:8000/index.html");
                        return null;
                    } catch (IOException e) {
                        e.printStackTrace();
                        errorMsg = "网络异常!";
                    }
                }
            }
        }
        model.addAttribute("userName",userName);
        model.addAttribute("errorMsg",errorMsg);

        return "redirect:/login.html";

    }

    @RequestMapping(value = "logout" , method = RequestMethod.GET)
    @ParamAnnotation(values = {"model"})
    public void logout(Model model){
        String sessionID = CookieUtils.getCookieByName(this.getRequest(),Contents.SESSIONID);
        String uuid = CookieUtils.getCookieByName(this.getRequest(),Contents.UUID);

        try {
            if(StringUtils.isNotBlank(sessionID) && StringUtils.isNotBlank(uuid)){
                userService.logout(sessionID,uuid);
            }
            getResponse().sendRedirect(Contents.getLoginURL());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
