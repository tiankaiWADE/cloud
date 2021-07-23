package com.bazl.dna.websocket.rabbitmq.service.binding;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.bazl.dna.websocket.rabbitmq.service.IProcessorService;

/**
 * 处理信息
 *
 * @author zhaoyong
 *
 */
@EnableBinding({ IProcessorService.class })
public class ProcessorBinding {

	/**
	 * 接收处理信息 StreamListener
	 * 转发信息 SendTo
	 *
	 * @param payload 结果
	 */
	@StreamListener(IProcessorService.MESSAGE_INPUT)
	public void processorMessage(Object payload) {
		System.out.println("receiver:" + payload);
	}
}
