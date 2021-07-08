package com.bazl.dna.interceptor;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;

import com.alibaba.fastjson.JSON;
import com.bazl.dna.common.ResponseData;
import com.bazl.dna.common.exception.DnaRuntimeException;
import com.bazl.dna.common.jwt.JwtUtils;
import com.bazl.dna.log.event.SysLogEvent;
import com.bazl.dna.sys.entity.SysLog;
import com.bazl.dna.util.DataUtils;

/**
 * Token拦截器
 *
 * @author zhaoyong
 *
 */
public class TokenInterceptor implements HandlerInterceptor {

	/**
	 * 项目密钥
	 */
	@Value("${ACCESS_TOKEN_SECRET}")
	private String secret;

	/**
	 * 白名单
	 */
	@Value("${no_author}")
	private String requestPath;
	
	private final ApplicationEventPublisher publisher;
	
	/**
	 * Constructor
	 * @param publisher ApplicationEventPublisher
	 */
	public TokenInterceptor(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	/**
	 * Handler
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) {

		// 请求地址
		String uri = request.getRequestURI();
		// 记录请求日志
		requestLog(request);

		// 验证白名单
		if (requestPath != null) {
			List<String> paths = Arrays.asList(requestPath.split(","));
			if (paths.stream().anyMatch(uri::contains)) {
				return true;
			}
		}

		// 验证token
		String auth = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (!DataUtils.isEmpty(auth)) {
			// 判断是否有效
			boolean flag = JwtUtils.validateToken(auth, secret);
			if (flag) {
				return true;
			}
		}

		// 返回错误信息
		ResponseData responseData = new ResponseData(HttpStatus.SC_UNAUTHORIZED, "token验证不通过");
		response.setStatus(HttpStatus.SC_UNAUTHORIZED);
		response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		// 写出
		try {
			response.getOutputStream().write(JSON.toJSONString(responseData).getBytes());
		} catch (IOException e) {
			throw new DnaRuntimeException(e);
		}
		return false;
	}

	/**
	 * 记录日志
	 *
	 * @param request 请求
	 */
	private void requestLog(HttpServletRequest request) {
		// 请求ip
		String ip = DataUtils.getIpAddress(request);
		// 请求地址
		String uri = request.getRequestURI();
		// 请求方法
		String methodName = request.getMethod();
		// 除get请求一律保存日志
		if (!HttpMethod.GET.matches(methodName)) {
			SysLog sysLog = new SysLog();
			sysLog.setCreateTime(new Timestamp(System.currentTimeMillis()));
			sysLog.setMethodName(methodName);
			sysLog.setOperationIp(ip);
			sysLog.setOperationPath(uri);
			sysLog.setOperationName(StringUtils.substringBefore(uri.substring(1), "/"));
			this.publisher.publishEvent(new SysLogEvent(sysLog));
		}
	}

}
