package com.bazl.dna.dfs.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.common.ResponseData;
import com.bazl.dna.controller.BaseController;
import com.bazl.dna.dfs.client.FastDFSClient;
import com.bazl.dna.dfs.entity.FastdfsFile;
import com.bazl.dna.dfs.service.IFastdfsFileService;
import com.bazl.dna.util.DataUtils;
import com.github.tobato.fastdfs.domain.fdfs.FileInfo;

/**
 * 文件上传
 * @author zhaoyong
 */
@RestController
@RequestMapping("/fdfs")
public class FastDFSFileController extends BaseController {

	@Autowired
	private FastDFSClient fastdfsClient;
	
	@Autowired
	private IFastdfsFileService fastdfsFileService;

	/**
	 * 查询列表
	 *
	 * @param paramJson 查询参数
	 * @return ResponseData
	 */
	@PostMapping("/list")
	public ResponseData list(@RequestBody JSONObject paramJson) {
		Page<FastdfsFile> list = fastdfsFileService.pageList(paramJson);
		return new ResponseData(list);
	}
	
	/**
	 * 文件上传
	 * 
	 * @param file MultipartFile
	 * @return ResponseData
	 */
	@PostMapping("/uploadMultipartFile")
	public ResponseData uploadMultipartFile(@RequestBody MultipartFile file) {
		try {
			if (file != null) {
				String url = fastdfsClient.uploadFile(file);
				return new ResponseData(url);
			}
		} catch (Exception ex) {
			LOGGER.error("调用FastDFS Client上传文件失败!", ex);
			return new ResponseData("上传失败! ErrorMsg: " + ex.getMessage());
		}
		return new ResponseData();
	}

	/**
	 * 文件上传 File 类型
	 * 
	 * @param file File
	 * @return ResponseData
	 */
	@PostMapping("/uploadFile")
	public ResponseData uploadFile(@RequestBody File file) {
		try {
			if (file != null) {
				String url = fastdfsClient.uploadFile(file);
				return new ResponseData(url);
			}
		} catch (Exception ex) {
			LOGGER.error("调用FastDFS Client上传文件失败!", ex);
			return new ResponseData("上传失败! ErrorMsg: " + ex.getMessage());
		}
		return new ResponseData();
	}

	/**
	 * 文件下载
	 * 
	 * @param fileUrl  url 开头从组名开始
	 * @param filename 文件名称
	 * @param response HttpServletResponse
	 */
	@GetMapping("/download")
	public void download(@RequestParam("fileUrl") String fileUrl, String filename, 
			HttpServletResponse response) {
		byte[] data = fastdfsClient.download(fileUrl);
		if (DataUtils.isEmpty(filename)) {
			filename = StringUtils.substringAfterLast(fileUrl, "/");
		}

		try {
			response.setCharacterEncoding(StandardCharsets.UTF_8.name());
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
					"attachment;filename=" + URLEncoder.encode(filename, StandardCharsets.UTF_8.name()));
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("download UnsupportedEncodingException:", e);
		}

		// 写出
		try (ServletOutputStream outputStream = response.getOutputStream()) {
			IOUtils.write(data, outputStream);
		} catch (IOException e) {
			LOGGER.error("download IOException:", e);
		}

	}

	/**
	 * 文件流下载
	 * 
	 * @param fileUrl url 开头从组名开始
	 * @return byte[]
	 */
	@GetMapping("/downloadFile")
	public byte[] downloadFile(@RequestParam("fileUrl") String fileUrl) {
		return fastdfsClient.download(fileUrl);
	}

	/**
	 * 删除文件
	 * 
	 * @param ids 文件地址
	 * @return ResponseData
	 */
	@DeleteMapping("/delete")
	public ResponseData delete(@RequestBody String... ids) {
		if (ids != null) {
			for (String id : ids) {
				fastdfsClient.deleteFile(id);
			}
		}
		return new ResponseData();
	}

	/**
	 * 获取文件
	 * 
	 * @param groupName 组名称
	 * @param path      地址
	 * @return ResponseData
	 */
	@GetMapping("/getFile")
	public ResponseData getFile(@RequestParam("groupName") String groupName, @RequestParam("path") String path) {
		FileInfo result = fastdfsClient.getFile(groupName, path);
		return new ResponseData(result);
	}

}
