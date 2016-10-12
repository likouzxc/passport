package com.likou.passport.service.expose;

import com.likou.common.net.CookieUtils;
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

        String t = (String) param.getValue(Contents.T);
        String i = (String) param.getValue(Contents.I);
        String sessionID = (String) param.getValue(Contents.SESSIONID);
        String uuid = (String) param.getValue(Contents.UUID);

        boolean flag =userService.isLogin(sessionID,t,i,uuid);

        if(flag) {
            return CallResult.SUCCESS();
        }else{
            return CallResult.FAILURE();
        }
    }
}
