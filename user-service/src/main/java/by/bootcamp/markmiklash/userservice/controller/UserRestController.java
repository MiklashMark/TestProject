package by.bootcamp.markmiklash.userservice.controller;

import by.bootcamp.markmiklash.userservice.core.dto.PageOfUsersDTO;
import by.bootcamp.markmiklash.userservice.core.dto.UserRegistrationDTO;
import by.bootcamp.markmiklash.userservice.core.enums.messages.LogMessages;
import by.bootcamp.markmiklash.userservice.core.enums.messages.Messages;
import by.bootcamp.markmiklash.userservice.service.api.IUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Log4j2
public class UserRestController {
    private final IUserService userService;

    public UserRestController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/registration", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        log.info(LogMessages.START_USER_REGISTRATION.getMessage() + userRegistrationDTO.getMail());
        userService.save(userRegistrationDTO);
        log.info(LogMessages.USER_REGISTRATION_SUCCESS.getMessage());
        return ResponseEntity.status(201).body(Messages.REGISTERED_SUCCESSFULLY.getMessage());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageOfUsersDTO> getPage(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size ) {
        log.info(LogMessages.START_GET_USER_PAGE.getMessage());
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(userService.getPage(pageable), HttpStatus.OK);
    }
}
