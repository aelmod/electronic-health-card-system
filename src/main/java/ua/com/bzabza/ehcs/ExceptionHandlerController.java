package ua.com.bzabza.ehcs;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ua.com.bzabza.ehcs.exception.EntityAlreadyExistsException;
import ua.com.bzabza.ehcs.security.BadCredentialsException;
import ua.com.bzabza.ehcs.user.BadUserBehaviorException;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ExceptionInfo entityNotFoundHandler(HttpServletRequest request, PersistenceException e) {
        return new ExceptionInfo(request.getRequestURI(), e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ExceptionInfo entityAlreadyExistsHandler(HttpServletRequest request, PersistenceException e) {
        return new ExceptionInfo(request.getRequestURI(), e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public ExceptionInfo badCredentialsHandler(HttpServletRequest request, PersistenceException e) {
        return new ExceptionInfo(request.getRequestURI(), e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(BadUserBehaviorException.class)
    public ExceptionInfo badUserBehaviorHandler(HttpServletRequest request, PersistenceException e) {
        return new ExceptionInfo(request.getRequestURI(), e.getMessage());
    }
}
