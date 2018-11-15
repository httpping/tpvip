package com.tp.api.exception;
/**
 * 自定义No Found异常
 * <p>Title: NoFoundExcepiton</p>  
 */
public class NoFoundExcepiton extends Exception {

	private static final long serialVersionUID = -5955607821816077172L;
	
	public NoFoundExcepiton(String errorInfo) {
		super(errorInfo);
	}
}
