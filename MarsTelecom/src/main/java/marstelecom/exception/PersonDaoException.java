package marstelecom.exception;

public class PersonDaoException extends Exception{
	
	private String errorCode;
	private String errorMessage;
	
	public PersonDaoException() {
		super();
	}

	public PersonDaoException(String message) {
		super(message);
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	

}
