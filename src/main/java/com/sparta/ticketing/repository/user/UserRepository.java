package com.sparta.ticketing.repository.user;

import com.sparta.ticketing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

}
