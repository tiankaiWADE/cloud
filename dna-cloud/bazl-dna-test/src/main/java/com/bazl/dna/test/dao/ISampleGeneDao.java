package com.bazl.dna.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bazl.dna.test.entity.SampleGene;

/**
 * SampleGene接口
 * @author zhaoyong
 */
public interface ISampleGeneDao extends JpaRepository<SampleGene, String>, JpaSpecificationExecutor<SampleGene> {

}
