package com.bazl.dna.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

/**
 * 数值工具类
 *
 * @author lrm
 */
public class NumberUtils {

	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

	/***
	 * 下取整
	 */
	public static final String ROUNDING_MODE_FLOOR = "floor";

	/***
	 * 上取整
	 */
	public static final String ROUNDING_MODE_CEIL = "ceil";

	/***
	 * 四舍五入
	 */
	public static final String ROUNDING_MODE_ROUND = "round";

	private NumberUtils() {

	}

	/***
	 * 把数据转换成两位小数精度
	 *
	 * @param source 数据
	 * @return String
	 */
	public static String df2(double source) {
		return DECIMAL_FORMAT.format(source);
	}

	/***
	 * 把数据转换成两位小数精度(四舍五入)
	 *
	 * @param source 数据
	 * @return double
	 */
	public static double format2precision(double source) {
		BigDecimal bg = BigDecimal.valueOf(source).setScale(2, RoundingMode.UP);
		return bg.doubleValue();
	}

	/***
	 * 取数组中的中位数(奇数-取中间|偶数-取中间两个数平均值)
	 *
	 * @param dataList     数据
	 * @param roundingMode floor-下取整|ceil-上取整|round-四舍五入(默认)
	 * @return 中位数值
	 */
	public static Integer getMedianData(List<Integer> dataList, String roundingMode) {
		Integer medianData = 0;
		if (!dataList.isEmpty()) {
			int count = dataList.size();
			int mid = count / 2;
			// 偶数-取中间两个数再求平均
			if (count % 2 == 0) {
				if (ROUNDING_MODE_FLOOR.equals(roundingMode)) {
					medianData = (int) Math.floor((dataList.get(mid) + dataList.get(mid + 1)) / 2d);
				} else if (ROUNDING_MODE_CEIL.equals(roundingMode)) {
					medianData = (int) Math.ceil((dataList.get(mid) + dataList.get(mid + 1)) / 2d);
				} else {
					medianData = Math.round((dataList.get(mid) + dataList.get(mid + 1)) / 2f);
				}

			} else {// 奇数-最中间数据
				medianData = dataList.get(mid);
			}
		}
		return medianData;
	}

	/***
	 * 求平均数
	 *
	 * @param dataList 数据
	 * @return double
	 */
	public static double getAvgIntegerData(List<Integer> dataList) {
		double avgData = 0;
		if (!dataList.isEmpty()) {
			int count = dataList.size();
			double sum = 0;
			for (Integer data : dataList) {
				sum += data;
			}
			avgData = sum / count;
		}
		return avgData;
	}

	/***
	 * 求平均数
	 *
	 * @param dataList 数据
	 * @return double
	 */
	public static double getAvgDoubleData(List<Double> dataList) {
		double avgData = 0;
		if (!dataList.isEmpty()) {
			int count = dataList.size();
			double sum = 0;
			for (Double data : dataList) {
				sum += data;
			}
			avgData = sum / count;
		}
		return avgData;
	}

	/***
	 * 数据列表按升序排序
	 *
	 * @param dataList 数据
	 */
	public static void listSortAsc(List<Integer> dataList) {
		/*
		 * int compare(Integer i1, Integer i2) 返回一个基本类型的整型， 返回负数表示：i1 小于i2， 返回0
		 * 表示：i1和i2相等， 返回正数表示：i1大于i2
		 */
		dataList.sort((i1, i2) -> {
			// 按照Integer进行升序排列
			if (i1 > i2) {
				return 1;
			}
			if (i1.equals(i2)) {
				return 0;
			}
			return -1;
		});
	}
}
