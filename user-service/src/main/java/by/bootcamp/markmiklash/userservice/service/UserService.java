package by.bootcamp.markmiklash.userservice.service;

import by.bootcamp.markmiklash.userservice.core.dto.UserRegistrationDTO;
import by.bootcamp.markmiklash.userservice.core.utils.EntityDTOMapper;
import by.bootcamp.markmiklash.userservice.repository.api.ICrudUserRepository;
import by.bootcamp.markmiklash.userservice.repository.entity.User;
import by.bootcamp.markmiklash.userservice.service.api.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private final ICrudUserRepository crudUserRepository;

    public UserService(ICrudUserRepository crudUserRepository) {
        this.crudUserRepository = crudUserRepository;
    }


    @Override
    public void save(UserRegistrationDTO userRegistrationDTO) {
        User user = EntityDTOMapper.INSTANCE.userRegistrationDTOToUser(userRegistrationDTO);
        crudUserRepository.save(user);
    }
}
