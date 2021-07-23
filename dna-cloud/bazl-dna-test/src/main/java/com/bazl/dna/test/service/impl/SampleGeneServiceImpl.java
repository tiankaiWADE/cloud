package com.bazl.dna.test.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bazl.dna.common.exception.DnaRuntimeException;
import com.bazl.dna.test.dao.ISampleGeneDao;
import com.bazl.dna.test.entity.SampleGene;
import com.bazl.dna.test.mapper.SampleGeneMapper;
import com.bazl.dna.test.service.ISampleGeneService;

/**
 * ISampleGeneService实现类
 * @author zhaoyong
 */
@Service
public class SampleGeneServiceImpl implements ISampleGeneService {
	
	@Autowired
	private ISampleGeneDao sampleGeneDao;
	
	@Autowired
	private SampleGeneMapper mapper;

	@Override
	@Transactional(rollbackFor = DnaRuntimeException.class)
	public SampleGene save(SampleGene entity) {
		return sampleGeneDao.save(entity);
	}

	@Override
	public SampleGene getById(String id) {
		Optional<SampleGene> optional = sampleGeneDao.findById(id);
		return optional.orElse(null);
	}

	@Override
	@Transactional(rollbackFor = DnaRuntimeException.class)
	public void delete(String id) {
		sampleGeneDao.deleteById(id);
	}

	@Override
	public List<Map<String, Object>> findGrantList(Map<String, Object> map) {
		return mapper.findGrantList(map);
	}

}
