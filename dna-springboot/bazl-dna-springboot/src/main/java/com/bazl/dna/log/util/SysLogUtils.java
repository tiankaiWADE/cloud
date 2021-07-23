package com.bazl.dna.log.util;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bazl.dna.sys.entity.SysLog;
import com.bazl.dna.util.DataUtils;

/**
 * SysLog对象
 * 
 * @author zhaoyong
 *
 */
public final class SysLogUtils {
	
	private SysLogUtils() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	/**
	 * SysLog对象
	 * @return SysLog
	 */
	public static SysLog getSysLog() {
		HttpServletRequest request = ((ServletRequestAttributes) Objects
				.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
		SysLog sysLog = new SysLog();
		sysLog.setOperationIp(DataUtils.getIpAddress(request));
		sysLog.setOperationPath(request.getRequestURI());
		sysLog.setMethodName(request.getMethod());
		return sysLog;
	}

}
