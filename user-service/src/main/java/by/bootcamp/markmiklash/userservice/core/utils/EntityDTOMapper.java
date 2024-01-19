package by.bootcamp.markmiklash.userservice.core.utils;

import by.bootcamp.markmiklash.userservice.core.dto.UserDTO;
import by.bootcamp.markmiklash.userservice.core.dto.UserRegistrationDTO;
import by.bootcamp.markmiklash.userservice.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EntityDTOMapper {
    EntityDTOMapper INSTANCE = Mappers.getMapper(EntityDTOMapper.class);


    @Mapping(target = "uuid", ignore = true)
    User userRegistrationDTOToUser(UserRegistrationDTO userRegistrationDTO);
    UserDTO userEntityToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);

    default List<UserDTO> userListToUserDTOList(List<User> users) {
        return users.stream().map(INSTANCE :: userEntityToUserDTO).toList();
    }
    default List<User> userDTOListToUserList(List<UserDTO> userDTO) {
        return userDTO.stream().map(INSTANCE :: userDTOToUser).toList();
    }
}
