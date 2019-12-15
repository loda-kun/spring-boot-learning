package me.loda.spring.openapi3.repository;

import me.loda.spring.openapi3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 *
 * @since 2019-06-06
 * Github: https://github.com/loda-kun
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
