package com.bazl.dna.common.exception;

/**
 * 异常信息
 * @author zhaoyong
 */
public class DnaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DnaException() {
		super();
	}
	
	public DnaException(String message) {
		super(message);
	}
	
	public DnaException(Exception e) {
		super(e);
	}
	
	public DnaException(String message, Exception e) {
		super(message, e);
	}
	
}
