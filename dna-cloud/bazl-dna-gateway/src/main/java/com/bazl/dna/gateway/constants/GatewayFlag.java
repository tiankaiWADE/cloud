package com.bazl.dna.gateway.constants;

/**
 * 网关队列常量
 * @author zhaoyong
 */
public class GatewayFlag {

	/**
	 * 队列名称
	 */
	private static boolean routeStatusFlag = true;

	/**
	 * Constructor
	 */
	private GatewayFlag() {
	}

	public static boolean isRoutestatusflag() {
		return routeStatusFlag;
	}

}
