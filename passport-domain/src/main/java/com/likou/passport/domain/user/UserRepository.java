package com.likou.passport.domain.user;

import com.likou.passport.bean.user.UserBean;
import com.likou.passport.dao.master.user.UserMasterDAO;
import com.likou.passport.dao.slave.user.UserSlaveDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jiangli on 16/8/6.
 */
@Service
public class UserRepository {

    @Autowired
    UserMasterDAO userMasterDAO;
    @Autowired
    UserSlaveDAO userSlaveDAO;

    public UserDomain createTestDomain(){
        return new UserDomain(this);
    }
    /**
     * 通过id初始化domain
     * @param id
     */
    public UserDomain createTestDomain(String id){
        return new UserDomain(this,id);
    }

    /**
     * 根据email、username、mobile初始化domain
     * @param type 登录类型,0:使用email初始化,1:使用用户名初始化,2:使用手机号初始化,其他使用id初始化
     * @param value 数值
     */
    public UserDomain createTestDomain(int type , String value) {
        return new UserDomain(this,type,value);
    }

    protected  int regUser(UserBean bean){
        return userMasterDAO.regUser(bean);
    }
    protected UserBean initUserByID(String id){
        return userSlaveDAO.getUserByID(id);
    }
    protected UserBean initUserByEmail(String email){
        return userSlaveDAO.getUserByEmail(email);
    }
    protected UserBean initUserByUserName(String userName){
        return userSlaveDAO.getUserByUserName(userName);
    }
    protected UserBean initUserByMobile(String mobile){
        return userSlaveDAO.getUserByMobile(mobile);
    }

}
