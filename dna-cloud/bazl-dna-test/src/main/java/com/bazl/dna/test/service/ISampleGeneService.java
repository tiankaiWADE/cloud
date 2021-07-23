package com.bazl.dna.test.service;

import java.util.List;
import java.util.Map;

import com.bazl.dna.test.entity.SampleGene;

/**
 * 基因型接口
 * @author zhaoyong
 */
public interface ISampleGeneService {

	/**
	 * 保存
	 * @param entity 对象
	 * @return SampleGene
	 */
	SampleGene save(SampleGene entity);

	/**
	 * 获取对象
	 * @param id 主键
	 * @return SampleGene
	 */
	SampleGene getById(String id);

	/**
	 * 删除对象
	 * @param id 主键
	 */
	void delete(String id);

	/**
	 * 获取列表
	 * @param map 查询参数
	 * @return List
	 */
	List<Map<String, Object>> findGrantList(Map<String, Object> map);
}
