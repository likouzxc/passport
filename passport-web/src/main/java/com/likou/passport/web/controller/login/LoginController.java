package com.likou.passport.web.controller.login;

import com.likou.common.net.CookieUtils;
import com.likou.core.annotation.ParamAnnotation;
import com.likou.core.web.AbstractController;
import com.likou.core.web.Contents;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by jiangli on 16/9/18.
 */
@Controller
@RequestMapping(value = "/")
public class LoginController  extends AbstractController {

    /**
     *
     * @param model
     * @param userName 用户名
     * @param errorMsg 错误消息
     * @return
     */
    @RequestMapping(value = "login" , method = RequestMethod.GET)
    @ParamAnnotation(values = {"model","userName","errorMsg"})
    public String loginToPage(Model model , String userName , String errorMsg){

        model.addAttribute("userName",userName);
        model.addAttribute("errorMsg",errorMsg);

        return "login";

    }

    /**
     *
     * @param model
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    @RequestMapping(value = "login" , method = RequestMethod.POST)
    @ParamAnnotation(values = {"model","userName","password","captcha","url"})
    public String login(Model model , String userName , String password ,String captcha , String url){

        String errorMsg = "用户名不存在或用户名密码错误!";

        if(!"code".equals(captcha)){
            errorMsg = "验证码错误!";
        }else if("root".equals(userName) && "pwd".equals(password)){
            try {
                CookieUtils.addCookieForLogin(getResponse(), Contents.getCookieHost());
                CookieUtils.addCookie(getResponse(), Contents.getCookieHost(),"/","uuid","lijiang81833");
                getResponse().sendRedirect("http://passport.mydning.com:8000/index.html");



            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        return loginToPage(model,userName,errorMsg);

    }
}
