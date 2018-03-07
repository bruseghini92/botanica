package ar.edu.um.ingenieria.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionControllerAdvice {
	private static final String URL_403 =  "errors/io";
	private static final String URL_404 = "errors/404";
	private static final String URL_500 = "errors/500";

	private static final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String exception(Exception e) {
		logger.info("Return:"+URL_500);
		return URL_500;
	}

	@ExceptionHandler(DisabledException.class)
	public String disabledException(DisabledException e) {
		logger.info("Return:"+URL_404);
		return URL_404;
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String exception404(NoHandlerFoundException e) {
		logger.info("Return:"+URL_404);
		return URL_404;
	}

	@ExceptionHandler({ AccessDeniedException.class, IllegalStateException.class })
	@ResponseStatus(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED)
	public String accessDeniedException(AccessDeniedException e) {
		logger.info("Return:"+URL_403);
		return URL_403;
	}
}