package com.ibsplc.amtsloginpage.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class JWTKeyGenerationException extends Exception{

	private static final Logger logger = LoggerFactory.getLogger(JWTKeyGenerationException.class);

	public JWTKeyGenerationException(String error, Throwable cause) {
		logger.error(error + "" + cause);
	}
}
