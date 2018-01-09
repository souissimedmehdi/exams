package com.brother.exams.exception;

public class TechnicalException extends Exception {

	private static final long serialVersionUID = -1714921899760537857L;

	public TechnicalException(Throwable cause) {
		super(cause);
	}

	public TechnicalException(String message) {
		super(message);
	}

}
