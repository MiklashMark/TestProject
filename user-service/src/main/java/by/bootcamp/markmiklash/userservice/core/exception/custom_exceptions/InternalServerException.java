package by.bootcamp.markmiklash.userservice.core.exception.custom_exceptions;


import by.itacademy.exceptions.enums.ErrorsTypes;
import by.itacademy.exceptions.error.ErrorResponse;
import lombok.Getter;

@Getter
public class InternalServerException extends RuntimeException {
    private final ErrorResponse errorResponse;
    public InternalServerException(String message) {
        super(message);
        errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setLogRef(ErrorsTypes.ERROR.getMessage());
    }
    public InternalServerException(String message, Throwable cause) {
        super(message, cause);
        errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setLogRef(ErrorsTypes.ERROR.getMessage());
    }

}
