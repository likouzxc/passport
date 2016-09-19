package com.likou.passport.provider;

import com.likou.core.dubbo.CallParam;
import com.likou.core.dubbo.CallResult;

/**
 * Created by jiangli on 16/6/28.
 */
public interface TestProvider {

    CallResult saveTestData(CallParam param) throws Exception;
}
