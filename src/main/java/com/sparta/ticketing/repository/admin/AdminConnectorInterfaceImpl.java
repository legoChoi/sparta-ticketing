package com.sparta.ticketing.repository.admin;

import com.sparta.ticketing.config.PasswordEncoder;
import com.sparta.ticketing.dto.admin.AdminRequest;
import com.sparta.ticketing.dto.admin.UpdateAdminRequest;
import com.sparta.ticketing.entity.AdminUser;
import com.sparta.ticketing.service.admin.AdminConnectorInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AdminConnectorInterfaceImpl implements AdminConnectorInterface {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public AdminUser addAdmin(AdminRequest adminRequest) {
        String encodedPassword = passwordEncoder.encode(adminRequest.getPassword());
        AdminUser adminUser = AdminUser.from(adminRequest.getAdminCode(), encodedPassword);

        return adminRepository.save(adminUser);
    }

    
    @Override
    @Transactional
    public AdminUser updateAdmin(UpdateAdminRequest adminRequest) {
        AdminUser byId = findById(adminRequest.getId());
        if (!adminRequest.getAdminCode().equals(byId.getAdminCode())) {
            byId.updateAdminCode(adminRequest.getAdminCode());
        }
        if (!passwordEncoder.matches(adminRequest.getPassword(), byId.getPassword())) {
            String encodePassword = passwordEncoder.encode(adminRequest.getPassword());
            byId.updatePassword(encodePassword);
        }
        return byId;
    }

    @Override
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    @Override
    public AdminUser findByAdminCode(String adminCode) {
        return adminRepository.findByAdminCode(adminCode).orElseThrow(()->new IllegalArgumentException("유효하지않은 관리자코드"));
    }

    private AdminUser findById(long adminId) {
        return adminRepository.findById(adminId).orElseThrow(() -> new IllegalArgumentException("관리자 계정을 찾을수 없음"));
    }
}
