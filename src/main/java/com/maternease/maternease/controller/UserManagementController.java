package com.maternease.maternease.controller;

import com.maternease.maternease.dto.ReqRes;
import com.maternease.maternease.entity.Users;
import com.maternease.maternease.service.UsersManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserManagementController {
    @Autowired
    private UsersManagementService usersManagementService;

    @PostMapping("/auth/register")
    public ResponseEntity<ReqRes> register(@RequestBody ReqRes reg){
        return ResponseEntity.ok(usersManagementService.register(reg));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes req){
        return ResponseEntity.ok(usersManagementService.login(req));
    }

//

    @PostMapping("/auth/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes req){
        return ResponseEntity.ok(usersManagementService.refreshToken(req));
    }

//    @GetMapping("/admin/get-all-users")
//    public ResponseEntity<ReqRes> getAllUsers(){
//        return ResponseEntity.ok(usersManagementService.getAllUsers());
//
//    }

    @GetMapping("/admin/get-users/{userId}")
    public ResponseEntity<ReqRes> getUserByID(@PathVariable Integer userId){
        return ResponseEntity.ok(usersManagementService.getUsersById(userId));

    }



    @PutMapping("/admin/update/{userId}")
    public ResponseEntity<ReqRes> updateUser(@PathVariable Integer userId, @RequestBody Users reqres){
        return ResponseEntity.ok(usersManagementService.updateUser(userId, reqres));
    }

//    @GetMapping("/adminuser/get-profile")
//    public ResponseEntity<ReqRes> getMyProfile(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = authentication.getName();
//        ReqRes response = usersManagementService.getMyInfo(email);
//        return  ResponseEntity.status(response.getStatusCode()).body(response);
//    }

//    @DeleteMapping("/admin/delete/{userId}")
//    public ResponseEntity<ReqRes> deleteUSer(@PathVariable Integer userId){
//        return ResponseEntity.ok(usersManagementService.deleteUser(userId));
//    }


}