package com.bazl.dna.swagger.controller.files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bazl.dna.common.ResponseData;
import com.bazl.dna.controller.BaseController;
import com.bazl.dna.swagger.client.IFileServiceClient;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 文件处理
 * @author zhaoyong
 */
@RefreshScope
@RestController
@RequestMapping("/file")
@Api(value = "文件处理", tags = "文件中心接口")
public class FileController extends BaseController {

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
	@ApiParam(value = "multipartFile", required = true)
	@ApiOperation(value = "上传文件", notes = "上传文件到文件中心 bazl-dna-files",
		httpMethod = "POST", response = ResponseData.class)
	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseData uploadFile(@RequestPart("multipartFile") MultipartFile multipartFile) {
		return client.upload(multipartFile);
	}
}
