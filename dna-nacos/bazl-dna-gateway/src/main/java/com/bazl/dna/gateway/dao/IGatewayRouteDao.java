package com.bazl.dna.gateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bazl.dna.gateway.entity.GatewayRoute;

/**
 * 路由网关接口
 * @author zhaoyong
 */
public interface IGatewayRouteDao extends JpaRepository<GatewayRoute, String>, JpaSpecificationExecutor<GatewayRoute> {

}
