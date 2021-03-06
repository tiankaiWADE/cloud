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
package com.bazl.dna.zuul.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * JavaWebToken常用类
 *
 * @author zhaoyong
 */
public class JwtUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);

    private JwtUtils() {

    }

    /**
     * 通过Token获取token中的用户名
     *
     * @param token  令牌
     * @param secret 秘钥
     * @return String
     */
    public static String getUsernameFromToken(String token, String secret) {
        String username = null;
        try {
            final Claims claims = getClaimsFromToken(token, secret);
            if (claims != null) {
                username = claims.getSubject();
            }
        } catch (Exception e) {
            LOGGER.error("Error getUsernameFromToken: ", e);
        }
        return username;
    }

    /**
     * 从token中读取Claims对象
     *
     * @param token  令牌
     * @param secret 加密秘钥
     * @return Claims
     */
    public static Claims getClaimsFromToken(String token, String secret) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            LOGGER.error("Error getClaimsFromToken: ", e);
        }
        return claims;
    }

    public static Boolean validateToken(String token, String secret) {
        final String username = getUsernameFromToken(token, secret);
        if (username != null) {
            JSONObject json = JSON.parseObject(username);
            return (!Strings.isNullOrEmpty(json.getString("userName")) && !isTokenExpired(token, secret));
        }
        return false;
    }

    private static Boolean isTokenExpired(String token, String secret) {
        final Date expiration = getExpirationDateFromToken(token, secret);
        if (expiration != null) {
            return expiration.before(new Date());
        }
        return Boolean.TRUE;
    }

    /**
     * 在access_token中获取有效期
     *
     * @param token 令牌
     * @param secret 秘钥
     * @return Date
     */
    public static Date getExpirationDateFromToken(String token, String secret) {
        Date expiration = null;
        try {
            final Claims claims = getClaimsFromToken(token, secret);
            if (claims != null) {
                expiration = claims.getExpiration();
            }
        } catch (Exception e) {
            LOGGER.error("Error getExpirationDateFromToken: ", e);
        }
        return expiration;
    }

}
