package by.bootcamp.markmiklash.userservice.core.enums.messages;

import lombok.Getter;

@Getter
public enum Messages {
    REGISTERED_SUCCESSFULLY("Registered successfully.");

    private final String message;

    Messages(String message) {
        this.message = message;
    }
}
