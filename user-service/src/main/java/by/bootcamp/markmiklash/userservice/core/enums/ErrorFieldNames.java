package by.bootcamp.markmiklash.userservice.core.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;


public enum ErrorFieldNames {
    MAIL("mail"),
    PASSWORD("password"),

    NAME("name"),
    SURNAME("surname"),
    PATRONYMIC("patronymic"),
    ROLE("role");
    private final String field;

    ErrorFieldNames(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
