package com.bazl.dna.websocket.config;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;

/**
 * WebSocket监听
 * @author zhaoyong
 */
@Component
public class WebSocketInterceptor implements HandshakeInterceptor {

	/**
	 * handler处理前调用,attributes属性最终在WebSocketSession里,可能通过webSocketSession.getAttributes().get(key值)获得
	 */
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) {
		System.out.println("握手开始");
		// 获得请求参数
		HashMap<String, String> paramMap = HttpUtil.decodeParamMap(request.getURI().getQuery(), StandardCharsets.UTF_8.name());
		String token = paramMap.get("token");
		String user = paramMap.get("user");
		if (StrUtil.isNotBlank(user)) {
			// 放入属性域
			attributes.put("token", token);
			attributes.put("user", user);
			System.out.println("用户  " + user + " 握手成功！通道 " + token);
			return true;
		}
		System.out.println("用户登录已失效");
		return false;
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {

	}
}
