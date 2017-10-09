package br.com.everis.controlereunioesws.exception;

public class ErrorResponse {

	private Integer errorCode = null;
	private String message = null;
	
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
