package com.maternease.maternease.controller;

import com.maternease.maternease.dto.ReqRes;
import com.maternease.maternease.entity.OurUsers;
import com.maternease.maternease.service.UsersManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
public class UserManagementController {
    @Autowired
    private UsersManagementService usersManagementService;

    @PostMapping("/auth/midwife-register")
    public ResponseEntity<ReqRes> midwifeRegister(@RequestBody ReqRes req) {
        return ResponseEntity.ok(usersManagementService.midwifeRegister(req));
    }

    @PostMapping("/auth/doctor-register")
    public ResponseEntity<ReqRes> doctorRegister(@RequestBody ReqRes req) {
        return ResponseEntity.ok(usersManagementService.doctorRegister(req));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes req) {
        return ResponseEntity.ok(usersManagementService.login(req));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes req) {
        return ResponseEntity.ok(usersManagementService.refreshToken(req));
    }

    @GetMapping("/admin/get-all-midwifes")
    public ResponseEntity<ReqRes> getAllMidwifes() {
        return ResponseEntity.ok(usersManagementService.getAllMidwifes());
    }

    @GetMapping("/admin/get-all-doctors")
    public ResponseEntity<ReqRes> getAllDoctors() {
        return ResponseEntity.ok(usersManagementService.getAllDoctors());
    }

    @GetMapping("/admin/get-user/{role}/{userId}")
    public ResponseEntity<ReqRes> getUserById( @PathVariable Integer userId) {
        return ResponseEntity.ok(usersManagementService.getUsersById( userId));
    }

    @PutMapping("/admin/update/{role}/{userId}")
    public ResponseEntity<ReqRes> updateUser( @PathVariable Integer userId, @RequestBody OurUsers reqres) {
        return ResponseEntity.ok(usersManagementService.updateUser( userId, reqres));
    }

    @GetMapping("/anyuser/get-profile")
    public ResponseEntity<ReqRes> getMyProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        ReqRes response = usersManagementService.getMyInfo(email);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/admin/delete/{role}/{userId}")
    public ResponseEntity<ReqRes> deleteUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(usersManagementService.deleteUser( userId));
    }
}