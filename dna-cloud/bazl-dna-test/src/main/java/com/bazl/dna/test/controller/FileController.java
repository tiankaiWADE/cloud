package com.bazl.dna.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bazl.dna.common.ResponseData;
import com.bazl.dna.test.client.IFileServiceClient;

/**
 * 文件处理
 * @author zhaoyong
 */
@RefreshScope
@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
	private IFileServiceClient client;

	/**
	 * 上传文件
	 * 需要指定
	 * 		类型: Content-Type: multipart/form-data
	 * 		方式: Post
	 * 		参数: @RequestPart
	 * @param multipartFile 上传文件
	 * @return ResponseData
	 */
	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseData uploadFile(@RequestPart("multipartFile") MultipartFile multipartFile) {
		return client.upload(multipartFile);
	}
}
