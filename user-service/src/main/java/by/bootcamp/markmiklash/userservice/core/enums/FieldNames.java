package by.bootcamp.markmiklash.userservice.core.enums;


public enum FieldNames {
    MAIL("mail"),
    PASSWORD("password"),

    NAME("name"),
    SURNAME("surname"),
    PATRONYMIC("patronymic"),
    ROLE("role");
    private final String field;

    FieldNames(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
