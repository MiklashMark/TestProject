package by.bootcamp.markmiklash.userservice.service;

import by.bootcamp.markmiklash.userservice.core.dto.UserRegistrationDTO;
import by.bootcamp.markmiklash.userservice.core.enums.messages.ErrorMessages;
import by.bootcamp.markmiklash.userservice.core.exception.custom_exceptions.DuplicateEntityException;
import by.bootcamp.markmiklash.userservice.core.exception.custom_exceptions.InternalServerException;
import by.bootcamp.markmiklash.userservice.core.utils.EntityDTOMapper;
import by.bootcamp.markmiklash.userservice.repository.api.ICrudUserRepository;
import by.bootcamp.markmiklash.userservice.repository.entity.User;
import by.bootcamp.markmiklash.userservice.service.api.IUserService;
import by.bootcamp.markmiklash.userservice.service.validation.IValidationService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService implements IUserService {
    private final ICrudUserRepository crudUserRepository;
    private final IValidationService validationService;

    public UserService(ICrudUserRepository crudUserRepository,
                       IValidationService validationService) {
        this.crudUserRepository = crudUserRepository;
        this.validationService = validationService;
    }


    @Override
    @Transactional
    public void save(UserRegistrationDTO userRegistrationDTO) {
      validationService.validateRegistration(userRegistrationDTO);
        User user = EntityDTOMapper.INSTANCE.userRegistrationDTOToUser(userRegistrationDTO);

        try {
            crudUserRepository.saveAndFlush(user); // Is used for executing catch code before closing transaction
        } catch (DataIntegrityViolationException e) {
            handleDataIntegrityViolationException(e);
        } catch (DataAccessException e) {
            handleDataAccessException(e);
        }
    }

    private void handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        Throwable cause = e.getCause();

        if (cause instanceof ConstraintViolationException constraintViolationException) {
            if (constraintViolationException.getMessage().contains("mail")) {
                throw new DuplicateEntityException(ErrorMessages.ALREADY_REGISTERED.getMessage());
            }
        }
    }

    private void handleDataAccessException(DataAccessException e) {
        throw new InternalServerException(ErrorMessages.SERVER_ERROR.getMessage());
    }

}
