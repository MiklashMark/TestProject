package by.bootcamp.markmiklash.userservice.core.exception.custom_exceptions;


import by.bootcamp.markmiklash.userservice.core.enums.ErrorsTypes;
import by.bootcamp.markmiklash.userservice.core.error.ErrorResponse;
import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException{
    private final ErrorResponse errorResponse;
    public EntityNotFoundException(String message, Throwable cause, ErrorResponse errorResponse) {
        super(message, cause);
        this.errorResponse = errorResponse;
    }

    public EntityNotFoundException(String message) {
        super(message);
        this.errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setLogRef(ErrorsTypes.ERROR.getMessage());
    }
}
