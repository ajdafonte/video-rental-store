package com.casumo.hometest.videorentalstore.common.rest;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.casumo.hometest.videorentalstore.common.error.VideoRentalStoreApiError;
import com.casumo.hometest.videorentalstore.common.error.VideoRentalStoreApiException;


/**
 * Handles all exceptions for all REST controllers and translates them to a proper error response. Because we don't limit it to RestController.class,
 * it also deals with both @{@link org.springframework.web.HttpMediaTypeException}.
 */
@ControllerAdvice
public class RestControllerErrorHandler
{
    @ExceptionHandler(VideoRentalStoreApiException.class)
    @ResponseBody
    VideoRentalStoreErrorRest handleVideoRentalStoreApiException(final HttpServletRequest request,
                                                                 final HttpServletResponse response,
                                                                 final VideoRentalStoreApiException exception)
    {
        response.setStatus(exception.getError().getHttpStatus().value());
        return new VideoRentalStoreErrorRest(request, exception.getError(), exception.getErrorParameters());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    VideoRentalStoreErrorRest handleException(final HttpServletRequest request, final Exception e)
    {
        return new VideoRentalStoreErrorRest(request, VideoRentalStoreApiError.INTERNAL_SERVER_ERROR);
    }

    /**
     * Thrown when the requested resource is unknown.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    VideoRentalStoreErrorRest handleNoHandlerFoundException(final HttpServletRequest request,
                                                            final NoHandlerFoundException exception)
    {
        return new VideoRentalStoreErrorRest(request, VideoRentalStoreApiError.UNKNOWN_RESOURCE, exception.getRequestURL());
    }

    /**
     * Thrown when an argument validation fails.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    VideoRentalStoreErrorRest handleMethodArgumentNotValidException(final HttpServletRequest request, final MethodArgumentNotValidException exception)
    {
        final String errorMsg = exception.getBindingResult().getFieldErrors().stream()
            .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
            .collect(Collectors.joining(", "));

        return new VideoRentalStoreErrorRest(
            request,
            VideoRentalStoreApiError.INVALID_REQUEST,
            errorMsg);
    }

    /**
     * Thrown when a request parameter can't be parsed because of an invalid field type.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    VideoRentalStoreErrorRest handleMethodArgumentNotValid(final HttpServletRequest request,
                                                           final MethodArgumentTypeMismatchException ex)
    {
        return new VideoRentalStoreErrorRest(request, VideoRentalStoreApiError.INVALID_REQUEST_PARAMETER, ex.getName());
    }
}
