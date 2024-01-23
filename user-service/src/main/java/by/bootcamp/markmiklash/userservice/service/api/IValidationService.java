package by.bootcamp.markmiklash.userservice.service.api;


import by.bootcamp.markmiklash.userservice.core.dto.UserRegistrationDTO;

public interface IValidationService {
    void validateRegistration(UserRegistrationDTO user);
}
