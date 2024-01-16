package by.bootcamp.markmiklash.userservice.core.enums.messages;

import lombok.Getter;

@Getter
public enum ErrorMessages {
    INCORRECT_MAIL_FORMAT("Incorrect mail format."),
    PASSWORD_LENGTH_REQUIREMENT("Password length should be at least 8 characters." +
            " The password must include at least letters, digits, and one or more special characters."),
    INVALID_FIO("Fio must consist of 3 words starting with a capital letter. Only letters are used."),
    ALREADY_REGISTERED("This email is already registered"),
    SERVER_ERROR("The server was unable to process the request correctly." +
            " Please contact your administrator"),
    USER_NOT_FOUND("User not found!"),
    FIELD_IS_EMPTY("field is empty"),
    DATA_NOT_FOUND("No results found for this search."),
    UNKNOWN_ERROR("Unknown error occurred. Please, contact the administrator.");
    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    }

