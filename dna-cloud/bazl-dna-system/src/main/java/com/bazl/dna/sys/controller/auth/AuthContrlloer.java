package com.bazl.dna.sys.controller.auth;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.common.ResponseData;
import com.bazl.dna.common.auth.AuthUser;
import com.bazl.dna.common.auth.annotation.CurrentUser;
import com.bazl.dna.common.cache.RedisCache;
import com.bazl.dna.common.constants.ErrorCodes;
import com.bazl.dna.common.constants.ErrorInfo;
import com.bazl.dna.controller.BaseController;
import com.bazl.dna.sys.constants.SysConstants;
import com.bazl.dna.sys.service.ISysRoleService;
import com.bazl.dna.sys.service.ISysUserService;
import com.bazl.dna.util.DataUtils;
import com.bazl.dna.util.VerifyCodeUtils;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;

/**
 * 认证登录接口
 * @author zhaoyong
 */
@RestController
@RequestMapping("/auth")
public class AuthContrlloer extends BaseController {

	private static final String CACHE_LOGIN_NAME = "Auth:Login";
	
	@Autowired
	private ISysUserService sysUserService;

	@Autowired
	private ISysRoleService sysRoleService;

	@Autowired
    private RedisCache redisCache;
	
	/**
	 * 系统登录
	 *
	 * @param json 登录对象
	 * @return ResponseData
	 */
	@PostMapping("/login")
	public ResponseData login(HttpServletRequest request, @RequestBody JSONObject json) {
		// 验证码
		if (!DataUtils.isEmpty(json.getString(SysConstants.UUID))) {
			String verifyKey = SysConstants.CAPTCHA_IMAGE + ":" + json.getString(SysConstants.UUID);
	        String captcha = redisCache.getCacheObject(verifyKey);
	        redisCache.deleteObject(verifyKey);
	        if (captcha == null || !captcha.equalsIgnoreCase(json.getString(SysConstants.CODE))) {
	        	return new ResponseData(ErrorCodes.ERR_PARAM, "验证码不正确!");
	        }
		}
		
		String userName = json.getString(SysConstants.USER_NAME);
		if (Strings.isNullOrEmpty(userName)) {
			return new ResponseData(ErrorCodes.ERR_USER_NAME_NOT_NULL, ErrorInfo.ERR_USER_NAME_NOT_NULL);
		}
		String password = json.getString(SysConstants.PASS);
		if (Strings.isNullOrEmpty(password)) {
			return new ResponseData(ErrorCodes.ERR_PASSWORD_NOT_NULL, ErrorInfo.ERR_PASS_NOT_NULL);
		}
		String ip = DataUtils.getIpAddress(request);
		LOGGER.info("ip: {}", ip);

		Map<String, Object> result = sysUserService.login(userName, password);
		if (result != null) {
			return new ResponseData(result);
		}
		return new ResponseData(ErrorCodes.ERR_USER_NOT_FOUND, ErrorInfo.ERR_USER_NOT_FOUND);
	}

	/**
	 * 退出登录
	 * @return ResponseData
	 */
	@PostMapping("/logout")
	public ResponseData logout(@CurrentUser AuthUser authUser) {
		String key = CACHE_LOGIN_NAME + ":" + authUser.getUserName();
		redisCache.deleteObject(key);
		return new ResponseData();
	}

	/**
	 * 获取左侧菜单
	 *
	 * @param menuType 菜单类型
	 * @return ResponseData
	 */
	@GetMapping("/menu/{menuType}")
	public ResponseData findMenuByRoles(@CurrentUser AuthUser user, @PathVariable String menuType) {
		if (Strings.isNullOrEmpty(menuType)) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		try {
			if (user != null && !user.getRoleList().isEmpty()) {
				JSONArray result = sysRoleService.findMenuByRoles(user.getRoleList(), menuType);
				return new ResponseData(result);
			}
		} catch (Exception e) {
			LOGGER.error("Error findMenuByRoles: ", e);
		}
		return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
	}

	/**
	 * 获取验证码
	 * @return ResponseData
	 */
	@GetMapping("/captchaImage")
	public ResponseData captchaImage() {
		// 生成随机字串
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		// 唯一标识
		String uuid = UUID.randomUUID().toString();
		String verifyKey = SysConstants.CAPTCHA_IMAGE + ":" + uuid;
		redisCache.setCacheObject(verifyKey, verifyCode, 3, TimeUnit.MINUTES);

		Map<String, Object> result = Maps.newHashMap();
		try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
			// 生成图片
			int w = 111;
			int h = 36;
			VerifyCodeUtils.outputImage(w, h, stream, verifyCode);

			result.put(SysConstants.UUID, uuid);
			result.put("img", Base64Utils.encodeToString(stream.toByteArray()));
		} catch (IOException e) {
			LOGGER.error("Error captchaImage: ", e);
		}

		return new ResponseData(result);
	}

	/**
	 * 获取菜单对应权限
	 *
	 * @param menuId 菜单id
	 * @return ResponseData
	 */
	@GetMapping("/menu/operation/{menuId}")
	public ResponseData findMenuOperByRoles(@CurrentUser AuthUser user, @PathVariable String menuId) {
		if (Strings.isNullOrEmpty(menuId)) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		try {
			if (user != null && !user.getRoleList().isEmpty()) {
				List<Map<String, String>> result = sysRoleService.findMenuOperByRoles(user.getRoleList(), menuId);
				return new ResponseData(result);
			}
		} catch (Exception e) {
			LOGGER.error("Error findMenuOperByRoles: ", e);
		}
		return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
	}

	

}
