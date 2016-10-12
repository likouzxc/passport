package com.likou.passport.provider;

import com.likou.common.captcha.Captcha;
import com.likou.core.dubbo.CallParam;
import com.likou.core.dubbo.CallResult;

/**
 * Created by jiangli on 16/9/23.
 */
public interface CaptchaProvider {
    /**
     *
     * @param param
     * @return
     */
    CallResult<Captcha> getCaptcha(CallParam param);

    /**
     *
     * @param param
     * @return
     */
    CallResult<String> getCode(CallParam param);
}
