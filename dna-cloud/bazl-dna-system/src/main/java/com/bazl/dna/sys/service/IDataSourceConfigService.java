package com.bazl.dna.sys.service;

import org.springframework.data.domain.Page;

import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.sys.entity.DataSourceConfig;

/**
 * 数据源信息
 *
 * @author zhaoyong
 */
public interface IDataSourceConfigService {

    /**
     * 查询分页列表
     *
     * @param paramJson 参数
     * @return Page
     */
    Page<DataSourceConfig> pageList(JSONObject paramJson);

    /**
     * 查询详情
     *
     * @param id 编号
     * @return DataSourceConfig
     */
    DataSourceConfig getById(String id);

    /**
     * 获取连接名称
     *
     * @param connectName 连接名称
     * @return DataSourceConfig
     */
    DataSourceConfig getConnectName(String connectName);

    /**
     * 保存
     *
     * @param entity 对象
     * @return DataSourceConfig
     */
    DataSourceConfig save(DataSourceConfig entity);

    /**
     * 删除
     *
     * @param id 编号
     * @return int
     */
    int deleteById(String id);

}
