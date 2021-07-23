package com.bazl.dna.test.rabbitmq;

/**
 * 消息发送测试
 * @author zhaoyong
 *
 */
public interface ISendService {

	/**
	 * 消息发送
	 * @param message 消息
	 */
	public void send(String message);
}