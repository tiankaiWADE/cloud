package com.bazl.dna.gateway.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bazl.dna.common.ResponseData;
import com.bazl.dna.gateway.constants.GatewayConstants;

/**
 * 异常处理
 * @author zhaoyong
 *
 */
@ResponseBody
@ControllerAdvice
public class GatewayErrorWebExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GatewayErrorWebExceptionHandler.class);

	private static final String ERROR_MESSAGE = "服务器异常，请联系管理员!";

	/**
	 * 构造方法
	 */
	public GatewayErrorWebExceptionHandler() {
		super();
	}

	/**
	 * 异常处理
	 * @param request 请求对象
	 * @param e 异常
	 * @return ResponseData
	 */
	@ExceptionHandler(value = Exception.class)
	public ResponseData exceptionHandler(HttpServletRequest request, Exception e) {
		LOGGER.error("Error:", e);
		Map<String, Object> result = getErrorAttributes(request, e);
		return new ResponseData(HttpStatus.INTERNAL_SERVER_ERROR.value(), ERROR_MESSAGE, result);
	}

	private Map<String, Object> getErrorAttributes(HttpServletRequest request, Exception error) {
        Map<String, Object> errorAttributes = new HashMap<>(8);
        errorAttributes.put(GatewayConstants.CODE, HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorAttributes.put(GatewayConstants.METHOD, request.getMethod());
        errorAttributes.put(GatewayConstants.PATH, request.getRequestURI());

        errorAttributes.put(GatewayConstants.MESSAGE, ERROR_MESSAGE);
        errorAttributes.put(GatewayConstants.RESULT, error.getMessage());
        return errorAttributes;
    }
}
