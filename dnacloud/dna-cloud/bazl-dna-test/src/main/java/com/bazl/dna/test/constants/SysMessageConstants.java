package com.bazl.dna.test.constants;

import com.bazl.dna.common.constants.PublicConstants;

/**
 * 消息常量
 * @author zhaoyong
 */
public class SysMessageConstants extends PublicConstants {
	
	/**
	 * 队列名称
	 */
	public static final String QUEUE_NAME = "sysMessageQueue";
	
	/**
	 * 队列标识
	 */
	public static final String QUEUE_KEY = "sysMessageKey";
	
	/**
	 * 队列类型
	 */
	public static final String QUEUE_TYPE = "sysMessageDirect";

	/**
	 * Constructor
	 */
	private SysMessageConstants() {
		super();
	}
}
