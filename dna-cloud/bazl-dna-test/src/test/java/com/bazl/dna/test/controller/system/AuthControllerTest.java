package com.bazl.dna.test.controller.system;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.common.ResponseData;
import com.bazl.dna.test.BaseTests;
import com.bazl.dna.test.DnaTestApplication;

/**
 * 接口测试
 * @author zhaoyong
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DnaTestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthControllerTest extends BaseTests {

	/**
	 * 获取菜单
	 */
	@Test
	public void getMenuById() {
		String url = URL + "/system/auth/menu/1";

		HttpEntity<JSONObject> entity = new HttpEntity<>(buildHeader());
		ResponseEntity<ResponseData> result = restTemplate.exchange(url, HttpMethod.GET, entity, ResponseData.class);
		print(result);
	}


}
