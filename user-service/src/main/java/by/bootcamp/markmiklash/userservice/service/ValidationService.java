package by.bootcamp.markmiklash.userservice.service;

import by.bootcamp.markmiklash.userservice.core.dto.UserRegistrationDTO;
import by.bootcamp.markmiklash.userservice.core.dto.ValidationDTO;
import by.bootcamp.markmiklash.userservice.core.enums.FieldNames;
import by.bootcamp.markmiklash.userservice.core.enums.UserRole;
import by.bootcamp.markmiklash.userservice.core.enums.ValidationPattern;
import by.bootcamp.markmiklash.userservice.core.enums.messages.ErrorMessages;
import by.bootcamp.markmiklash.userservice.core.error.ErrorDetail;
import by.bootcamp.markmiklash.userservice.core.error.StructuredErrorResponse;
import by.bootcamp.markmiklash.userservice.core.exception.custom_exceptions.ValidationException;
import by.bootcamp.markmiklash.userservice.service.api.IValidationService;
import org.springframework.stereotype.Service;


@Service
public class ValidationService implements IValidationService {
    private final ThreadLocal<StructuredErrorResponse> errorsResponseThreadLocal;
    private static final int MAX_MAIL_LENGTH = 50;
    private static final int MAX_NAME_LENGTH = 20;
    private static final int MAX_SURNAME_LENGTH = 40;
    private static final int MAX_PATRONYMIC_LENGTH = 40;
    public ValidationService() {
        errorsResponseThreadLocal = ThreadLocal
                .withInitial(StructuredErrorResponse::new);
    }

    @Override
    public void validateRegistration(UserRegistrationDTO user) {
        try {
            validateField(new ValidationDTO(user.getMail(), FieldNames.MAIL.getField(),
                    ValidationPattern.EMAIL.getPattern(), MAX_MAIL_LENGTH));
            validateField(new ValidationDTO(user.getName(), FieldNames.NAME.getField(),
                    ValidationPattern.FIO.getPattern(), MAX_NAME_LENGTH));
            validateField(new ValidationDTO(user.getSurname(), FieldNames.SURNAME.getField(),
                    ValidationPattern.FIO.getPattern(), MAX_SURNAME_LENGTH));
            validateField(new ValidationDTO(user.getPatronymic(), FieldNames.PATRONYMIC.getField(),
                    ValidationPattern.FIO.getPattern(), MAX_PATRONYMIC_LENGTH));
            validateUserRole(user.getRole());

            if (!errorsResponseThreadLocal.get().getErrors().isEmpty()) {
                throw new ValidationException(errorsResponseThreadLocal.get());
            }
        } finally {
            errorsResponseThreadLocal.remove();
        }
    }

    private void validateField(ValidationDTO validationDTO) {
        if (validationDTO.getFieldValue().isBlank()) {
            addError(validationDTO.getFieldName(), ErrorMessages.FIELD_IS_EMPTY.getMessage());
        }
        if (!validationDTO.getFieldValue().matches(validationDTO.getRegexPattern())) {
            addError(validationDTO.getFieldName(), ErrorMessages.INCORRECT_FORMAT.getMessage());
        }
        if (validationDTO.getFieldValue().length() > validationDTO.getMaxLength()) {
            addError(validationDTO.getFieldName(), ErrorMessages.REQUIRED_LENGTH.getMessage()
                    + validationDTO.getMaxLength());
        }
    }

    private void validateUserRole(UserRole role) {
        if (role == null) {
            addError(FieldNames.ROLE.getField(), ErrorMessages.FIELD_IS_EMPTY.getMessage());
        }
    }

    public void addError(String field, String message) {
        ErrorDetail error = new ErrorDetail(field, message);
        errorsResponseThreadLocal.get().addError(error);
    }
}