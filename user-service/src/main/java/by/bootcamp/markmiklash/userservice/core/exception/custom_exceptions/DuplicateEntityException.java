package by.bootcamp.markmiklash.userservice.core.exception.custom_exceptions;



import by.bootcamp.markmiklash.userservice.core.enums.ErrorsTypes;
import by.bootcamp.markmiklash.userservice.core.error.ErrorResponse;
import lombok.Getter;
import org.springframework.dao.DataIntegrityViolationException;


@Getter
public class DuplicateEntityException extends DataIntegrityViolationException {
    private final ErrorResponse errorResponse;
    public DuplicateEntityException(String message, Throwable e) {
        super(message, e);
        errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setLogRef(ErrorsTypes.ERROR.getMessage());
    }

    public DuplicateEntityException(String msg, ErrorResponse errorResponse) {
        super(msg);
        this.errorResponse = errorResponse;
    }
}
