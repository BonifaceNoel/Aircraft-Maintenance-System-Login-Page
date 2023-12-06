package com.ibsplc.amtsloginpage.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class NewUserInvalidException extends Exception {

	private static final Logger logger = LoggerFactory.getLogger(NewUserInvalidException.class);

	public NewUserInvalidException(String error, Throwable cause) {
		logger.error(error + "" + cause);
	}
}
