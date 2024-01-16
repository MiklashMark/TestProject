package by.bootcamp.markmiklash.userservice.service;

import by.bootcamp.markmiklash.userservice.repository.api.ICrudUserRepository;
import by.bootcamp.markmiklash.userservice.service.api.IUserService;

public class UserService implements IUserService {
    private final ICrudUserRepository crudUserRepository;

    public UserService(ICrudUserRepository crudUserRepository) {
        this.crudUserRepository = crudUserRepository;
    }


}
