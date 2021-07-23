package com.bazl.dna.common.exception;

/**
 * 异常处理
 * @author zhaoyong
 */
public class DnaRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DnaRuntimeException() {
		super();
	}
	
	public DnaRuntimeException(String message) {
		super(message);
	}
	
	public DnaRuntimeException(Exception e) {
		super(e);
	}
	
	public DnaRuntimeException(String message, Exception e) {
		super(message, e);
	}
	
}
