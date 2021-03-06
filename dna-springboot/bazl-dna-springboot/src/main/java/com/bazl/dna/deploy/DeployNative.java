package com.bazl.dna.deploy;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bazl.dna.deploy.utils.DeployMethod;
import com.bazl.dna.deploy.utils.DeployUtils;

/**
 * 自动化本地项目
 *
 * 部署到本地：请先执行 maven build 生成jar到 target 下在运行
 *
 * @author zhaoyong
 *
 */
public class DeployNative {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeployNative.class);

	/**
	 * @param args 测试
	 */
	public static void main(String[] args) {
		Properties properties = DeployUtils.getProperties();
		String project = properties.get("deploy.project").toString();
		String[] projectNames = StringUtils.split(project, ",");
		LOGGER.info("Deploy name: {}", properties.getProperty("deploy.name"));
		LOGGER.info("Deploy project: {}", project);
		// 部署目录
		DeployMethod.deployNative(projectNames, properties.getProperty("deploy.name"),
				properties.getProperty("deploy.version"), properties.getProperty("deploy.suffix"), 
				properties.getProperty("deploy.location"));
	}

}
