package com.ibsplc.amtsloginpage.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class NewUserInvalidException extends Exception {

	private static final Logger logger = LoggerFactory.getLogger(NoLoginNameException.class);

	public NewUserInvalidException(String error, Throwable cause) {
		logger.error(error + "" + cause);
	}

	public NewUserInvalidException(String error, String name) {
		logger.error(error + "" + name);
	}
}
