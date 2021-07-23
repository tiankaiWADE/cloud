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
package com.bazl.dna.util;

import javax.servlet.http.HttpServletRequest;

import com.bazl.dna.common.auth.AuthUser;

/**
 * JavaWebToken常用类
 *
 * @author zhaoyong
 */
public class RequestUtils {

	private static HttpServletRequest httpRequest;

	public static final String AUTH_USER = "authUser";

	public static final String ACCESS_TOKEN = "accessToken";

	public static final String ERROR_TOKEN_MESSAGE = "accessToken is null";

	public static final String ERROR_AUTH_USER_MESSAGE = "authUser is null";

	public static final String TOKEN_TYPE = "tokenType";

	public static final String EXPIRED = "Expired";

	private RequestUtils() {

	}

	/**
	 * get method
	 *
	 * @return HttpServletRequest
	 */
	public static HttpServletRequest getHttpRequest() {
		return httpRequest;
	}

	/**
	 * set method
	 *
	 * @param request HttpServletRequest
	 */
	public static void setHttpRequest(HttpServletRequest request) {
		httpRequest = request;
	}

	/**
	 * 获取用户对象
	 *
	 * @return AuthUser
	 */
	public static AuthUser getAuthUser() {
		if (httpRequest != null && httpRequest.getAttribute(AUTH_USER) != null) {
			return (AuthUser) httpRequest.getAttribute(AUTH_USER);
		}
		return null;
	}

	/**
	 * 获取token
	 *
	 * @return token字符串
	 */
	public static String getToken() {
		if (httpRequest != null && httpRequest.getAttribute(ACCESS_TOKEN) != null) {
			return httpRequest.getAttribute(ACCESS_TOKEN).toString();
		}
		return null;
	}

}
