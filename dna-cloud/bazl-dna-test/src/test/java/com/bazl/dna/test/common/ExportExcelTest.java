package com.bazl.dna.test.common;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.bazl.dna.test.entity.User;
import com.bazl.dna.util.excel.ExportExcelUtils;
import com.google.common.collect.Lists;

public class ExportExcelTest {

	@Test
    public void testExcel() {
		String sheetName = "12345.xlsx";
		String[] titleKey = new String[] {"ID","账号", "名称","年龄","时间"};
		List<User> list = Lists.newArrayList();
		User user = new User();
		user.setId(1L);
		user.setAge(15);
		user.setName("admin");
		user.setUsername("管理员");
		user.setTime(new Timestamp(new Date().getTime()));
		list.add(user);
		
		String filePath = "d:\\";
		String[] columnName = new String[] {"id", "name", "username", "age", "time"};
		ExportExcelUtils.exportExcel(sheetName, titleKey, columnName, list, filePath);
	}

}
