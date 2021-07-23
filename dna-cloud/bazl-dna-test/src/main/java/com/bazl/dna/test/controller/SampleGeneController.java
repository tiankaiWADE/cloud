package com.bazl.dna.test.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bazl.dna.common.ResponseData;
import com.bazl.dna.test.entity.SampleGene;
import com.bazl.dna.test.service.ISampleGeneService;

/**
 * SampleGene控制器
 * @author zhaoyong
 */
@RestController
@RequestMapping("/sampleGene")
public class SampleGeneController {

	@Autowired
	private ISampleGeneService service;
	
	@PostMapping("save")
	private ResponseData save(@RequestBody SampleGene entity) {
		SampleGene result = service.save(entity);
		return new ResponseData(result);
	}
	
	@GetMapping("get")
	private ResponseData get(String id) {
		SampleGene result = service.getById(id);
		return new ResponseData(result);
	}
	
	@GetMapping("delete")
	private ResponseData delete(String id) {
		service.delete(id);
		return new ResponseData();
	}
	
	@PostMapping("list")
	private ResponseData list(@RequestBody Map<String, Object> map) {
		//查询基因座至少大于8个参数
		if (map.keySet().size() > 8) {
			List<Map<String, Object>> list = service.findGrantList(map);
			return new ResponseData(list);
		}
		return new ResponseData(); 
	}
}
