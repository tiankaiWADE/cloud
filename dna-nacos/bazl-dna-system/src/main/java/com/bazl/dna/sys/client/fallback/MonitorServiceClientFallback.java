package com.bazl.dna.sys.client.fallback;

import org.springframework.stereotype.Component;

import com.bazl.dna.common.ResponseData;
import com.bazl.dna.common.constants.ErrorCodes;
import com.bazl.dna.common.constants.ErrorInfo;
import com.bazl.dna.sys.client.IMonitorServiceClient;

/**
 * 监听服务回调
 * @author zhaoyong
 */
@Component
public class MonitorServiceClientFallback implements IMonitorServiceClient {

    @Override
    public ResponseData<Object> getInfo(String id) {
        return new ResponseData<>(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
    }


}
