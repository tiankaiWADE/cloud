package com.bazl.dna.sys.controller.system;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.common.ResponseData;
import com.bazl.dna.common.auth.AuthUser;
import com.bazl.dna.common.auth.annotation.CurrentUser;
import com.bazl.dna.common.constants.ErrorCodes;
import com.bazl.dna.common.constants.ErrorInfo;
import com.bazl.dna.common.constants.PublicConstants;
import com.bazl.dna.controller.BaseController;
import com.bazl.dna.sys.client.IFileServiceClient;
import com.bazl.dna.sys.constants.SysConstants;
import com.bazl.dna.sys.entity.SysUser;
import com.bazl.dna.sys.service.ISysJobService;
import com.bazl.dna.sys.service.ISysRoleService;
import com.bazl.dna.sys.service.ISysUserService;
import com.bazl.dna.util.DataUtils;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;

/**
 * 用户管理
 * @author zhaoyong
 */
@RestController
@RequestMapping("/user")
public class SysUserContrlloer extends BaseController {

	@Autowired
	private ISysUserService sysUserService;

	@Autowired
	private ISysRoleService sysRoleService;

	@Autowired
	private ISysJobService sysJobService;

	@Autowired
	private IFileServiceClient fileServiceClient;

	/**
	 * 用户列表
	 *
	 * @param paramJson 参数
	 * @return ResponseData
	 */
	@PostMapping("/list")
	public ResponseData findUserList(@RequestBody JSONObject paramJson) {
		Page<SysUser> page = sysUserService.pageList(paramJson);
		return new ResponseData(page);
	}

