package by.bootcamp.markmiklash.userservice.service;

import by.bootcamp.markmiklash.userservice.core.dto.UserDTO;
import by.bootcamp.markmiklash.userservice.core.dto.UserRegistrationDTO;
import by.bootcamp.markmiklash.userservice.core.enums.UserRole;
import by.bootcamp.markmiklash.userservice.core.error.StructuredErrorResponse;
import by.bootcamp.markmiklash.userservice.core.exception.custom_exceptions.EntityNotFoundException;
import by.bootcamp.markmiklash.userservice.core.exception.custom_exceptions.ValidationException;
import by.bootcamp.markmiklash.userservice.core.utils.EntityDTOMapper;
import by.bootcamp.markmiklash.userservice.repository.api.ICrudUserRepository;
import by.bootcamp.markmiklash.userservice.repository.entity.User;
import by.bootcamp.markmiklash.userservice.service.api.IValidationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private ICrudUserRepository crudUserRepository;

    @Mock
    private IValidationService validationService;
    @Mock
    private Pageable pageable;
    @InjectMocks
    private UserService userService;

    @Test
    public void saveSuccessfully() {
        UserRegistrationDTO userRegistrationDTO = createValidRegistrationDTO();
        userService.save(userRegistrationDTO);

        verify(validationService, times(1)).validateRegistration(userRegistrationDTO);
        verify(crudUserRepository, times(1)).saveAndFlush(any(User.class));
    }

    @Test
    public void invalidDataSave() {
        UserRegistrationDTO invalidUser = createValidRegistrationDTO();

        doThrow(new ValidationException(new StructuredErrorResponse()))
                .when(validationService)
                .validateRegistration(invalidUser);

        Assert.assertThrows(ValidationException.class, () -> userService.save(invalidUser));
        verify(crudUserRepository, never()).saveAndFlush(any(User.class));
    }

    @Test
    public void getPageSuccessfully() {
        List<User> users = new ArrayList<>();
        users.add(createUser());
        Page<User> userPage = new PageImpl<>(users);

        when(crudUserRepository.findAll(any(Pageable.class))).thenReturn(userPage);

        pageable = PageRequest.of(1,10);
        List<UserDTO> resultUsers = userService.getPage(pageable).getContent();
        List<UserDTO> userDTOS = EntityDTOMapper.INSTANCE.userListToUserDTOList(users);

        Assert.assertEquals(userDTOS.get(0), resultUsers.get(0));

        verify(crudUserRepository, times(1)).findAll(any(Pageable.class));

    }
    @Test
    public void getEmptyPage() {
        Page<User> emptyUsersPage = new PageImpl<>(Collections.emptyList());

        pageable = PageRequest.of(1,10);
        when(crudUserRepository.findAll(any(Pageable.class))).thenReturn(emptyUsersPage);
        Assert.assertThrows(EntityNotFoundException.class, () -> userService.getPage(pageable));

        verify(crudUserRepository, times(1)).findAll(any(Pageable.class));
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
    private User createUser() {
        User user = new User();
        user.setUuid(UUID.randomUUID());
        user.setName("Mark");
        user.setSurname("Miklash");
        user.setPatronymic("Vladislavovich");
        user.setMail("markmiklash@gmail.com");
        user.setRole(UserRole.ADMIN);
        return user;
    }
}

