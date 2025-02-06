package com.sparta.ticketing.controller.admin;

import com.sparta.ticketing.aop.annotation.OnlyAdmin;
import com.sparta.ticketing.dto.admin.AdminRequest;
import com.sparta.ticketing.dto.admin.AdminResponse;
import com.sparta.ticketing.dto.admin.UpdateAdminRequest;
import com.sparta.ticketing.service.admin.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@OnlyAdmin
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping
    public ResponseEntity<AdminResponse> addAdmin(@RequestBody AdminRequest adminRequest) {
        AdminResponse adminResponse = adminService.addAdmin(adminRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(adminResponse);
    }

    @PatchMapping
    public ResponseEntity<AdminResponse> updateAdmin(
            @RequestBody AdminRequest adminRequest,
            HttpServletRequest httpServletRequest
    ) {
        Long id = (Long) httpServletRequest.getAttribute("userId");
        UpdateAdminRequest updateAdminRequest = UpdateAdminRequest.from(adminRequest, id);
        AdminResponse adminResponse = adminService.updateAdmin(updateAdminRequest);
        return ResponseEntity.status(HttpStatus.OK).body(adminResponse);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAdmin(HttpServletRequest httpServletRequest) {
        Long id = (Long) httpServletRequest.getAttribute("userId");
        adminService.deleteAdmin(id);
        return ResponseEntity.ok().build();
    }

}
