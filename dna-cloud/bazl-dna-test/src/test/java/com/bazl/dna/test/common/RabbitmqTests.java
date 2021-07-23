package com.bazl.dna.test.common;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.test.constants.SysMessageConstants;
import com.bazl.dna.test.rabbitmq.ISendService;

/**
 * Rabbitmq Test
 * @author zhaoyong
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Component
public class RabbitmqTests {

	@Autowired
	private ISendService sendService;

    @Test
    public void send() {
    	JSONObject entity = new JSONObject();
		entity.put("queueType", SysMessageConstants.QUEUE_TYPE);
		entity.put("queueKey", SysMessageConstants.QUEUE_KEY);
		entity.put("createTime", LocalDateTime.now());
		
		entity.put("messageType", "test");
		entity.put("messageName", "测试");
		entity.put("context", "context");
		entity.put("sendUser", "admin");
		entity.put("receiveUser", "admin");
		sendService.send(JSON.toJSONString(entity));
    }
}
