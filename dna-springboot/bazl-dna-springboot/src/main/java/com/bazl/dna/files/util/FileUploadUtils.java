package com.bazl.dna.files.util;

import java.io.File;
import java.sql.Timestamp;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.bazl.dna.files.entity.FastdfsFile;
import com.bazl.dna.util.DataUtils;
import com.bazl.dna.util.SnowFlakeUtils;

/**
 * 本地文件上传
 * @author zhaoyong
 *
 */
public class FileUploadUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadUtils.class);

	private static final String FILE_HTTP = "http://";
	private static final String FILE_DELIMITER = "/";
	
	private FileUploadUtils() {
	}

	/**
	 * 上传文件
	 * 
	 * @param file 文件
	 * @param fileWeb web访问地址
	 * @param filePath 文件上传根目录
	 * @param uploadDir 上传的文件夹
	 * @param maxLength 文件长度
	 * @return FastdfsFile
	 */
	public static FastdfsFile upload(MultipartFile file, String fileWeb, String filePath, String uploadDir, long maxLength) {
		try {
			long fileNameLength = file.getSize();
			if (fileNameLength > maxLength) {
				LOGGER.error("文件长度过大，清重新上传文件");
				return null;
			}

			String fileName = extractFilename(file);
			File desc = getAbsoluteFile(filePath, uploadDir, fileName);
			file.transferTo(desc);
			// http url
			return getPathFileName(file, fileWeb, uploadDir, fileName);
		} catch (Exception e) {
			LOGGER.error("upload error:", e);
		}
		return null;
	}

	private static String extractFilename(MultipartFile file) {
		String extension = DataUtils.getExtension(file.getOriginalFilename());
		return new SnowFlakeUtils(1, 1).nextId() + "." + extension;
	}

	private static File getAbsoluteFile(String filePath, String uploadDir, String fileName) {
		File desc = new File(filePath + File.separator + uploadDir + File.separator + fileName);

		if (!desc.exists() && !desc.getParentFile().exists()) {
			desc.getParentFile().mkdirs();
		}
		return desc;
	}

	private static FastdfsFile getPathFileName(MultipartFile file, String fileWeb, String uploadDir, String fileName) {
		String fileUrl = fileWeb + FILE_DELIMITER + uploadDir + FILE_DELIMITER + fileName;

		String ss = fileUrl.replace(FILE_HTTP, "");
		String fastUrl = StringUtils.substringBefore(ss, FILE_DELIMITER);
		String id = ss.replace(fastUrl + FILE_DELIMITER, "");
		// save file
		FastdfsFile fileEntity = new FastdfsFile();
		fileEntity.setId(id);
		fileEntity.setFileName(fileName);
		fileEntity.setFastdfsUrl(FILE_HTTP + fastUrl);
		fileEntity.setFilePath(fileUrl);
		fileEntity.setDisplayName(file.getOriginalFilename());
		fileEntity.setFileSuffix(DataUtils.getExtension(file.getOriginalFilename()));
		fileEntity.setSize(DataUtils.convertFileSize(file.getSize()));
		fileEntity.setDirectory(false);
		fileEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
		return fileEntity;
	}

	/**
	 * 下载文件
	 * @param filePath 文件根路径
	 * @param fileUrl 文件地址
	 * @return byte[]
	 */
	public static byte[] download(String filePath, String fileUrl) {
		String s = StringUtils.substringAfter(fileUrl, "/");
		String path = filePath + FILE_DELIMITER + s;
		File file = new File(path);
		try {
			return FileUtil.readAsByteArray(file);
		} catch (Exception e) {
			LOGGER.error("download error:", e);
		}
		return new byte[0];	
	}

}
