package com.bazl.dna.test.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vladmihalcea.hibernate.type.json.JsonStringType;

/**
 * SampleGene对象
 * @author zhaoyong
 */
@Entity
@Table(name = "test_sample_gene")
@TypeDef(name = "json", typeClass = JsonStringType.class)
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class SampleGene implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name = "sample_id", length = 64)
	private String sampleId;

	@Type(type = "json")
	@Column(name = "gene_info")
	private JSONObject geneInfo;
	
	public SampleGene() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSampleId() {
		return sampleId;
	}

	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}

	public JSONObject getGeneInfo() {
		return geneInfo;
	}

	public void setGeneInfo(JSONObject geneInfo) {
		this.geneInfo = geneInfo;
	}

}
