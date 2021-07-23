package com.bazl.dna.sys.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bazl.dna.common.ResponseData;
import com.bazl.dna.sys.client.fallback.MonitorServiceClientFallback;

/**
 * 监控处理接口
 * @author zhaoyong
 */
@FeignClient(value = "bazl-dna-monitor", fallback = MonitorServiceClientFallback.class)
public interface IMonitorServiceClient {

	/**
	 * 根据id获取任务信息
	 * @param id 任务编号
	 * @return ResponseData
	 */
	@GetMapping(value="/task/{id}")
	ResponseData<Object> getInfo(@PathVariable("id") String id);

}
