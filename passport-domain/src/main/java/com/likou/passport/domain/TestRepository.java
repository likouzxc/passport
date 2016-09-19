package com.likou.passport.domain;

import com.likou.passport.bean.TestBean;
import com.likou.passport.dao.master.TestMasterDAO;
import com.likou.passport.dao.slave.TestSlaveDAO;
import com.likou.passport.domain.TestDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jiangli on 16/8/6.
 */
@Service
public class TestRepository {


    @Autowired
    TestMasterDAO testMasterDAO;
    @Autowired
    TestSlaveDAO testSlaveDAO;

    public TestDomain createTestDomain(){
        return new TestDomain(this);
    }

    protected  int addOne(TestBean bean ){
        return testMasterDAO.addOne(bean);
    }


}
