package com.likou.passport.web.controller.user;

import com.likou.common.net.CookieUtils;
import com.likou.core.annotation.ParamAnnotation;
import com.likou.core.error.UserErrorEnum;
import com.likou.core.web.AbstractController;
import com.likou.core.web.Contents;
import com.likou.passport.piping.param.user.UserParam;
import com.likou.passport.service.self.CaptchaService;
import com.likou.passport.service.self.UserService;
import com.likou.passport.web.validate.UserValidate;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jiangli on 16/9/18.
 */
@Controller
@RequestMapping(value = "/")
public class RegController extends AbstractController {

    @Autowired
    CaptchaService captchaService;

    @Autowired
    UserService userService;

    @Autowired
    UserValidate userValidate;


    /**
     *
     * @param model
     * @param userBean 用户名
     * @param errorMsg 错误消息
     * @return
     */
    @RequestMapping(value = "reg" , method = RequestMethod.GET)
    @ParamAnnotation(values = {"model","userBean","errorMsg"})
    public String regToPage(Model model , UserParam userBean , String errorMsg){

        model.addAttribute("userBean",userBean);
        model.addAttribute("errorMsg",errorMsg);

        return "reg";

    }

    /**
     *
     * @param model
     * @param userBean 用户信息
     * @param captcha code
     * @return
     */
    @RequestMapping(value = "regAction" , method = RequestMethod.POST)
    @ParamAnnotation(values = {"model","userBean","repassword","captcha"})
    public String reg(Model model , UserParam userBean,String repassword, String captcha ){

        String sessionID = CookieUtils.getCookieByName(this.getRequest(),Contents.SESSIONID);

        UserErrorEnum userErrorEnum;

        if(StringUtils.isNotBlank(captchaService.getCode(sessionID))
                &&!captchaService.getCode(sessionID).equalsIgnoreCase(captcha)){
            userErrorEnum = UserErrorEnum.ERROR_CAPTCHA;
        }else{
            userErrorEnum = userValidate.checkUser(userBean , repassword);
        }

        switch (userErrorEnum){
            case OK : {

                boolean flag = userService.reg(userBean);
                if(!flag){
                    userErrorEnum = UserErrorEnum.ERROR_SYSTEM;
                    String errorMsg = userErrorEnum.getMessage();
                    return regToPage(model,userBean,errorMsg);
                }else{
                    model.addAttribute("okMsg",userErrorEnum.getMessage());
                    return "redirect:/login.html";
                }
            }
            default : {
                String errorMsg = userErrorEnum.getMessage();
                return regToPage(model,userBean,errorMsg);
            }
        }

//
//
//
//
//
//
//        if(StringUtils.isNotBlank(captchaService.getCode(sessionID))
//                &&!captchaService.getCode(sessionID).equals(captcha)){
//            errorMsg = "验证码错误!";
//        }else if(StringUtils.isNotBlank(userName)
//                &&StringUtils.isNotBlank(password)){
//            int type = userService.getTypeForLogin(userName);
//            if(type < 0){
//                errorMsg = "登录名格式错误!";
//            }else{
//                UserResult userResult = userService.login(userName , password);
//                if(userResult != null){
//                    try {
//                        CookieUtils.addCookieForLogin(getResponse(), Contents.getCookieHost());
//                        CookieUtils.addCookie(getResponse(), Contents.getCookieHost(),"/",Contents.UUID,userResult.getId());
//                        getResponse().sendRedirect("http://passport.mydning.com:8000/index.html");
//                        return null;
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        errorMsg = "网络异常!";
//                    }
//                }
//            }
//        }

    }


}
