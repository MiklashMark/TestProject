package by.bootcamp.markmiklash.userservice.service.api;

import by.bootcamp.markmiklash.userservice.core.dto.PageOfUsersDTO;
import by.bootcamp.markmiklash.userservice.core.dto.UserRegistrationDTO;
import org.springframework.data.domain.Pageable;


public interface IUserService {
    void save(UserRegistrationDTO userRegistrationDTO);
    PageOfUsersDTO getPage(Pageable usersPage);
}
