package by.bootcamp.markmiklash.userservice.core.utils;

import by.bootcamp.markmiklash.userservice.core.dto.UserRegistrationDTO;
import by.bootcamp.markmiklash.userservice.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EntityDTOMapper {
    EntityDTOMapper INSTANCE = Mappers.getMapper(EntityDTOMapper.class);


    @Mapping(target = "uuid", ignore = true)
    User userRegistrationDTOToUser(UserRegistrationDTO userRegistrationDTO);
}
