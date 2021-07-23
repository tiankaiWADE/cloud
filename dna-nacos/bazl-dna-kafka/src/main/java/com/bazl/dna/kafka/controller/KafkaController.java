package com.bazl.dna.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bazl.dna.controller.BaseController;
import com.bazl.dna.kafka.constants.KafkaConstants;

/**
 * Kafka控制器
 * @author zhaoyong
 */
@RestController
@RequestMapping("/kafka")
public class KafkaController extends BaseController {

	@Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
	
	/**
	 * 发送消息
	 * @param message 消息
	 * @return String
	 */
	@GetMapping("/send")
	public String send(String message) {
		LOGGER.info("Send topic:{}, message:{}", KafkaConstants.TOPIC, message);
		kafkaTemplate.send(KafkaConstants.TOPIC, message);
		return message;
	}
}
