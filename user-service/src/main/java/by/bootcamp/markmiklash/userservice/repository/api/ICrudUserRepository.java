package by.bootcamp.markmiklash.userservice.repository.api;

import by.bootcamp.markmiklash.userservice.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ICrudUserRepository extends JpaRepository<User, UUID> {

}
