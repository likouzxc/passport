package com.likou.passport.dao.slave.user;

import com.likou.passport.bean.user.UserBean;
import org.springframework.stereotype.Repository;

/**
 * Created by jiangli on 16/9/20.
 */
@Repository
public interface UserSlaveDAO {

    /**
     * 通过ID获取用户
     * @param id
     * @return
     */
    public UserBean getUserByID(String id);

    /**
     * 通过email获取用户
     * @param email
     * @return
     */
    public UserBean getUserByEmail(String email);

    /**
     * 通过userName获取用户
     * @param userName
     * @return
     */
    public UserBean getUserByUserName(String userName);

    /**
     * 通过mobile获取用户
     * @param mobile
     * @return
     */
    public UserBean getUserByMobile(String mobile);
}
