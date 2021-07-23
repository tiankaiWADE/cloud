package com.bazl.dna.files.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bazl.dna.files.entity.FastdfsFile;

/**
 * fastdfs文件接口
 * @author zhaoyong
 */
public interface IFastdfsFileDao extends JpaRepository<FastdfsFile, String>, JpaSpecificationExecutor<FastdfsFile> {

}
