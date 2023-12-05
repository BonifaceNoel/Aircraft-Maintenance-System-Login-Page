package com.ibsplc.amtsloginpage.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class NoLoginNameException extends Exception {

	private static final Logger logger = LoggerFactory.getLogger(NoLoginNameException.class);

	public NoLoginNameException(String error, Throwable cause) {
		logger.error(error + "" + cause);
	}

	public NoLoginNameException(String error, String name) {
		logger.error(error + "" + name);
	}
}