	/**
	 * 用户保存
	 *
	 * @param paramJson 用户对象
	 * @return ResponseData
	 */
	@PutMapping("/save")
	public ResponseData save(@RequestBody JSONObject paramJson, HttpServletRequest request) {
		SysUser sysUser = JSON.toJavaObject(paramJson, SysUser.class);
		if (sysUser == null) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		sysUser.setLastIp(DataUtils.getIpAddress(request));
		SysUser entity = sysUserService.saveUser(sysUser);
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, PublicConstants.SUCCESS_CODE);
		result.put(PublicConstants.MSG, entity);
		return new ResponseData(result);
	}

	/**
	 * 获取信息
	 *
	 * @param id 编号
	 * @return ResponseData
	 */
	@GetMapping("/get/{id}")
	public ResponseData get(@PathVariable String id) {
		if (Strings.isNullOrEmpty(id)) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		SysUser sysUser = sysUserService.getById(id);

		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.MSG, sysUser);

		result.put("roleList", sysRoleService.findList(new JSONObject()));
		result.put("jobList", sysJobService.findList(new JSONObject()));
		return new ResponseData(result);
	}

	/**
	 * 个人信息
	 * 
	 * @param authUser 登录对象
	 * @return ResponseData
	 */
	@GetMapping("/profile")
	public ResponseData profile(@CurrentUser AuthUser authUser) {
		SysUser result = sysUserService.getById(authUser.getId());
		if (result != null) {
			return new ResponseData(result);
		}
		return new ResponseData(ErrorCodes.ERR_USER_NOT_FOUND, ErrorInfo.ERR_USER_NOT_FOUND);
	}

	/**
	 * 上传图片
	 * 
	 * @param authUser 登录对象
	 * @param file     文件
	 * @return ResponseData
	 */
	@PostMapping("avatar")
	public ResponseData avatar(@CurrentUser AuthUser authUser, @RequestParam("avatarfile") MultipartFile file) {
		if (!file.isEmpty()) {
			ResponseData responseData = fileServiceClient.upload(file);
			if (responseData.getCode() == PublicConstants.SUCCESS_CODE) {
				Object fileUrl = responseData.getResult();

				SysUser sysUser = sysUserService.getById(authUser.getId());
				sysUser.setAvatar(fileUrl.toString());
				sysUserService.update(sysUser);
				return new ResponseData(fileUrl);
			}
		}
		return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
	}

	/**
	 * 更新个人信息
	 * 
	 * @param paramJson 对象
	 * @param authUser  登录对象
	 * @return ResponseData
	 */
	@PostMapping("/updateUserProfile")
	public ResponseData updateUserProfile(@RequestBody JSONObject paramJson, @CurrentUser AuthUser authUser) {
		SysUser vo = JSON.toJavaObject(paramJson, SysUser.class);
		if (vo != null) {
			SysUser sysUser = sysUserService.getById(authUser.getId());
			sysUser.setEmail(vo.getEmail());
			sysUser.setRealName(vo.getRealName());
			sysUser.setPhone(vo.getPhone());
			sysUser.setSex(vo.getSex());
			sysUser.setLastTime(new Timestamp(System.currentTimeMillis()));
			SysUser result = sysUserService.update(sysUser);
			return new ResponseData(result);
		}
		return new ResponseData();
	}

	/**
	 * 删除用户
	 *
	 * @param ids 编号
	 * @return ResponseData
	 */
	@DeleteMapping("/delete")
	public ResponseData delete(@RequestBody String... ids) {
		if (ids == null) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		int code = 0;
		for (String id : ids) {
			code = sysUserService.deleteById(id);
		}
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, code);
		return new ResponseData(result);
	}

	/**
	 * 判断用户是否存在
	 *
	 * @param json 参数
	 * @return ResponseData
	 */
	@PostMapping("/checkUserName")
	public ResponseData checkUserName(@RequestBody JSONObject json) {
		String userName = json.getString(SysConstants.USER_NAME);
		if (Strings.isNullOrEmpty(userName)) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		String userId = json.getString(SysConstants.ID);
		long code = sysUserService.checkUserName(userId, userName);

		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, code);
		return new ResponseData(result);
	}

	/**
	 * 修改密码
	 *
	 * @param json 参数
	 * @return ResponseData
	 */
	@PostMapping("/resetPassword")
	public ResponseData resetPassword(@RequestBody JSONObject json) {
		String userId = json.getString(SysConstants.USER_ID);
		String password = json.getString(SysConstants.P);
		Integer code = sysUserService.editPassword(userId, password);

		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, code);
		return new ResponseData(result);
	}

	/**
	 * 修改密码
	 *
	 * @param authUser 登录对象
	 * @param json     参数
	 * @return ResponseData
	 */
	@PostMapping("/editPassword")
	public ResponseData editPassword(@CurrentUser AuthUser authUser, @RequestBody JSONObject json) {
		String oldPassword = DigestUtils.md5Hex(json.getString("oldPassword")).toUpperCase();
		String password = json.getString(SysConstants.P);
		SysUser sysUser = sysUserService.getById(authUser.getId());
		if (!oldPassword.equals(sysUser.getPassword())) {
			return new ResponseData(ErrorCodes.ERR_PASSWORD_ERROR, ErrorInfo.ERR_PASS_ERROR);
		}
		Integer code = sysUserService.editPassword(authUser.getId(), password);

		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, code);
		return new ResponseData(result);
	}

	/**
	 * 修改用户状态
	 *
	 * @param json 参数
	 * @return ResponseData
	 */
	@PostMapping("/editStatus")
	public ResponseData editStatus(@RequestBody JSONObject json) {
		String status = json.getString(SysConstants.STATUS);
		if (Strings.isNullOrEmpty(status)) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		String userId = json.getString(SysConstants.USER_ID);
		if (Strings.isNullOrEmpty(userId)) {
			return new ResponseData(ErrorCodes.ERR_PARAM, ErrorInfo.ERR_PARAM);
		}
		Integer code = sysUserService.editStatus(userId, status);

		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, code);
		return new ResponseData(result);
	}

	/**
	 * 导出Excel
	 * 
	 * @param paramJson 参数
	 * @return ResponseData
	 */
	@PostMapping("/export")
	public ResponseEntity<byte[]> export(@RequestBody JSONObject paramJson) {
		List<SysUser> list = sysUserService.findList(paramJson);

		String[] titleKeys = new String[] { "编号", "用户标识", "用户名称", "姓名", "性别", "邮箱", "电话", "备注", "状态", "创建时间", "最后登录时间",
				"最后登录ip" };
		String[] columnNames = { "id", "userCode", "userName", "realName", "sex", "email", "phone", "remark", "status",
				"createTime", "lastTime", "lastIp" };

		String fileName = SysUser.class.getSimpleName() + System.currentTimeMillis();
		return exportExcel(fileName, titleKeys, columnNames, list);
	}

}
