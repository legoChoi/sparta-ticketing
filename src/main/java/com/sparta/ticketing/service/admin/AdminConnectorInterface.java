package com.sparta.ticketing.service.admin;

import com.sparta.ticketing.dto.admin.AdminRequest;
import com.sparta.ticketing.dto.admin.AdminResponse;
import com.sparta.ticketing.dto.admin.UpdateAdminRequest;
import com.sparta.ticketing.entity.AdminUser;
import org.springframework.stereotype.Component;

@Component
public interface AdminConnectorInterface {
    AdminUser addAdmin(AdminRequest adminRequest);

    AdminUser updateAdmin(UpdateAdminRequest adminRequest);

    void deleteAdmin(Long id);
}
