package com.bazl.dna.zuul.filter;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.zuul.jwt.JwtUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * token过滤器，所有请求在路由到各个服务之前就行过滤，验证token是否合法
 *
 * @author zhaoyong
 *
 */
@RefreshScope
@Component
public class TokenFilter extends ZuulFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(TokenFilter.class);

	private static final String IS_SUCCESS = "isSuccess";

	@Value("${ACCESS_TOKEN_SECRET}")
	private String secret;

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		// 获取Token
		String authToken = request.getHeader(HttpHeaders.AUTHORIZATION);
		// 验证token
		if (StringUtils.isNotBlank(authToken)) {
			// 验证token是否有效
			boolean flag = JwtUtils.validateToken(authToken, secret);
			if (flag) {
				ctx.setSendZuulResponse(true);
				ctx.setResponseStatusCode(HttpStatus.SC_OK);
				ctx.set(IS_SUCCESS, true);
				return null;
			}
		}
		// 不对该请求进行路由
		ctx.setSendZuulResponse(false);
		// 返回状态码
		ctx.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
		ctx.set(IS_SUCCESS, false);
		HttpServletResponse response = ctx.getResponse();
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		JSONObject json = new JSONObject();
		try {
			json.put("code", HttpStatus.SC_UNAUTHORIZED);
			json.put("message", "token验证不通过");
			response.getWriter().print(json.toString());
			ctx.setResponse(response);
			return null;
		} catch (Exception e) {
			LOGGER.error("Error TokenFilter: ", e);
		}
		return "";
	}

	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		return (boolean) ctx.get(IS_SUCCESS);
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
