package com.bazl.dna.elasticsearch.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.bazl.dna.common.constants.PublicConstants;
import com.bazl.dna.controller.BaseController;
import com.bazl.dna.elasticsearch.entity.SampleGene;
import com.bazl.dna.elasticsearch.service.ISampleGeneElasticsearchService;
import com.bazl.dna.gateway.constants.GatewayConstants;
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
	 * @return Map<String, Object>
	 */
	@PutMapping("/save")
	public Map<String, Object> save(@RequestBody JSONObject paramJson) {
		SampleGene sampleGene = JSON.toJavaObject(paramJson, SampleGene.class);
		
		Map<String, Object> result = Maps.newHashMap();
		result.put(GatewayConstants.CODE, PublicConstants.ERROR_CODE);
		
		if (sampleGene == null) {
			result.put(PublicConstants.MSG, "基因对象为空!");
			return result;
		}
		if (!sampleGene.getGeneList().isEmpty()) {
			// 基因型格式转换
			String jsonArray = JSON.toJSONString(sampleGene.getGeneList());
			sampleGene.setGeneInfo(GeneTransFormUtils.geneFormatString(jsonArray));
			String s = GeneTransFormUtils.geneTrimString(sampleGene.getGeneInfo().toJSONString());
			sampleGene.setMatchJson(JSON.parseObject(s));
		}
		SampleGene entity = service.save(sampleGene);
		result.put(PublicConstants.CODE, PublicConstants.SUCCESS_CODE);
		result.put(PublicConstants.MSG, entity);
		return result;
	}

	/**
	 * id查询
	 *
	 * @param id 编号
	 * @return SampleGene
	 */
	@GetMapping("/get/{id}")
	public SampleGene get(@PathVariable("id") String id) {
		SampleGene sampleGene = service.findById(id);
		if (sampleGene != null && !DataUtils.isEmpty(sampleGene.getGeneInfo())) {
			// 基因型格式转换
			sampleGene.setGeneList(GeneTransFormUtils.geneFormatList(sampleGene.getGeneInfo().toJSONString()));
		}
		return sampleGene;
	}

	/**
	 * 删除
	 *
	 * @param ids 编号
	 * @return Map<String, Object>
	 */
	@DeleteMapping("/delete")
	public Map<String, Object> delete(@RequestBody String... ids) {
		for (String id : ids) {
			service.deleteById(id);
		}
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, PublicConstants.SUCCESS_CODE);
		return result;
	}

	/**
	 * 列表
	 *
	 * @param paramJson 查询条件参数
	 * @return Page<SampleGene>
	 */
	@PostMapping("/list")
	public Page<SampleGene> list(@RequestBody JSONObject paramJson) {
		return this.service.findList(paramJson);
	}

}
