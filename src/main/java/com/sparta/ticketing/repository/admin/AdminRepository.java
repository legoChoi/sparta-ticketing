package com.sparta.ticketing.repository.admin;

import com.sparta.ticketing.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminUser, Long> {

    Optional<AdminUser> findByAdminCode(String adminCode);
}
