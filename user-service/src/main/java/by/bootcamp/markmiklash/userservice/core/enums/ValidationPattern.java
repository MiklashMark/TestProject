package by.bootcamp.markmiklash.userservice.core.enums;

import lombok.Getter;

@Getter
public enum ValidationPattern {
    EMAIL(("^[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")),
    PASSWORD("^(?=.*[A-Za-zА-Яа-я])(?=.*\\d)(?=.*[@#$%^&+=]).*$"),
    FIO("^[a-zA-Z]+$");

    private final String pattern;

    ValidationPattern(String pattern) {
        this.pattern = pattern;
    }

}

