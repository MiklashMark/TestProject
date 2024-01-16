package by.bootcamp.markmiklash.userservice.core.enums;

public enum ErrorFieldNames {
    MAIL("mail"),
    PASSWORD("password"),

    NAME("name"),
    SURNAME("surname"),
    PATRONYMIC("patronymic");
    private final String field;

    ErrorFieldNames(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
