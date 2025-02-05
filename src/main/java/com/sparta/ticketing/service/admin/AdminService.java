package com.sparta.ticketing.service.admin;

import com.sparta.ticketing.dto.admin.AdminRequest;
import com.sparta.ticketing.dto.admin.AdminResponse;
import com.sparta.ticketing.dto.admin.UpdateAdminRequest;
import com.sparta.ticketing.entity.AdminUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminConnectorInterface adminConnectorInterface;

    public AdminResponse addAdmin(AdminRequest adminRequest) {
        AdminUser admin = adminConnectorInterface.addAdmin(adminRequest);
        return AdminResponse.from(admin);
    }

    public AdminResponse updateAdmin(UpdateAdminRequest adminRequest) {
        AdminUser adminUser = adminConnectorInterface.updateAdmin(adminRequest);
        return AdminResponse.from(adminUser);
    }

    public void deleteAdmin(Long id) {
        adminConnectorInterface.deleteAdmin(id);
    }
}
