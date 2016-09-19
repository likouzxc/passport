package com.likou.passport.dao.slave;

import com.likou.passport.bean.TestBean;
import org.springframework.stereotype.Repository;

/**
 * Created by jiangli on 16/6/30.
 */
@Repository
public interface TestSlaveDAO {

    public TestBean getOne();
}
