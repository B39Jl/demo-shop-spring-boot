package com.qcentrifuge.db.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRep extends JpaRepository<Users, Long> {

    Optional<Users> findById(Long aLong);

    Users findByLogin(String login);

}
