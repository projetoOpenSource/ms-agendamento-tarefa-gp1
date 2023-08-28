package br.com.agendamento.api.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResolveExceptionHandler{
	
	/**
	 * Captura as Exceções do InternalErrorException
	 * 
	 * @param e
	 *            exceção que ocorreu ao axecutar alguma ação no sistema
	 * @param request
	 *            O contêiner do servlet cria um HttpServletRequest objeto e o passa como um argumento para os métodos de serviço do servlet
	 *            ( doGet, doPost, etc).
	 * @return ResponseEntity<ResponseMessageError>
	 */
	@ExceptionHandler(InternalErrorException.class)
	public ResponseEntity<ResponseMessageError> handleInternalError(InternalErrorException e, HttpServletRequest request) {
		return popularResponseMessageError(e, HttpStatus.INTERNAL_SERVER_ERROR.value(),
				messages.get("messages.handler.titulo.internalerror"), request);
	}

	/**
	 * Captura as Exceções do ValidationException
	 * 
	 * @param e
	 *            exceção que ocorreu ao axecutar alguma ação no sistema
	 * @param request
	 *            O contêiner do servlet cria um HttpServletRequest objeto e o passa como um argumento para os métodos de serviço do servlet
	 *            ( doGet, doPost, etc).
	 * @return ResponseEntity<ResponseMessageError>
	 */
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ResponseMessageError> handleValidation(ValidationException e, HttpServletRequest request) {
		return popularResponseMessageError(e, HttpStatus.BAD_REQUEST.value(), messages.get("messages.handler.titulo.validation"), request);
	}

	/**
	 * Captura as Exceções do Exception
	 * 
	 * @param e
	 *            exceção que ocorreu ao axecutar alguma ação no sistema
	 * @param request
	 *            O contêiner do servlet cria um HttpServletRequest objeto e o passa como um argumento para os métodos de serviço do servlet
	 *            ( doGet, doPost, etc).
	 * @return ResponseEntity<ResponseMessageError>
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseMessageError> handleException(Exception e, HttpServletRequest request) {
		return popularResponseMessageError(e, HttpStatus.INTERNAL_SERVER_ERROR.value(), messages.get("messages.handler.titulo.exception"),
				request);
	}

	/**
	 * Método que popula o response para cada exceção.
	 * 
	 * @param e
	 *            exceção que ocorreu ao axecutar alguma ação no sistema
	 * @param status
	 *            status da exceção que ocorreu
	 * @param titulo
	 *            titulo da exceção que ocorreu
	 * @param request
	 *            O contêiner do servlet cria um HttpServletRequest objeto e o passa como um argumento para os métodos de serviço do servlet
	 *            ( doGet, doPost, etc).
	 * @return ResponseEntity<ResponseMessageError>
	 */
	private static ResponseEntity<ResponseMessageError> popularResponseMessageError(Exception e, Integer status, String titulo,
			HttpServletRequest request) {
		ResponseMessageError err = new ResponseMessageError();
		err.setTimestamp(Instant.now());
		err.setStatus(status);
		err.setError(titulo);
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

}
