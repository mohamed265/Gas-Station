package com.free.gasstation.exception;

public class NotUniqueUserNameException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotUniqueUserNameException() {
	}

	public NotUniqueUserNameException(String e) {
		super(e);
		System.out.println(e);
	}

}
