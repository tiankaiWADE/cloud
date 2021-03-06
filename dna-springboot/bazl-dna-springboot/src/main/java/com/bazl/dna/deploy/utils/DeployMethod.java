package com.bazl.dna.deploy.utils;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bazl.dna.deploy.entity.SshServer;
import com.bazl.dna.util.DataUtils;
import com.google.common.collect.Lists;

import ch.ethz.ssh2.Connection;

/**
 * 部署工具类
 * @author zhaoyong
 */
public class DeployMethod {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeployMethod.class);
	
	private static final String FILE_DELIMITER = "/";
	
	private static final String FILE_LIB = "lib";
	private static final String FILE_TARGET = "target";
	private static final String FILE_RESOURCES = "resources";
	
	private DeployMethod() {
	}

	/**
	 * 获取对象列表
	 * @return List<Server>
	 */
	public static List<SshServer> getServerList(Properties properties) {
		// 服务器信息
		List<SshServer> deployServerList = Lists.newArrayList();
		if (properties != null) {
			String ip = properties.get("servers.ip").toString();
			String[] ips = StringUtils.split(ip, ",");
			for (int i = 0; i < ips.length; i++) {
				SshServer server = new SshServer(ips[i],
						Integer.parseInt(StringUtils.split(properties.get("servers.port").toString(), ",")[i]),
						StringUtils.split(properties.get("servers.username").toString(), ",")[i],
						StringUtils.split(properties.get("servers.password").toString(), ",")[i]);
				deployServerList.add(server);
			}
		}
		return deployServerList;
	}

	/**
	 * 停止服务
	 * @param con 连接对象
	 * @param targetServer 目标服务
	 */
	public static void stop(Connection con, String targetServer) {
		//kill tomcat
		String cmd = "kill -9 `cat "+targetServer+".pid 2>/dev/null` 2>/dev/null || true";
		LOGGER.info("> {}", cmd);
		List<List<String>> execList = DeployUtils.execCommand(con, new String[] { cmd });
		for (List<String> list : execList) {
			for (String s : list) {
				LOGGER.info("> {}", s);
			}
		}
	}

	/**
	 * 启动tomcat服务
	 * @param con 连接对象
	 * @param targetServer 目标服务
	 */
	public static void start(Connection con, String targetServer) {
		//tomcat启动找不到java_home,需要设置 ln -s /opt/jdk1.8/bin/java /bin/java
		String cmd = targetServer + "/bin/startup.sh";
		LOGGER.info("> {}", cmd);
		List<List<String>> execList = DeployUtils.execCommand(con, new String[] { cmd });
		for (List<String> list : execList) {
			for (String s : list) {
				LOGGER.info("> {}", s);
			}
		}
	}

	/**
	 * 上传文件到目标服务器
	 */
	public static void deploy(SshServer server, String localFile, String targetPath) {
		// upload
		String a1 = "scp -P " + server.getPort() + " -r " + localFile + " " + server.getUsername() + "@" + server.getIp() + ":" + targetPath;
		LOGGER.info("> {}", a1);
		DeployUtils.execExecute(a1);
	}

	/**
	 * 执行命令
	 * @param con 连接对象
	 * @param commands 命令
	 */
	public static void execCommands(Connection con, String[] commands) {

		List<List<String>> execList = DeployUtils.execCommand(con, commands);
		for (List<String> list : execList) {
			for (String s : list) {
				LOGGER.info("> {}", s);
			}
		}
	}

	/**
	 * 打包部署到指定 目录中
	 * @param projectNames 项目名称
	 * @param targetRoot 目标目录
	 * @param version 版本
	 * @param suffix 后缀
	 */
	public static void deployNative(String[] projectNames, String targetRoot, 
			String version, String suffix, String location) {
		for (String projectName : projectNames) {
			// jar包全路径
			String path = location + File.separator + projectName + File.separator +
					FILE_TARGET + File.separator + projectName + "-" + version + "." + suffix;
			String resourcePath = location + File.separator + projectName + File.separator +
					FILE_TARGET + File.separator + FILE_RESOURCES;
			String libPath = location + File.separator + projectName + File.separator +
					FILE_TARGET + File.separator + FILE_LIB;
			// 目标目录
			String targetPath = targetRoot + File.separator + projectName;

			// 拷贝相关文件
			DataUtils.createFolder(targetPath);
			DataUtils.copyFile(path, targetPath);
			DataUtils.copyDirectory(resourcePath, targetPath);
			DataUtils.copyDirectory(libPath, targetRoot);

		}
		
	}

	public static void deployServer(List<SshServer> deployServerList,
			String[] projectNames, String local, String targetRoot, String version, String suffix, String libFile) {
		try {
			// 多个目标进行部署
			for (SshServer server : deployServerList) {
				// get connection
				Connection con = DeployUtils.getConnection(server.getIp(), server.getPort());

				// 认证
				boolean auth = DeployUtils.auth(con, server.getUsername(), server.getPassword());
				LOGGER.info("Auth : {}", auth);
				if (auth) {
					String[] cmds = deploy(con, server, projectNames, local, targetRoot, version + "." + suffix, libFile);
					DeployMethod.execCommands(con, cmds);
				}
				// close
				DeployUtils.close(con, null, null);
			}
		} catch (Exception e) {
			LOGGER.error("deployServer error:", e);
		}
	}

	public static String[] deploy(Connection con, SshServer server,
			String[] projectNames, String local, String targetRoot, String version, String libFile) {
		String[] cmds = new String[projectNames.length];
		for (int i = 0; i < projectNames.length; i++) {
			// 上传的文件
			String localFile = local + File.separator + projectNames[i] + File.separator + projectNames[i] + "-" + version;
			// 上传的路径
			String targetPath = targetRoot + FILE_DELIMITER + projectNames[i] + FILE_DELIMITER;
			// 配置文件
			String resourcesFile = local + File.separator + projectNames[i] + File.separator + FILE_RESOURCES;
			// 依赖包上传到lib
			String[] libFiles = StringUtils.split(libFile, ",");
			if (DeployUtils.isMac()) {
				// mac scp方式
				DeployMethod.deploy(server, localFile, targetPath);
				DeployMethod.deploy(server, resourcesFile, targetPath);
				for (String l : libFiles) {
					DeployMethod.deploy(server, local + File.separator + FILE_LIB + File.separator + l + "-" + version, 
							targetRoot + FILE_DELIMITER + FILE_LIB + FILE_DELIMITER);
				}
			} else {
				// windows scp方式
				DeployUtils.uploadFileMap(con, new String[] { localFile } , targetPath);
				
				// resource
				File[] files = new File(resourcesFile).listFiles();
				for (File f : files) {
					if (!f.isDirectory()) {
						DeployUtils.uploadFileMap(con, new String[] { f.getPath() } , targetPath + FILE_RESOURCES + FILE_DELIMITER);
					}
				}
				
				// lib
				for (String l : libFiles) {
					DeployUtils.uploadFileMap(con, new String[] { local + File.separator + FILE_LIB + File.separator + l + "-" + version },
							targetRoot + FILE_DELIMITER + FILE_LIB + FILE_DELIMITER);
				}
			}
			// 准备执行的命令
			cmds[i] = targetRoot + FILE_DELIMITER + projectNames[i] + FILE_DELIMITER + "start.sh";
		}
		return cmds;
	}
}
