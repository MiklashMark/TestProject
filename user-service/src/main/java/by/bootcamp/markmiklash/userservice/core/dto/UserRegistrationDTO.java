package by.bootcamp.markmiklash.userservice.core.dto;

import by.bootcamp.markmiklash.userservice.core.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDTO {
    private String surname;
    private String name;
    private String patronymic;
    private String mail;
    private UserRole role;
}
