package com.likou.passport.dao.master.user;

import com.likou.passport.bean.user.UserBean;
import org.springframework.stereotype.Repository;

/**
 * Created by jiangli on 16/9/20.
 */
@Repository
public interface UserMasterDAO {

    public int regUser(UserBean bean);
}
