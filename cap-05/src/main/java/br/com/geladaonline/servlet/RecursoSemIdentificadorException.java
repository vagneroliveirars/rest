package br.com.geladaonline.servlet;

public class RecursoSemIdentificadorException extends Exception {

	private static final long serialVersionUID = -547461041144710339L;

	public RecursoSemIdentificadorException() {
		// TODO Auto-generated constructor stub
	}

	public RecursoSemIdentificadorException(String message) {
		super(message);
	}

	public RecursoSemIdentificadorException(Throwable cause) {
		super(cause);
	}

	public RecursoSemIdentificadorException(String message, Throwable cause) {
		super(message, cause);
	}

	public RecursoSemIdentificadorException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
