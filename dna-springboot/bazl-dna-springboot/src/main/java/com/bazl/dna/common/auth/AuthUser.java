/*
 *    Copyright 2016 10gen Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.bazl.dna.common.auth;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.sys.constants.SysConstants;
import com.bazl.dna.util.RequestUtils;

/**
 * Jwt验证用户
 * @author zhaoyong
 */
public class AuthUser implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private final String id;
	private final String userName;
	private final String password;
	private final String isAdmin;
	private final String tokenType;
	private final List<String> roleList;
	private final List<String> jobList;
	private final List<String> orgList;

	private final String userType;

	@SuppressWarnings("unchecked")
	public AuthUser(JSONObject json) {
		this.id = json.getString(SysConstants.ID);
		this.userName = json.getString(SysConstants.USER_NAME);
		this.password = json.getString(SysConstants.PASS); 
		this.tokenType = json.getString(RequestUtils.TOKEN_TYPE);
		this.isAdmin = json.getString(SysConstants.IS_ADMIN);
		this.userType = json.getString(SysConstants.USER_TYPE);
		this.roleList = json.getObject(SysConstants.ROLES, List.class);
		this.jobList = json.getObject(SysConstants.JOBS, List.class);
		this.orgList = json.getObject(SysConstants.ORGS, List.class);
	}

	public String getId() {
		return id;
	}

	public String getTokenType() {
		return tokenType;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getUserType() {
		return userType;
	}

	public List<String> getRoleList() {
		return roleList;
	}

	public List<String> getJobList() {
		return jobList;
	}

	public List<String> getOrgList() {
		return orgList;
	}

}
