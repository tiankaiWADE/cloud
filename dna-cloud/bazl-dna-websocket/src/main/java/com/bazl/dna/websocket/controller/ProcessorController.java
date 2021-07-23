package com.bazl.dna.websocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.websocket.rabbitmq.service.IProcessorService;

/**
 * 发送消息 Rabbitmq Channel
 *
 * @author zhaoyong
 */
@RestController
public class ProcessorController {

	@Autowired
	private IProcessorService processorService;

	/**
	 * 发送消息
	 *
	 * @param data 消息
	 */
	@PostMapping("send")
	public void send(@RequestBody JSONObject data) {
		Message<?> message = MessageBuilder.withPayload(data).build();
		processorService.output().send(message);
	}
}
