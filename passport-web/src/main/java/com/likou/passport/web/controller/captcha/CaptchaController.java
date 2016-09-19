package com.likou.passport.web.controller.captcha;

import com.likou.common.captcha.CreaterCaptcha;
import com.likou.core.annotation.ParamAnnotation;
import com.likou.core.web.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "loginPNG" , method = RequestMethod.GET)
    @ParamAnnotation(values = {"model","userName","errorMsg"})
    public void loginPNG(Model model){

        int width = 90 ;
        int height = 36 ;
        
        CreaterCaptcha captcha = new CreaterCaptcha(width , height);
        String code = captcha.getCode();
        try {
            ServletOutputStream outputStream = this.getResponse().getOutputStream();
            captcha.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
