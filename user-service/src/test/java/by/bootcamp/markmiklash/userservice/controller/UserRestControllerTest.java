package by.bootcamp.markmiklash.userservice.controller;

import by.bootcamp.markmiklash.userservice.core.dto.PageOfUsersDTO;
import by.bootcamp.markmiklash.userservice.core.dto.UserDTO;
import by.bootcamp.markmiklash.userservice.core.dto.UserRegistrationDTO;
import by.bootcamp.markmiklash.userservice.core.enums.UserRole;
import by.bootcamp.markmiklash.userservice.service.api.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class UserRestControllerTest {
    @Mock
    private IUserService userService;

    @InjectMocks
    private UserRestController userRestController;

    private MockMvc mockMvc;
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userRestController).build();
        mapper = new ObjectMapper();
    }

    private UserRegistrationDTO createValidUserRegistrationDTO() {
        return new UserRegistrationDTO(
                "Miklash", "Mark", "Vladislavovich",
                "markmiklash@gmail.com", UserRole.ADMIN);
    }

    @Test
    void register() throws Exception {
        UserRegistrationDTO registrationDTO = createValidUserRegistrationDTO();

        String userJson = mapper.writeValueAsString(registrationDTO);

        mockMvc.perform(post("/users/registration")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(userJson))
                .andExpect(status().isCreated());

        verify(userService, times(1)).save(any(UserRegistrationDTO.class));
    }

    @Test
    void getPage() throws Exception {
        List<UserDTO> userDTOS = Collections.emptyList();
        PageOfUsersDTO pageOfUsersDTO = new PageOfUsersDTO(1, 10,
                1, 10, 1, true, true, userDTOS);

        when(userService.getPage(any(Pageable.class))).thenReturn(pageOfUsersDTO);
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
        verify(userService, times(1)).getPage(any(Pageable.class));
    }
}

