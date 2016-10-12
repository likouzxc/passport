package com.likou.passport.domain;

import com.likou.core.exception.DubboException;
import com.likou.passport.bean.TestBean;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jiangli on 16/6/29.
 */
public class TestDomain {

    private TestRepository testRepository;

    public static int i=0;

    public TestDomain(TestRepository testRepository) {
        this.testRepository = testRepository;
    }


    @Transactional(rollbackFor = Exception.class)
    public void saveTestData(String key , String value) throws Exception {

        TestBean pojo = new TestBean();
        pojo.setId(i+++"");
        pojo.setUsername("lk");
        pojo.setPassword("pwd");
        pojo.setBirthday(1314l);
        pojo.setEmail("ddds@mail.com");
        pojo.setGender(true);
        pojo.setPhone("18600933902");
        pojo.setCreateTime(12123l);

        int count = testRepository.addOne(pojo);
        System.err.println(count);
        if(i%5==0)throw new DubboException("d");
        System.err.println("UserDomain---saveTestData");
    }
}
