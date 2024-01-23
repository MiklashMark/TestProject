package by.bootcamp.markmiklash.userservice.service;

import by.bootcamp.markmiklash.userservice.core.dto.PageOfUsersDTO;
import by.bootcamp.markmiklash.userservice.core.dto.UserDTO;
import by.bootcamp.markmiklash.userservice.core.dto.UserRegistrationDTO;
import by.bootcamp.markmiklash.userservice.core.enums.FieldNames;
import by.bootcamp.markmiklash.userservice.core.enums.messages.ErrorMessages;
import by.bootcamp.markmiklash.userservice.core.enums.messages.LogMessages;
import by.bootcamp.markmiklash.userservice.core.exception.custom_exceptions.DuplicateEntityException;
import by.bootcamp.markmiklash.userservice.core.exception.custom_exceptions.EntityNotFoundException;
import by.bootcamp.markmiklash.userservice.core.exception.custom_exceptions.InternalServerException;
import by.bootcamp.markmiklash.userservice.core.exception.custom_exceptions.ValidationException;
import by.bootcamp.markmiklash.userservice.core.utils.EntityDTOMapper;
import by.bootcamp.markmiklash.userservice.repository.api.ICrudUserRepository;
import by.bootcamp.markmiklash.userservice.repository.entity.User;
import by.bootcamp.markmiklash.userservice.service.api.IUserService;
import by.bootcamp.markmiklash.userservice.service.api.IValidationService;
import lombok.extern.log4j.Log4j2;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
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
        try {
            validationService.validateRegistration(userRegistrationDTO);
            User user = EntityDTOMapper.INSTANCE.userRegistrationDTOToUser(userRegistrationDTO);
            crudUserRepository.saveAndFlush(user);
            log.info(LogMessages.USER_REGISTERED_SUCCESSFULLY.getMessage(), user.getMail());
        } catch (ValidationException e) {
            log.warn(LogMessages.USER_REGISTRATION_VALIDATION_FAILED.getMessage(), e.getMessage());
        } catch (DataIntegrityViolationException e) {
            handleDataIntegrityViolationException(e);
        } catch (DataAccessException e) {
            handleDataAccessException(e);
        }
    }

    @Override
    public PageOfUsersDTO getPage(Pageable pageable) {
        try {
            Page<User> usersPage = getUsers(pageable);
            checkIfUsersPageIsEmpty(usersPage);
            return buildPageOfUserDTO(usersPage);
        } catch (DataAccessException e) {
            log.error(LogMessages.ERROR_RETRIEVING_USER_PAGE.getMessage(), e.getMessage());
            throw new InternalServerException(ErrorMessages.SERVER_ERROR.getMessage());
        }
    }

    private Page<User> getUsers(Pageable pageable) {
        Sort sort = Sort.by(Sort.Direction.ASC, FieldNames.MAIL.getField());
        Pageable pageableWithSort = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                sort);
        try {
            return crudUserRepository.findAll(pageableWithSort);
        } catch (DataAccessException e) {
            throw new InternalServerException(ErrorMessages.SERVER_ERROR.getMessage());
        }
    }

    private void checkIfUsersPageIsEmpty(Page<User> usersPage) {
        if (usersPage.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessages.DATA_NOT_FOUND.getMessage());
        }
    }

    private PageOfUsersDTO buildPageOfUserDTO(Page<User> usersPage) {
        PageOfUsersDTO pageOfUsersDTO = new PageOfUsersDTO();

        pageOfUsersDTO.setSize(usersPage.getSize());
        pageOfUsersDTO.setNumber(usersPage.getNumber());
        pageOfUsersDTO.setTotalPages(usersPage.getTotalPages());
        pageOfUsersDTO.setTotalElements(usersPage.getTotalElements());
        pageOfUsersDTO.setNumberOfElements(usersPage.getNumberOfElements());
        pageOfUsersDTO.setFirst(usersPage.isFirst());
        pageOfUsersDTO.setLast(usersPage.isLast());

        List<UserDTO> usersDTO = EntityDTOMapper.INSTANCE
                .userListToUserDTOList(usersPage.getContent());
        pageOfUsersDTO.setContent(usersDTO);

        return pageOfUsersDTO;
    }

    private void handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        Throwable cause = e.getCause();

        if (cause instanceof ConstraintViolationException constraintViolationException) {
            if (constraintViolationException.getMessage().contains(FieldNames.MAIL.getField())) {
                log.warn(LogMessages.ATTEMPTED_DUPLICATE_EMAIL.getMessage());
                throw new DuplicateEntityException(ErrorMessages.ALREADY_REGISTERED.getMessage(), e);
            }
        }
    }

    private void handleDataAccessException(DataAccessException e) {
        log.error(LogMessages.DATA_ACCESS_ERROR.getMessage(), e.getMessage());
        throw new InternalServerException(ErrorMessages.SERVER_ERROR.getMessage(), e);
    }
}