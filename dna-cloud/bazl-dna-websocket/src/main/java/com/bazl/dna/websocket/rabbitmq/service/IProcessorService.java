package com.bazl.dna.websocket.rabbitmq.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * 发送接收信息
 * 
 * @author zhaoyong
 *
 */
public interface IProcessorService {

	final String MESSAGE_INPUT = "input";

	final String MESSAGE_OUTPUT = "output";

	/**
	 * 接收信息
	 * @return SubscribableChannel
	 */
	@Input(MESSAGE_INPUT)
	SubscribableChannel input();

	/**
	 * 发送信息
	 * @return MessageChannel
	 */
	@Output(MESSAGE_OUTPUT)
	MessageChannel output();
}
