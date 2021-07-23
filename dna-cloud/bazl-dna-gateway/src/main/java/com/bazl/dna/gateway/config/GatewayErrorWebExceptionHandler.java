package com.bazl.dna.gateway.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.error.ErrorAttributeOptions.Include;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.bazl.dna.gateway.constants.GatewayConstants;
import com.bazl.dna.gateway.constants.GatewayErrorEnum;

/**
 * 异常处理
 * @author zhaoyong
 *
 */
public class GatewayErrorWebExceptionHandler extends DefaultErrorWebExceptionHandler {

	/**
	 * 构造方法
	 */
	public GatewayErrorWebExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties,
			ErrorProperties errorProperties, ApplicationContext applicationContext) {
		super(errorAttributes, resourceProperties, errorProperties, applicationContext);
	}

	@Override
	public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Throwable error = super.getError(request);
        Map<String, Object> errorAttributes = new HashMap<>(8);
        errorAttributes.put(GatewayConstants.CODE, super.getHttpStatus(
        		super.getErrorAttributes(request, ErrorAttributeOptions.of(Include.STACK_TRACE))));
        errorAttributes.put(GatewayConstants.METHOD, request.methodName());
        errorAttributes.put(GatewayConstants.PATH, request.path());
        
        	
        String description = GatewayErrorEnum.getDescription(error.getMessage());
        errorAttributes.put(GatewayConstants.MESSAGE, description + "异常，请联系管理员!");
        errorAttributes.put(GatewayConstants.RESULT, error.getMessage());
        return errorAttributes;
    }

    @Override
    public RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    @Override
    public int getHttpStatus(Map<String, Object> errorAttributes) {
        return HttpStatus.INTERNAL_SERVER_ERROR.value();
    }
}
