package com.bazl.dna.dubbo.api.service;

/**
 * Echo Service
 * @author zhaoyong
 *
 */
public interface IEchoService {

	/**
	 * echo
	 * @param message data
	 * @return String 
	 */
	String echo(String message);
}
