package com.sds.icto.instakgram.exception;

public class MemberDAOException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MemberDAOException() {
		super("Member DAO Exception");
	}

	public MemberDAOException(String msg) {
		super(msg);
	}
}
