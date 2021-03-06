package com.bazl.dna.sys.controller.file;

import java.io.File;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bazl.dna.common.ResponseData;
import com.bazl.dna.common.exception.DnaRuntimeException;
import com.bazl.dna.controller.BaseController;
import com.bazl.dna.sys.client.IFileServiceClient;
import com.bazl.dna.util.DataUtils;

/**
 * 文件处理
 * @author zhaoyong
 */
@RefreshScope
@RestController
@RequestMapping("/file")
public class FileController extends BaseController {

	@Autowired
	private IFileServiceClient fileServiceClient;

	/**
	 * 上传文件
	 * 需要指定
	 * 		类型: Content-Type: multipart/form-data
	 * 		方式: Post
	 * 		参数: @RequestPart
	 * @param multipartFile 文件
	 * @return ResponseData
	 */
	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseData<Map<String, Object>> uploadFile(@RequestPart("multipartFile") MultipartFile multipartFile) {
		return fileServiceClient.upload(multipartFile);
	}

	/**
	 * 下载
	 * @param fileName 文件名称
	 * @param delete 是否删除
	 * @return ResponseEntity
	 */
	@GetMapping("/download")
	public ResponseEntity<byte[]> download(String fileName, boolean delete) {
		try {
			File file = new File(filePath + File.separator + fileName);
			ResponseEntity<byte[]> result = DataUtils.download(file, fileName);
			if (delete) {
				FileUtils.deleteQuietly(file);
			}
			return result;
		} catch (Exception e) {
			throw new DnaRuntimeException("下载文件异常!");
		}
	}

}
