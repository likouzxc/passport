package com.likou.passport.service.expose;

import com.likou.common.net.CookieUtils;
import com.likou.core.dubbo.CallParam;
import com.likou.core.dubbo.CallResult;
import com.likou.core.dubbo.UserProvider;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import static org.apache.commons.codec.digest.MessageDigestAlgorithms.MD5;

/**
 * Created by jiangli on 16/9/19.
 */
@Service
public class UserExpose implements UserProvider {
    @Override
    public CallResult isLogin(CallParam param) throws Exception {

        String t = (String) param.getValue("t");
        String i = (String) param.getValue("i");
        String sessionID = (String) param.getValue("sessionID");
        boolean flag =false;

        if(StringUtils.isNotBlank(t) && StringUtils.isNotBlank(sessionID)  && StringUtils.isNotBlank(i)){
            int index = Integer.parseInt(i);
            StringBuffer sb = new StringBuffer(sessionID);
            sb.insert(index, CookieUtils.MD5);
            if(t.equals(DigestUtils.md5Hex(sb.toString()))){
                flag = true;
            }
        }
        if(flag) {
            return CallResult.SUCCESS();
        }else{
            return CallResult.FAILURE();
        }
    }
}
