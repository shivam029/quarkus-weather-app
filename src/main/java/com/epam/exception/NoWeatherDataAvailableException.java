package com.epam.exception;

public class NoWeatherDataAvailableException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoWeatherDataAvailableException(String msg) {
		super(msg);
	}

}
