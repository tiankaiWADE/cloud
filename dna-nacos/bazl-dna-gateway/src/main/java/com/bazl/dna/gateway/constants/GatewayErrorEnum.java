package com.bazl.dna.gateway.constants;

import org.apache.logging.log4j.util.Strings;

/**
 * 服务错误信息
 * 
 * @author zhaoyong
 *
 */
public enum GatewayErrorEnum {

	/** 错误信息枚举 */
	BAZL_DNA_FILES("bazl-dna-files", "文件中心"), 
	BAZL_DNA_SYSTEM("bazl-dna-system", "权限中心"),
	BAZL_DNA_MONITOR("bazl-dna-monitor", "监控服务"),
	BAZL_DNA_KAFKA("bazl-dna-kafka", "Kafka服务"),
	BAZL_DNA_WEBSOCKET("bazl-dna-websocket", "Websocket服务"),
	BAZL_DNA_ELASTICSEARCH("bazl-dna-elasticsearch", "Elasticsearch服务");

	GatewayErrorEnum(String name, String description) {
		this.name = name;
		this.description = description;
	}

	private String name;
	private String description;

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
	public static String getDescription(String name) {
		for (GatewayErrorEnum e : GatewayErrorEnum.values()) {
			if (name.contains(e.getName())) {
				return e.getDescription();
			}
		}
		return Strings.EMPTY;
	}

}
