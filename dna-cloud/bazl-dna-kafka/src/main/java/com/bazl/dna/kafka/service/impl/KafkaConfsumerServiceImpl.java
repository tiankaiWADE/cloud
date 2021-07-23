package com.bazl.dna.kafka.service.impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;

import com.bazl.dna.kafka.service.IKafkaConsumerService;
import com.google.common.base.Optional;

/**
 * IKafkaConsumerService实现类
 * 
 * @author zhaoyong
 */
@Service
public class KafkaConfsumerServiceImpl implements IKafkaConsumerService {

	@Override
	public void listen(ConsumerRecord<String, ?> record) {
		Optional<?> kafkaMessage = Optional.of(record.value());

		if (kafkaMessage.isPresent()) {
			Object message = kafkaMessage.get();
			System.out.println("kafka message:" + message);
		}
	}

}
