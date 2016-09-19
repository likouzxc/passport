package com.likou.passport.service.expose;

import com.likou.core.dubbo.AbstractService;
import com.likou.core.dubbo.CallParam;
import com.likou.core.dubbo.CallResult;
import com.likou.passport.cache.TestCache;
import com.likou.passport.domain.TestRepository;
import com.likou.passport.provider.TestProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jiangli on 16/6/29.
 */
@Service
public class TestExpose extends AbstractService implements TestProvider{

    @Autowired
    TestCache testCache;
    @Autowired
    TestRepository testRepository;

    @Override
    public CallResult saveTestData(CallParam param) throws Exception{
        System.err.println("TestExpose---saveTestData");
        try {
            testRepository.createTestDomain().saveTestData(param.getValue("key" , String.class), param.getValue("value" , String.class));
            return CallResult.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
