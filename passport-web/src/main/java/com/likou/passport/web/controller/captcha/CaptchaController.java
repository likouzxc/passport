package com.likou.passport.web.controller.captcha;

import com.likou.common.captcha.Captcha;
import com.likou.common.character.IDGen;
import com.likou.common.net.CookieUtils;
import com.likou.core.annotation.ParamAnnotation;
import com.likou.core.web.AbstractController;
import com.likou.core.web.Contents;
import com.likou.passport.service.self.CaptchaService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import java.io.IOException;

/**
 * Created by jiangli on 16/9/18.
 */
@Controller
@RequestMapping(value = "/captcha")
public class CaptchaController extends AbstractController {

    @Autowired
    CaptchaService captchaService;

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "loginPNG" , method = RequestMethod.GET)
    @ParamAnnotation(values = {"model","sessionID"})
    public void loginPNG(Model model ){

        int width = 90 ;
        int height = 36 ;

        try {
            String sessionID = CookieUtils.getCookieByName(this.getRequest(),Contents.SESSIONID);
            if(StringUtils.isBlank(sessionID))sessionID = IDGen.get32ID();
            Captcha captcha = captchaService.getCaptcha(width,height,sessionID);
            ServletOutputStream outputStream = this.getResponse().getOutputStream();
            CookieUtils.addCookie(this.getResponse(), Contents.getCookieHost(),"/",Contents.SESSIONID,sessionID);
            captcha.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
