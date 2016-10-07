package br.com.geladaonline.exception;

/**
 * Exception for not found beers
 * 
 * @author vagner
 *
 */
public class CervejaNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 7090096144041488400L;

	public CervejaNaoEncontradaException() {

	}

	public CervejaNaoEncontradaException(String message) {
		super(message);
	}

	public CervejaNaoEncontradaException(Throwable cause) {
		super(cause);
	}

	public CervejaNaoEncontradaException(String message, Throwable cause) {
		super(message, cause);
	}

	public CervejaNaoEncontradaException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
