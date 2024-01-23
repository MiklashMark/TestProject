package by.bootcamp.markmiklash.userservice.core.enums.messages;

public enum LogMessages {
    USER_REGISTERED_SUCCESSFULLY("User {} registered successfully."),
    USER_REGISTRATION_VALIDATION_FAILED("User registration validation failed: {}"),
    ERROR_RETRIEVING_USER_PAGE("Error while retrieving user page: {}"),
    ATTEMPTED_DUPLICATE_EMAIL("Attempted to register with duplicate email"),
    DATA_ACCESS_ERROR("Data access error: {}");

    private final String message;

    LogMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
