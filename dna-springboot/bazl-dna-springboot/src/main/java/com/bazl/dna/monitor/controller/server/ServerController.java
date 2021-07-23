package com.bazl.dna.monitor.controller.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bazl.dna.controller.BaseController;
import com.bazl.dna.monitor.entity.Server;

/**
 * 服务器监控
 * 
 * @author zhaoyong
 */
@RestController
@RequestMapping("/monitor/server")
public class ServerController extends BaseController {

	/**
	 * 当前服务器信息
	 */
	@GetMapping("/index")
	public Server index() {
		Server server = new Server();
		server.copyTo();
		return server;
	}
}
