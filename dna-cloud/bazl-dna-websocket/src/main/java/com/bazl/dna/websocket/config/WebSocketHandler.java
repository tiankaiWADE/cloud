package com.bazl.dna.websocket.config;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import com.google.common.collect.Maps;

/**
 * WebSocket监听
 * @author zhaoyong
 */
@Component
public class WebSocketHandler extends AbstractWebSocketHandler {

	/**
	 * 存储sessionId和webSocketSession
	 * 需要注意的是，webSocketSession没有提供无参构造，不能进行序列化，也就不能通过redis存储
	 * 在分布式系统中，要想别的办法实现webSocketSession共享
	 */
	private static Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();
	private static Map<String, Map<String, String>> userMap = new ConcurrentHashMap<>();

	/**
	 * webSocket连接创建后调用
	 *
	 * @param session session
	 * @throws Exception 异常
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// 获取参数
		String user = String.valueOf(session.getAttributes().get("user"));
		String token = String.valueOf(session.getAttributes().get("token"));
		Map<String, String> users = userMap.get(token);
		if (users == null) {
			users = Maps.newHashMap();
		}
		users.put(user, session.getId());
		userMap.put(token, users);
		sessionMap.put(session.getId(), session);
		String jionMessage = "jion";
		sendMessage(token, user, jionMessage);
	}

	/**
	 * 接收到消息会调用
	 */
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		if (message instanceof TextMessage) {
			sendMessage(session.getAttributes().get("token").toString(), session.getAttributes().get("user").toString(),
					message.getPayload().toString());
		} else if (message instanceof BinaryMessage) {

		} else if (message instanceof PongMessage) {

		} else {
			System.out.println("Unexpected WebSocket message type: " + message);
		}
	}

	/**
	 * 连接出错会调用
	 */
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) {
		sessionMap.remove(session.getId());
	}

	/**
	 * 连接关闭会调用
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
		sessionMap.remove(session.getId());
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

	/**
	 * 后端发送消息
	 *
	 * @throws IOException 异常
	 */
	public void sendMessage(String token, String userName, String message) throws IOException {
		Map<String, String> users = userMap.get(token);
		if (users != null) {
			for (String key : users.keySet()) {
				WebSocketSession session = sessionMap.get(users.get(key));
				session.sendMessage(new TextMessage(userName + "&&" + message));
			}
		}
	}
}
