package com.sparta.ticketing.repository.admin;

import com.sparta.ticketing.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminUser, Long> {

}
