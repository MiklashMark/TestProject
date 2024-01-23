package by.bootcamp.markmiklash.userservice.service;

import by.bootcamp.markmiklash.userservice.core.dto.UserRegistrationDTO;
import by.bootcamp.markmiklash.userservice.core.enums.UserRole;
import by.bootcamp.markmiklash.userservice.core.error.ErrorDetail;
import by.bootcamp.markmiklash.userservice.core.error.StructuredErrorResponse;
import by.bootcamp.markmiklash.userservice.core.exception.custom_exceptions.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testng.Assert;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ValidationServiceTest {
    @Mock
    private ThreadLocal<StructuredErrorResponse> structuredErrorResponseThreadLocale;

    @InjectMocks
    private ValidationService validationService;

    @Test
    public void validateSuccessfully() {
        UserRegistrationDTO userRegistrationDTO = createValidRegistrationDTO();
        assertDoesNotThrow(() -> validationService.validateRegistration(userRegistrationDTO));

        verify(structuredErrorResponseThreadLocale, times(0)).get();
    }
    @Test
    public void validateInvalidData() {
        UserRegistrationDTO userRegistrationDTO = createValidRegistrationDTO();
        userRegistrationDTO.setName("Марк");
        Assert.assertThrows(ValidationException.class,
                () -> validationService.validateRegistration(userRegistrationDTO));
    }

    public UserRegistrationDTO createValidRegistrationDTO() {
        UserRegistrationDTO user = new UserRegistrationDTO();
        user.setName("Mark");
        user.setSurname("Miklash");
        user.setPatronymic("Vladislavovich");
        user.setMail("markmiklash@gmail.com");
        user.setRole(UserRole.ADMIN);
        return user;
    }
}
