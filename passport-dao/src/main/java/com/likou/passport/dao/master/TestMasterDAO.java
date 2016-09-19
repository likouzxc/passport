package com.likou.passport.dao.master;

import com.likou.passport.bean.TestBean;
import org.springframework.stereotype.Repository;

/**
 * Created by jiangli on 16/6/30.
 */
@Repository
public interface TestMasterDAO {

    public int addOne(TestBean pojo);
}
