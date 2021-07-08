package com.bazl.dna.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bazl.dna.common.ResponseData;
import com.bazl.dna.kafka.constants.KafkaConstants;

/**
 * Kafka控制器
 * @author zhaoyong
 */
@RestController
@RequestMapping("/kafka")
public class KafkaController {

	@Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
	
	@GetMapping("/sendMessage")
	public ResponseData sendMessage(String data) {
		kafkaTemplate.send(KafkaConstants.TOPIC, data);
		return new ResponseData(data);
	}
}
