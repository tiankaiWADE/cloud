package com.bazl.dna.test.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.bazl.dna.common.ResponseData;
import com.bazl.dna.common.feign.config.FeignSupportConfig;
import com.bazl.dna.test.client.fallback.FileServiceFallback;

/**
 * 文件服务调用
 * @author zhaoyong
 *
 */
@FeignClient(value = "bazl-dna-files", fallback = FileServiceFallback.class,
		configuration = FeignSupportConfig.class)
public interface IFileServiceClient {

	/**
	 * 上传文件
	 * 		类型: Content-Type: multipart/form-data
	 * 		方式: Post
	 * 		参数: @RequestPart("name")
	 *
	 * 		服务端接收方式: @RequestBody
	 * @param file 上传文件
	 * @return ResponseData
	 */
	@PostMapping(value="/fdfs/uploadMultipartFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	ResponseData upload(@RequestPart("file") MultipartFile file);

}
