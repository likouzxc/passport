package com.likou.passport.service.expose.captcha;

import com.likou.common.captcha.Captcha;
import com.likou.core.dubbo.CallParam;
import com.likou.core.dubbo.CallResult;
import com.likou.core.web.Contents;
import com.likou.passport.service.self.CaptchaService;
import com.likou.passport.provider.CaptchaProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jiangli on 16/9/23.
 */
@Service
public class CaptchaExpose implements CaptchaProvider {
    @Autowired
    CaptchaService captchaService;

    @Override
    public CallResult<Captcha> getCaptcha(CallParam param){

        int width = (int) param.getValue("width");
        int height = (int) param.getValue("height");
        String sessionID = (String) param.getValue(Contents.SESSIONID);

        Captcha captcha = captchaService.getCaptcha(width, height, sessionID);

        return CallResult.SUCCESS(captcha);
    }
    @Override
    public CallResult<String> getCode(CallParam param){

        String sessionID = (String) param.getValue(Contents.SESSIONID);
        String code = captchaService.getCode(sessionID);
        if(code == null){
            return CallResult.FAILURE(-1,"没有验证码code");
        }else{
            return CallResult.SUCCESS(code);
        }
    }
}
