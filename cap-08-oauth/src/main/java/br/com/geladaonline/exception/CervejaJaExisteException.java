package br.com.geladaonline.exception;

/**
 * Exception for existing beers
 * 
 * @author vagner
 *
 */
public class CervejaJaExisteException extends RuntimeException {

	private static final long serialVersionUID = -8361799171234012718L;

	public CervejaJaExisteException() {
	}

	public CervejaJaExisteException(String message) {
		super(message);
	}

	public CervejaJaExisteException(Throwable cause) {
		super(cause);
	}

	public CervejaJaExisteException(String message, Throwable cause) {
		super(message, cause);
	}

	public CervejaJaExisteException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
