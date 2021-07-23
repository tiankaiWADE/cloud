package com.bazl.dna.test.config;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.bazl.dna.datasource.config.DataSourceConfig;
import com.bazl.dna.datasource.constants.DataSourceConstants;

/**
 * 动态数据源拦截器 服务中请添加@Aspect
 * 
 * @author zhaoyong
 *
 */
@Component
public class DynamicDataSourceAspect {

	@Before("execution(* com.bazl.dna.test.service..*.*(..))")
	public void before(JoinPoint point) {
		String dbType = DataSourceConstants.DataSourceType.PRIMARY.name();
		Object[] args = point.getArgs();
		for (Object arg : args) {
			// 判断拦截参数 如果包含则切换数据源
			if (arg != null && StringUtils.contains(arg.toString(), DataSourceConstants.DYNAMIC_DATA_TYPE)) {
				dbType = StringUtils.substringAfterLast(arg.toString(), DataSourceConstants.DYNAMIC_DATA_TYPE);
			}
		}
		DataSourceConfig.setDataSource(dbType);
	}

	@After("execution(* com.bazl.dna.test.service..*.*(..))")
	public void after(JoinPoint point) {
		DataSourceConfig.clearDataSource();
	}
}
