package com.likou.passport.service.self;

import com.likou.common.character.IDGen;
import com.likou.core.dubbo.AbstractService;
import com.likou.core.dubbo.CallParam;
import com.likou.core.dubbo.CallResult;
import com.likou.core.web.Contents;
import com.likou.passport.cache.TestCache;
import com.likou.passport.domain.TestRepository;
import com.likou.passport.provider.TestProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jiangli on 16/6/29.
 */
@Service
public class TestService extends AbstractService {

    @Autowired
    TestCache testCache;
    @Autowired
    TestRepository testRepository;

    public void saveTestData(String key , String value) throws Exception{
        System.err.println("TestExpose---saveTestData");
//        testCache.saveTestData(key,value);
        try {
            testRepository.createTestDomain().saveTestData(key, value);
            CallParam callParam = new CallParam(IDGen.get32ID(), Contents.getRequestFrom());
            callParam.add("key","1");
            callParam.add("value","2");
//            CallResult callResult = getService(TestProvider.class).saveTestData(callParam);
//            getLogger().info(callResult);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
