package com.bazl.dna.monitor.entity.server;

import java.lang.management.ManagementFactory;
import java.util.Date;

import com.bazl.dna.common.constants.PublicConstants;
import com.bazl.dna.util.Arith;
import com.bazl.dna.util.DateUtil;

/**
 * JVM相关信息
 * 
 * @author zhaoyong
 */
public class Jvm {
	/**
	 * 当前JVM占用的内存总数(M)
	 */
	private double total;

	/**
	 * JVM最大可用内存总数(M)
	 */
	private double max;

	/**
	 * JVM空闲内存(M)
	 */
	private double free;

	/**
	 * JDK版本
	 */
	private String version;

	/**
	 * JDK路径
	 */
	private String home;

	public double getTotal() {
		return Arith.div(total, (PublicConstants.BYTE_SIZE * PublicConstants.BYTE_SIZE), 2);
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getMax() {
		return Arith.div(max, (PublicConstants.BYTE_SIZE * PublicConstants.BYTE_SIZE), 2);
	}

	public void setMax(double max) {
		this.max = max;
	}

	public double getFree() {
		return Arith.div(free, (PublicConstants.BYTE_SIZE * PublicConstants.BYTE_SIZE), 2);
	}

	public void setFree(double free) {
		this.free = free;
	}

	public double getUsed() {
		return Arith.div(total - free, (PublicConstants.BYTE_SIZE * PublicConstants.BYTE_SIZE), 2);
	}

	public double getUsage() {
		return Arith.mul(Arith.div(total - free, total, 4), 100);
	}

	/**
	 * 获取JDK名称
	 */
	public String getName() {
		return ManagementFactory.getRuntimeMXBean().getVmName();
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	/**
	 * JDK启动时间
	 */
	public String getStartTime() {
		return DateUtil.dateToString(DateUtil.getServerStartDate(), DateUtil.FULL_TIME_FORMAT);
	}

	/**
	 * JDK运行时间
	 */
	public String getRunTime() {
		return DateUtil.getDatePoor(new Date(), DateUtil.getServerStartDate());
	}
}
