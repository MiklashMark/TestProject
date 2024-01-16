package by.bootcamp.markmiklash.userservice.core.enums;

public enum ErrorsTypes {
    STRUCTURED_ERROR("structured_error"),
    ERROR("error");

    private final String message;

    ErrorsTypes(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
