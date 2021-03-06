package com.bazl.dna.generator.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bazl.dna.common.ResponseData;
import com.bazl.dna.common.auth.AuthUser;
import com.bazl.dna.common.auth.annotation.CurrentUser;
import com.bazl.dna.common.constants.PublicConstants;
import com.bazl.dna.controller.BaseController;
import com.bazl.dna.generator.entity.GenTable;
import com.bazl.dna.generator.entity.GenTableColumn;
import com.bazl.dna.generator.service.IGenTableColumnService;
import com.bazl.dna.generator.service.IGenTableService;
import com.bazl.dna.util.ConvertString;
import com.google.common.collect.Maps;

/**
 * 代码生成 操作处理
 *
 * @author zhaoyong
 */
@RestController
@RequestMapping("/gen")
public class GeneratorController extends BaseController {

	@Autowired
	private IGenTableService genTableService;

	@Autowired
	private IGenTableColumnService genTableColumnService;

	/**
	 * 查询代码生成列表
	 */
	@PostMapping("/list")
	public ResponseData genList(GenTable genTable) {
		List<GenTable> list = genTableService.selectGenTableList(genTable);
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.PARAM_LIST, list);
		result.put(PublicConstants.PARAM_COUNT, list.size());
		return new ResponseData(result);
	}

	/**
	 * 修改代码生成业务
	 */
	@GetMapping(value = "/{tableId}")
	public ResponseData getInfo(@PathVariable Long tableId) {
		GenTable table = genTableService.selectGenTableById(tableId);
		List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(tableId);
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.MSG, table);
		result.put(PublicConstants.PARAM_LIST, list);
		return new ResponseData(result);
	}

	/**
	 * 查询数据库列表
	 */
	@PostMapping("/db/list")
	public ResponseData dataList(GenTable genTable) {
		List<GenTable> list = genTableService.selectDbTableList(genTable);
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.PARAM_LIST, list);
		result.put(PublicConstants.PARAM_COUNT, list.size());
		return new ResponseData(result);
	}

	/**
	 * 查询数据表字段列表
	 */
	@GetMapping(value = "/column/{tableId}")
	public ResponseData columnList(@PathVariable Long tableId) {
		List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(tableId);
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.PARAM_LIST, list);
		result.put(PublicConstants.PARAM_COUNT, list.size());
		return new ResponseData(result);
	}

	/**
	 * 导入表结构（保存）
	 */
	@PostMapping("/importTable")
	public ResponseData importTableSave(@CurrentUser AuthUser authUser, String tables) {
		String operName = authUser == null ? null : authUser.getUserName();
		String[] tableNames = ConvertString.toStrArray(tables);
		// 查询表信息
		List<GenTable> tableList = genTableService.selectDbTableListByNames(tableNames);
		genTableService.importGenTable(tableList, operName);

		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, PublicConstants.SUCCESS_CODE);
		return new ResponseData(result);
	}

	/**
	 * 修改保存代码生成业务
	 */
	@PutMapping
	public ResponseData editSave(@Validated @RequestBody GenTable genTable) {
		genTableService.validateEdit(genTable);
		genTableService.updateGenTable(genTable);

		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, PublicConstants.SUCCESS_CODE);
		return new ResponseData(result);
	}

	/**
	 * 删除代码生成
	 */
	@DeleteMapping("/{tableIds}")
	public ResponseData remove(@PathVariable Long[] tableIds) {
		genTableService.deleteGenTableByIds(tableIds);
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, PublicConstants.SUCCESS_CODE);
		return new ResponseData(result);
	}

	/**
	 * 预览代码
	 */
	@GetMapping("/preview/{tableId}")
	public ResponseData preview(@PathVariable("tableId") Long tableId) {
		Map<String, String> dataMap = genTableService.previewCode(tableId);
		return new ResponseData(dataMap);
	}

	/**
	 * 生成代码（下载方式）
	 */
	@GetMapping("/download/{tableName}")
	public void download(HttpServletResponse response, @PathVariable("tableName") String tableName) {
		byte[] data = genTableService.downloadCode(tableName);
		genCode(response, data);
	}

	/**
	 * 生成代码（自定义路径）
	 */
	@GetMapping("/genCode/{tableName}")
	public ResponseData genCode(@PathVariable("tableName") String tableName) {
		genTableService.generatorCode(tableName);
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, PublicConstants.SUCCESS_CODE);
		return new ResponseData(result);
	}

	/**
	 * 同步数据库
	 */
	@GetMapping("/synchDb/{tableName}")
	public ResponseData synchDb(@PathVariable("tableName") String tableName) {
		genTableService.synchDb(tableName);
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, PublicConstants.SUCCESS_CODE);
		return new ResponseData(result);
	}

	/**
	 * 批量生成代码
	 */
	@GetMapping("/batchGenCode")
	public void batchGenCode(HttpServletResponse response, String tables) {
		String[] tableNames = ConvertString.toStrArray(tables);
		byte[] data = genTableService.downloadCode(tableNames);
		genCode(response, data);
	}

	/**
	 * 生成zip文件
	 */
	private void genCode(HttpServletResponse response, byte[] data) {
		try {
			response.reset();
			response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
			response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Generator.zip\"");
			response.setContentLength(data.length);
			response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
			IOUtils.write(data, response.getOutputStream());
		} catch (IOException e) {
			LOGGER.error("batchGenCode error: ", e);
		}
	}
}
