package com.bazl.dna.elasticsearch.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.common.ResponseData;
import com.bazl.dna.common.constants.ErrorCodes;
import com.bazl.dna.common.constants.ErrorInfo;
import com.bazl.dna.common.constants.PublicConstants;
import com.bazl.dna.controller.BaseController;
import com.bazl.dna.elasticsearch.entity.SampleGene;
import com.bazl.dna.elasticsearch.service.ISampleGeneElasticsearchService;
import com.bazl.dna.util.DataUtils;
import com.bazl.dna.util.GeneTransFormUtils;
import com.google.common.collect.Maps;

/**
 * Elasticsearch Controller
 *
 * @author zhaoyong
 *
 */
@RestController
@RequestMapping("/elasticsearch/sampleGene")
public class SampleGeneController extends BaseController {

	@Autowired
	private ISampleGeneElasticsearchService service;

	/**
	 * 保存
	 *
	 * @param paramJson 基因对象
	 * @return ResponseData
	 */
	@PutMapping("/save")
	public ResponseData save(@RequestBody JSONObject paramJson) {
		SampleGene sampleGene = JSON.toJavaObject(paramJson, SampleGene.class);
		if (sampleGene != null) {
			if (!sampleGene.getGeneList().isEmpty()) {
				// 基因型格式转换
				String jsonArray = JSON.toJSONString(sampleGene.getGeneList());
				sampleGene.setGeneInfo(GeneTransFormUtils.geneFormatString(jsonArray));
				String s = GeneTransFormUtils.geneTrimString(sampleGene.getGeneInfo().toJSONString());
				sampleGene.setMatchJson(JSON.parseObject(s));
			}
			SampleGene entity = service.save(sampleGene);
			Map<String, Object> result = Maps.newHashMap();
			result.put(PublicConstants.CODE, PublicConstants.SUCCESS_CODE);
			result.put(PublicConstants.MSG, entity);
			return new ResponseData(result);
		}
		return new ResponseData();
	}

	/**
	 * id查询
	 *
	 * @param id 编号
	 * @return ResponseData
	 */
	@GetMapping("/get/{id}")
	public ResponseData get(@PathVariable("id") String id) {
		if (!DataUtils.isEmpty(id)) {
			SampleGene sampleGene = service.findById(id);
			if (sampleGene != null && !DataUtils.isEmpty(sampleGene.getGeneInfo())) {
				// 基因型格式转换
				sampleGene.setGeneList(GeneTransFormUtils.geneFormatList(sampleGene.getGeneInfo().toJSONString()));
			}
			return new ResponseData(sampleGene);
		}
		return new ResponseData();
	}

	/**
	 * 删除
	 *
	 * @param ids 编号
	 * @return ResponseData
	 */
	@DeleteMapping("/delete")
	public ResponseData delete(@RequestBody String... ids) {
		if (ids == null) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		for (String id : ids) {
			service.deleteById(id);
		}
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, PublicConstants.SUCCESS_CODE);
		return new ResponseData(result);
	}

	/**
	 * 列表
	 *
	 * @param paramJson 查询条件参数
	 * @return ResponseData
	 */
	@PostMapping("/list")
	public ResponseData list(@RequestBody JSONObject paramJson) {
		Iterable<SampleGene> result = this.service.findList(paramJson);
		return new ResponseData(result);
	}

}
