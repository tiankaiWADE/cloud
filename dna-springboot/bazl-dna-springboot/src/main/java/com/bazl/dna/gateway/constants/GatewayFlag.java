package com.bazl.dna.gateway.constants;

/**
 * 网关开关常量
 * @author zhaoyong
 */
public class GatewayFlag {

	/**
	 * 队列名称
	 */
	protected static boolean routeStatusFlag = true;

	/**
	 * Constructor
	 */
	private GatewayFlag() {
	}

	public static boolean isRoutestatusflag() {
		return routeStatusFlag;
	}

}
