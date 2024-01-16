package by.bootcamp.markmiklash.userservice.repository.api;

import by.bootcamp.markmiklash.userservice.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ICrudUserRepository extends JpaRepository<User, UUID> {

}
