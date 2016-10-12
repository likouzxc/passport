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

import java.io.IOException;

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
                &&!captchaService.getCode(sessionID).equals(captcha)){
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
                        userService.addCookieForLogin(getResponse(), Contents.getCookieHost());
                        CookieUtils.addCookie(getResponse(), Contents.getCookieHost(),"/",Contents.UUID,userResult.getId());
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
}
