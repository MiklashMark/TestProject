package by.bootcamp.markmiklash.userservice.service.api;

import by.bootcamp.markmiklash.userservice.core.dto.UserRegistrationDTO;

public interface IUserService {
    void save(UserRegistrationDTO userRegistrationDTO);
}
