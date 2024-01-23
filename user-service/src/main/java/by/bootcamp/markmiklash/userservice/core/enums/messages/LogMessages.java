package by.bootcamp.markmiklash.userservice.core.enums.messages;

public enum LogMessages {
    USER_REGISTERED_SUCCESSFULLY("User {} registered successfully."),
    USER_REGISTRATION_VALIDATION_FAILED("User registration validation failed: {}"),
    ERROR_RETRIEVING_USER_PAGE("Error while retrieving user page: {}"),
    ATTEMPTED_DUPLICATE_EMAIL("Attempted to register with duplicate email"),
    DATA_ACCESS_ERROR("Data access error: {}"),


    START_VALIDATION("Starting validation."),
    END_VALIDATION("Validation completed."),
    START_FIELD_VALIDATION("Starting validation for field: {}."),
    END_FIELD_VALIDATION("Validation completed for field: {}."),
    START_ROLE_VALIDATION("Starting role validation."),
    END_ROLE_VALIDATION("Role validation completed."),
    ERROR_ADDED("Error added during validation. Field: {}, Message: {}."),


    START_USER_REGISTRATION("Starting user registration."),
    USER_REGISTRATION_SUCCESS("User registration completed successfully."),
    USER_REGISTRATION_ERROR("Error occurred during user registration. Message: {}"),
    START_GET_USER_PAGE("Starting retrieval of user page."),
    GET_USER_PAGE_SUCCESS("User page retrieval completed successfully."),
    GET_USER_PAGE_ERROR("Error occurred during user page retrieval. Message: {}");

    private final String message;

    LogMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
