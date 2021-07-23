package com.bazl.dna.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 配置文件中no_author配置不需要验证的请求路径，如果是该请求路径，则进入下一个过滤器
 *
 * @author zhaoyong
 *
 */
@RefreshScope
@Component
public class RequestPathFilter extends ZuulFilter {

	private static final String IS_SUCCESS = "isSuccess";

	@Value("${no_author}")
	private String requestPath;

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String uri = request.getRequestURI();
		// 对该请求进行路由
		ctx.setSendZuulResponse(true);
		// 返回状态码
		ctx.setResponseStatusCode(HttpStatus.SC_OK);
		if (requestPath != null) {
			String[] paths = requestPath.split(",");
			boolean isSuccess = true;
			for (String path : paths) {
				if (uri.contains(path)) {
					isSuccess = false;
					break;
				}
			}
			// 设值，让下一个Filter看到当前Filter的状态
			ctx.set(RequestPathFilter.IS_SUCCESS, isSuccess);
		} else {
			// 如果没有no_author属性，则默认所有请求都需要进入到下一个token验证过滤
			ctx.set(RequestPathFilter.IS_SUCCESS, true);
		}
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

}
