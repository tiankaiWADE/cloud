package com.bazl.dna.sys.controller.system;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bazl.dna.common.ResponseData;
import com.bazl.dna.common.constants.PublicConstants;
import com.bazl.dna.controller.BaseController;
import com.bazl.dna.sys.entity.SysMessage;
import com.bazl.dna.sys.service.ISysMessageService;
import com.google.common.collect.Maps;

/**
 * 消息通知
 * @author zhaoyong
 */
@RestController
@RequestMapping("/message")
public class SysMessageController extends BaseController {

	@Autowired
	private ISysMessageService sysMessageService;

	/**
	 * 查询结果 有返回 true 并且删除
	 * @param messageType 消息类型
	 * @return ResponseData
	 */
	@GetMapping("/refresh")
	public ResponseData refresh(String messageType) {
		Map<String, Object> result = Maps.newHashMap();
		result.put(PublicConstants.CODE, Boolean.FALSE);

		List<SysMessage> list = sysMessageService.findList(messageType);
		if (!list.isEmpty()) {
			list.forEach(entity -> sysMessageService.deleteById(entity.getId()));
			result.put(PublicConstants.CODE, Boolean.TRUE);
		}
		return new ResponseData(result);
	}
}
