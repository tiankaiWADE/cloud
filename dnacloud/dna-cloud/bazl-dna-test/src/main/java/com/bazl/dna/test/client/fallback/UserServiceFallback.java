package com.bazl.dna.test.client.fallback;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.common.ResponseData;
import com.bazl.dna.test.client.IUserServiceClient;

/**
 * 用户服务回调
 * @author zhaoyong
 */
@Component
public class UserServiceFallback implements IUserServiceClient {

	@Override
	public ResponseData login(String json) {
		return null;
	}

	@Override
	public ResponseData getTypeById(String id) {
		return null;
	}

	@Override
	public ResponseData findTypeList(JSONObject paramJson) {
		return null;
	}

}
