package com.bazl.dna.common;

import java.io.Serializable;

/**
 * 服务响应对象
 * 
 * @author zhaoyong
 */
public class ResponseData implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 错误编码,如果等于1，则是正常返回
	 */
	private int code = 1;
	/**
	 * 返回数据结果
	 */
	private Object result;
	/**
	 * 错误提示信息
	 */
	private String message;

	public ResponseData() {

	}

	public ResponseData(int code) {
		this.code = code;
	}

	public ResponseData(Object result) {
		this.result = result;
	}

	public ResponseData(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public ResponseData(int code, String message, Object result) {
		this.code = code;
		this.message = message;
		this.result = result;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
