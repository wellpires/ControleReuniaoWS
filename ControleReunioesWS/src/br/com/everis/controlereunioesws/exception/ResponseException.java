package br.com.everis.controlereunioesws.exception;

public class ResponseException extends Exception {

	private static final long serialVersionUID = 1L;
	public String errorMessage = null;
	
	public ResponseException() {
		super();
	}
	
	public ResponseException(String errorMessage){
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	
	public String getErrorMessage(){
		return errorMessage;
	}
	
}
