package com.bazl.dna.kafka.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

import com.bazl.dna.kafka.constants.KafkaConstants;

/**
 * Kafka消费者接口
 * 
 * @author zhaoyong
 */
public interface IKafkaConsumerService {

	/**
	 * 监听服务
	 * 
	 * @param data 参数
	 */
	@KafkaListener(id = KafkaConstants.GROUP_ID, topics = { KafkaConstants.TOPIC })
	void listen(ConsumerRecord<String, ?> record);
}
