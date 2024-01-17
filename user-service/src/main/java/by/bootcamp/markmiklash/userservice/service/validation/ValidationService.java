package by.bootcamp.markmiklash.userservice.service.validation;

import by.bootcamp.markmiklash.userservice.core.dto.UserRegistrationDTO;
import by.bootcamp.markmiklash.userservice.core.dto.ValidationDTO;
import by.bootcamp.markmiklash.userservice.core.enums.ErrorFieldNames;
import by.bootcamp.markmiklash.userservice.core.enums.UserRole;
import by.bootcamp.markmiklash.userservice.core.enums.ValidationPattern;
import by.bootcamp.markmiklash.userservice.core.enums.messages.ErrorMessages;
import by.bootcamp.markmiklash.userservice.core.error.ErrorDetail;
import by.bootcamp.markmiklash.userservice.core.error.StructuredErrorResponse;
import by.bootcamp.markmiklash.userservice.core.exception.custom_exceptions.ValidationException;
import org.springframework.stereotype.Service;


@Service
public class ValidationService implements IValidationService {
    private StructuredErrorResponse errorsResponse;
    private static final int MAX_MAIL_LENGTH = 50;
    private static final int MAX_NAME_LENGTH = 20;
    private static final int MAX_SURNAME_LENGTH = 40;
    private static final int MAX_PATRONYMIC_LENGTH = 40;

    public ValidationService(StructuredErrorResponse errorsResponse) {
        this.errorsResponse = errorsResponse;
    }

    @Override
    public void validateRegistration(UserRegistrationDTO user) {
        errorsResponse = new StructuredErrorResponse();

        validateField(new ValidationDTO(user.getMail(), ErrorFieldNames.MAIL.getField(),
                ValidationPattern.EMAIL.getPattern(), MAX_MAIL_LENGTH));
        validateField(new ValidationDTO(user.getName(), ErrorFieldNames.NAME.getField(),
                ValidationPattern.FIO.getPattern(), MAX_NAME_LENGTH));
        validateField(new ValidationDTO(user.getSurname(), ErrorFieldNames.SURNAME.getField(),
                ValidationPattern.FIO.getPattern(), MAX_SURNAME_LENGTH));
        validateField(new ValidationDTO(user.getPatronymic(), ErrorFieldNames.PATRONYMIC.getField(),
                ValidationPattern.FIO.getPattern(), MAX_PATRONYMIC_LENGTH));
        validateUserRole(user.getRole());

        if (!errorsResponse.getErrors().isEmpty()) {
            throw new ValidationException(errorsResponse);
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
            addError(validationDTO.getFieldName(),ErrorMessages.REQUIRED_LENGTH.getMessage()+ String.valueOf(validationDTO.getMaxLength()));
        }
    }

    private void validateUserRole(UserRole role) {
        if (role == null) {
            addError(ErrorFieldNames.ROLE.getField(), ErrorMessages.FIELD_IS_EMPTY.getMessage());
        }
    }

    private void addError(String field, String message) {
        ErrorDetail error = new ErrorDetail(field, message);
        errorsResponse.addError(error);
    }
}