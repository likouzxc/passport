package com.likou.passport.service.self;

import com.likou.common.character.IDGen;
import com.likou.common.net.CookieUtils;
import com.likou.common.rule.RegEx;
import com.likou.core.dubbo.AbstractService;
import com.likou.core.web.Contents;
import com.likou.passport.domain.user.UserDomain;
import com.likou.passport.domain.user.UserRepository;
import com.likou.passport.piping.param.user.UserParam;
import com.likou.passport.piping.result.user.UserResult;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 * Created by jiangli on 16/6/29.
 */
@Service
public class UserService extends AbstractService {


    private static String MD5 = "lijiang";
    private static Random random = new Random();


    @Autowired
    UserRepository userRepository;

    private BeanCopier userDomain2UserResult = BeanCopier.create(UserDomain.class,UserResult.class,false);
    private BeanCopier userParam2UserDomain = BeanCopier.create(UserParam.class,UserDomain.class,false);

    /**
     * 判断是否登录
     * @param sessionID
     * @param t
     * @param i
     * @param uuid
     * @return
     * @throws Exception
     */
    public boolean isLogin(String sessionID ,String t ,String i ,String uuid) throws Exception{

        if(StringUtils.isNotBlank(t) && StringUtils.isNotBlank(sessionID)  && StringUtils.isNotBlank(i)){
            if(t.equals(getT(sessionID,Integer.parseInt(i)))) {

                return true;
            }
        }
        return false;
    }

    /**
     * 用户名密码登录
     * @param userName
     * @param password
     * @return
     */
    public UserResult login(String userName , String password){

        int type = getTypeForLogin(userName);
        UserDomain userDomain = userRepository.createTestDomain(type, userName);
        if(userDomain.getId() == null || !userDomain.checkPassword(password)){
            return null;
        }else{
            UserResult result = new UserResult();
            userDomain2UserResult.copy(userDomain,result,null);
            return result;
        }
    }

    /**
     * 检查用户是否存在
     * @param userName
     * @return
     */
    public boolean exsit(String userName){
        int type = getTypeForLogin(userName);
        UserDomain userDomain = userRepository.createTestDomain(type, userName);
        if(userDomain.getId() == null){
            return false;
        }else{
            return true;
        }
    }

    /**
     *
     * @param value
     * @return 0:使用email初始化,1:使用用户名初始化,2:使用手机号初始化,其他使用id初始化
     */
    public int getTypeForLogin(String value){
        int type = -1;
        if(RegEx.EMAILREGEX.matcher(value).matches()) {
            type = 0;
        }else if(RegEx.USERNAMEREGEX.matcher(value).matches()) {
            type = 1;
        }else if(RegEx.MOBILREGEX.matcher(value).matches()) {
            type = 2;
        }
        return type;
    }

    public boolean reg(UserParam userBean){

        UserDomain userDomain = userRepository.createTestDomain();
        userParam2UserDomain.copy(userBean,userDomain,null);

        try {
            boolean flag = userDomain.regUser();
            return flag;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    /**
     *
     * @param response
     * @param domain
     */
    public void addCookieForLogin(HttpServletResponse response, String domain){
        int index = random.nextInt(32);
        String sessionID = DigestUtils.md5Hex(IDGen.get32ID());
        String t = getT(sessionID,index);
        String i = Integer.toString(index);

        CookieUtils.addCookie(response,domain,"/",Contents.I,i);
        CookieUtils.addCookie(response,domain,"/",Contents.T,t);
        CookieUtils.addCookie(response,domain,"/",Contents.SESSIONID,sessionID);
    }

    private String getT(String sessionID,int index){
        StringBuffer sb = new StringBuffer(sessionID);
        sb.insert(index,MD5);
        String t = DigestUtils.md5Hex(sb.toString());
        return t;
    }
}
