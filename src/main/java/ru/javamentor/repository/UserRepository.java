package ru.javamentor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javamentor.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String name);
    Optional<User> findIdByUsername(String name);
}
