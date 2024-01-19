package by.bootcamp.markmiklash.userservice.repository.entity;

import by.bootcamp.markmiklash.userservice.core.enums.UserRole;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "users", name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private UUID uuid;
    @Column(nullable = false)

    private String surname;
    @Column(nullable = false)

    private String name;
    @Column(nullable = false)

    private String patronymic;
    @Column(unique = true, nullable = false)
    private String mail;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public User() {
    }

    public User(UUID uuid, String surname,
                String name, String patronymic,
                String mail, UserRole role) {
        this.uuid = uuid;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.mail = mail;
        this.role = role;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(uuid, user.uuid) && Objects.equals(surname, user.surname) && Objects.equals(name, user.name) && Objects.equals(patronymic, user.patronymic) && Objects.equals(mail, user.mail) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, surname, name, patronymic, mail, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid=" + uuid +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", mail='" + mail + '\'' +
                ", role=" + role +
                '}';
    }
}
