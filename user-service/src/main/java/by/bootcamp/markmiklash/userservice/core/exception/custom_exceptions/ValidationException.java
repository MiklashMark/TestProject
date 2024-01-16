package by.bootcamp.markmiklash.userservice.core.exception.custom_exceptions;
import by.bootcamp.markmiklash.userservice.core.enums.ErrorsTypes;
import by.bootcamp.markmiklash.userservice.core.error.ErrorResponse;
import by.bootcamp.markmiklash.userservice.core.error.StructuredErrorResponse;
import lombok.Getter;

@Getter
public class ValidationException extends IllegalArgumentException {
    private StructuredErrorResponse structuredErrorResponse;
    private ErrorResponse errorResponse;
    private boolean isStructuredError;

    public ValidationException(StructuredErrorResponse errorResponse) {
        isStructuredError = true;
        this.structuredErrorResponse = errorResponse;
        errorResponse.setLogRef(ErrorsTypes.STRUCTURED_ERROR.getMessage());
    }

    public ValidationException(String message) {
        super(message);
        errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setLogRef(ErrorsTypes.ERROR.getMessage());
    }
}
