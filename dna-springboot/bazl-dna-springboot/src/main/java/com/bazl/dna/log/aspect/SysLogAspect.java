package com.bazl.dna.log.aspect;

import java.sql.Timestamp;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpMethod;

import com.alibaba.fastjson.JSON;
import com.bazl.dna.log.event.SysLogEvent;
import com.bazl.dna.log.util.SysLogUtils;
import com.bazl.dna.sys.entity.SysLog;
import com.bazl.dna.util.DataUtils;

/**
 * SysLog Aspect日志拦截器 使用时@sysLog("名称")
 *
 * @author zhaoyong
 *
 */
@Aspect
public class SysLogAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(SysLogAspect.class);

	private final ApplicationEventPublisher publisher;

	/**
	 * Constructor
	 * @param publisher ApplicationEventPublisher
	 */
	public SysLogAspect(ApplicationEventPublisher publisher) {
		super();
		this.publisher = publisher;
	}

	/**
	 * Aspect around
	 * @param point ProceedingJoinPoint
	 * @param sysLog SysLog
	 * @return Object
	 */
	@Around("@annotation(sysLog)")
	public Object around(ProceedingJoinPoint point, com.bazl.dna.log.annotation.SysLog sysLog) {
		try {
			String className = point.getTarget().getClass().getName();
			String methodName = point.getSignature().getName();
			LOGGER.debug("[Class]:{},[Method]:{}", className, methodName);
			SysLog entity = SysLogUtils.getSysLog();
			entity.setOperationName(sysLog.value());
			if (HttpMethod.PUT.name().equals(methodName) || HttpMethod.POST.name().equals(methodName)) {
				String params = getParams(point.getArgs());
				entity.setOperationContext(StringUtils.substring(params, 0, 2000));
			}
			entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
			Object obj = point.proceed();
			this.publisher.publishEvent(new SysLogEvent(entity));
			return obj;
		} catch (Throwable e) {
			LOGGER.error("SysLogAspect error:", e);
		}
		return null;
	}

	private String getParams(Object[] paramsArray) {
		StringBuilder params = new StringBuilder();
		if (paramsArray != null && paramsArray.length > 0) {
			for (int i = 0; i < paramsArray.length; i++) {
				if (!DataUtils.isEmpty(paramsArray[i])) {
					Object jsonObj = JSON.toJSON(paramsArray[i]);
					if (!DataUtils.isEmpty(jsonObj))
						params.append(jsonObj.toString() + " ");
				}
			}
		}
		return params.toString().trim();
	}
}
