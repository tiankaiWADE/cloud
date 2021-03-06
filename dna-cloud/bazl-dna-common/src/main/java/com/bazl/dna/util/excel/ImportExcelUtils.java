package com.bazl.dna.util.excel;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bazl.dna.util.DataUtils;
import com.google.common.collect.Lists;

/**
 * Excel导入
 *
 * @author lrm
 */
public class ImportExcelUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImportExcelUtils.class);

	private ImportExcelUtils() {
	}
	
	/**
	 * 获取Excel
	 * 
	 * @param is        输入流
	 * @param extension 扩展名
	 * @return Workbook
	 * @throws IOException 异常
	 */
	public static Workbook getWorkbook(InputStream is, String extension) throws IOException {
		// 创建工作表
		Workbook workbook;
		if(extension.contains("xlsx")) {
			workbook = new XSSFWorkbook(is);
		} else {
			workbook = new HSSFWorkbook(is);
		}
		return workbook;
	}

	/***
	 * 读取Excel数据
	 *
	 * @param is       Excel文件输入流
	 * @param sheetAt  Sheet所在页，默认第一页0
	 * @param titleRow 标题所在行，默认首行0
	 * @param extension excel后缀
	 * @return ExcelData
	 */
	public static ExcelData readExcel(InputStream is, Integer sheetAt, Integer titleRow, String extension) {
		try (Workbook workbook = getWorkbook(is, extension)) {
			return readExcel(workbook, sheetAt, titleRow);
		} catch (IOException e) {
			LOGGER.error("Error readXlsExcelData: ", e);
		}
		return null;
	}
	
	private static ExcelData readExcel(Workbook wb, Integer sheetAt, Integer titleRow) {
		ExcelData excelData = new ExcelData();
		// 读取Sheet, 默认0
		Sheet sheet = wb.getSheetAt(sheetAt == null ? 0 : sheetAt);
		/*
		 * 1、获取Sheet名称
		 */
		String sheetName = sheet.getSheetName().trim();
		excelData.setSheetName(sheetName);

		/*
		 * 2、获取标题行，默认首行0
		 */
		Row row = sheet.getRow(titleRow == null ? 0 : titleRow);
		// 标题总列数
		int colNum = row.getPhysicalNumberOfCells();
		String[] title = new String[colNum];
		for (int i = 0; i < colNum; i++) {
			Object val = getCellValue(row.getCell((short) i));
			title[i] = val != null ? val.toString().trim() : "";
		}
		excelData.setColumnNames(title);

		/*
		 * 3、获取数据行，正文内容默认从标题行+1开始
		 */
		int rowNum = sheet.getLastRowNum();
		// 由于第0行和第一行已经合并了 在这里索引从2开始
		// 正文内容应该从第二行开始,第一行为表头的标题
		List<Object[]> dataList = Lists.newArrayList();
		for (int i = titleRow + 1; i <= rowNum; i++) {
			Object[] objs = new Object[colNum];
			row = sheet.getRow(i);
			int nullCells = checkNullCells(row);
			if (nullCells >= colNum) {
				continue;
			}
			int j = 0;
			while (j < colNum) {
				Cell cell = row.getCell((short) j);
				Object val = null;
				if (cell != null && cell.getCellType() != CellType.BLANK) {
					val = getCellValue(cell);
				}
				objs[j] = val != null ? val.toString().trim() : val;
				j++;
			}
			dataList.add(objs);
		}
		excelData.setDataList(dataList);
		return excelData;
	}

	/***
	 * 读取Cell的值
	 *
	 * @param cell 单元格
	 * @return Object
	 */
	private static Object getCellValue(Cell cell) {
		Object value;
		switch (cell.getCellType()) {
		case STRING:
			value = cell.getStringCellValue().trim();
			break;
		case _NONE:
			value = null;
			break;
		case BOOLEAN:
			value = cell.getBooleanCellValue();
			break;
		case BLANK:
			value = null;
			break;
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				Date date = cell.getDateCellValue();
				value = com.bazl.dna.util.DateUtil.dateToString(date, com.bazl.dna.util.DateUtil.FULL_TIME_FORMAT);
			} else if (String.valueOf(cell.getNumericCellValue()).contains(".")) {
				DecimalFormat df = new DecimalFormat("#");
				value = df.format(cell.getNumericCellValue());
			} else {
				value = (cell + "").trim();
			}
			break;
		case FORMULA:
			// 读公式计算值
			value = cell.getCellFormula();
			if (!DataUtils.isEmpty(value)
					&& value.toString().toUpperCase().contains("DATE")) {
				Date date = cell.getDateCellValue();
				value = com.bazl.dna.util.DateUtil.dateToString(date, com.bazl.dna.util.DateUtil.FULL_TIME_FORMAT);
			} else {
				value = String.valueOf(cell.getNumericCellValue());
				// 如果获取的数据值为非法值,则转换为获取字符串
				if ("NaN".equals(value)) {
					value = cell.getRichStringCellValue().toString();
				}
			}
			break;
		default:
			value = cell.toString();
		}
		return value;
	}

	/***
	 * 判断空单元格数
	 *
	 * @param row 行
	 * @return int
	 */
	private static int checkNullCells(Row row) {
		int num = 0;
		for (Cell c : row) {
			if (c.getCellType() == CellType.BLANK) {
				num++;
			}
		}
		return num;
	}

}
