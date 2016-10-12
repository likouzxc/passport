package com.likou.passport.service.self;

import com.likou.common.captcha.Captcha;
import com.likou.passport.cache.CaptchaCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jiangli on 16/9/20.
 */
@Service
public class CaptchaService {

    @Autowired
    CaptchaCache captchaCache;

    public Captcha getCaptcha(int width , int height , String sessionID){

        Captcha captcha = new Captcha(width , height);
        captcha.createCode();
        captchaCache.saveCaptcha(sessionID,captcha.getCode());
        return captcha;
    }

    public String getCode(String sessionID){
        return captchaCache.getCode(sessionID);
    }
}
