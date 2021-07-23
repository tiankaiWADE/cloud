package com.bazl.dna.dfs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bazl.dna.dfs.entity.FastdfsFile;

/**
 * 文件接口
 * @author zhaoyong
 */
public interface IFastdfsFileDao extends JpaRepository<FastdfsFile, String>, JpaSpecificationExecutor<FastdfsFile> {

}
