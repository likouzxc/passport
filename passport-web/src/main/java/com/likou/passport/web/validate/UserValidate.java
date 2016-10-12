package com.likou.passport.web.validate;

import com.likou.core.error.UserErrorEnum;
import com.likou.passport.piping.param.user.UserParam;
import com.likou.passport.service.self.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.likou.common.rule.RegEx.*;

/**
 * Created by jiangli on 16/10/12.
 */
@Service
public class UserValidate {

    @Autowired
    UserService userService;

    public UserErrorEnum checkUser(UserParam userParam ,String repassword){

        if(!USERNAMEREGEX.matcher(userParam.getUserName()).matches()){
            return UserErrorEnum.FORMAT_USERNAME;
        }

        if(!MOBILREGEX.matcher(userParam.getMobile()).matches()){
            return UserErrorEnum.FORMAT_MOBIL;
        }

        if(!EMAILREGEX.matcher(userParam.getEmail()).matches()){
            return UserErrorEnum.FORMAT_EMAIL;
        }

        if(!PASSWORDREGEX.matcher(userParam.getPassword()).matches()){
            return UserErrorEnum.FORMAT_PASSWORD;
        }

        if(userService.exsit(userParam.getUserName())){
            return UserErrorEnum.EXSIT_USERNAME;
        }

        if(userService.exsit(userParam.getMobile())){
            return UserErrorEnum.EXSIT_MOBIL;
        }

        if(userService.exsit(userParam.getEmail())){
            return UserErrorEnum.EXSIT_EMAIL;
        }

        if(userService.exsit(userParam.getEmail())){
            return UserErrorEnum.EXSIT_EMAIL;
        }

        if(userService.exsit(userParam.getEmail())){
            return UserErrorEnum.EXSIT_EMAIL;
        }

        if(StringUtils.isNotBlank(repassword) && !repassword.equals(userParam.getPassword())){
            return UserErrorEnum.EXSIT_PASSWORD;
        }

        return UserErrorEnum.OK;

    }
}
