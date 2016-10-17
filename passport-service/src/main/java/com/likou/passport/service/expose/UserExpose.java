package com.likou.passport.service.expose;

import com.likou.common.net.CookieUtils;
import com.likou.core.bean.LoginCookieBean;
import com.likou.core.dubbo.CallParam;
import com.likou.core.dubbo.CallResult;
import com.likou.core.dubbo.UserProvider;
import com.likou.core.web.Contents;
import com.likou.passport.service.self.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.apache.commons.codec.digest.MessageDigestAlgorithms.MD5;

/**
 * Created by jiangli on 16/9/19.
 */
@Service
public class UserExpose implements UserProvider {

    @Autowired
    UserService userService;

    @Override
    public CallResult isLogin(CallParam param) throws Exception {

        LoginCookieBean cookieBean = param.getValue("cookieBean", LoginCookieBean.class);
        boolean flag =userService.isLogin(cookieBean);

        if(flag) {
            return CallResult.SUCCESS();
        }else{
            return CallResult.FAILURE();
        }
    }
}
