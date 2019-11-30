package com.labs.springswagger.repository;

import com.labs.springswagger.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
