package com.maternease.maternease.service.IMPL;

import com.maternease.maternease.dto.ReqRes;
import com.maternease.maternease.entity.OurUsers;
import com.maternease.maternease.repository.OurUsersRepo;
import com.maternease.maternease.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AdminServiceIMPL implements AdminService {

    @Autowired
    private OurUsersRepo ourUsersRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public ReqRes registerMidwife(ReqRes req) {

        ReqRes resp = new ReqRes();

        try {
            if (req.getPassword() == null || req.getPassword().isEmpty()) {
                resp.setStatusCode(400);
                resp.setMassage("Password cannot be null or empty");
                return resp;
            }

            Optional<OurUsers> existingUserByEmail = ourUsersRepo.findByEmail(req.getEmail());
            if (existingUserByEmail.isPresent()) {
                resp.setStatusCode(400);
                resp.setMassage("Email is already registered!");
                return resp;
            }

            OurUsers ourUser = new OurUsers();
            ourUser.setEmail(req.getEmail());
            ourUser.setFullName(req.getFullName());
            ourUser.setNic(req.getNic());
            ourUser.setCreatedAt(new Date());  // Automatically set to the current timestamp
            ourUser.setPassword(passwordEncoder.encode(req.getPassword()));
            ourUser.setContactNo(req.getContactNo());
            ourUser.setDob(req.getDob());
            ourUser.setGender(req.getGender());
            ourUser.setRole(req.getRole());

            OurUsers ourUsersResult = ourUsersRepo.save(ourUser);
            if (ourUsersResult.getId() > 0) {
                resp.setOurUsers((ourUsersResult));
                resp.setMassage("User registered successfully");
                resp.setStatusCode(200);
            }

        } catch (Exception e) {
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    @Override
    public ReqRes registerDoctor(ReqRes req) {
        ReqRes resp = new ReqRes();

        try {
            if (req.getPassword() == null || req.getPassword().isEmpty()) {
                resp.setStatusCode(400);
                resp.setMassage("Password cannot be null or empty");
                return resp;
            }

            Optional<OurUsers> existingUserByEmail = ourUsersRepo.findByEmail(req.getEmail());
            if (existingUserByEmail.isPresent()) {
                resp.setStatusCode(400);
                resp.setMassage("Email is already registered!");
                return resp;
            }

            OurUsers ourUser = new OurUsers();
            ourUser.setRole(req.getRole());
            ourUser.setEmail(req.getEmail());
            ourUser.setPassword(passwordEncoder.encode(req.getPassword()));

            OurUsers ourUsersResult = ourUsersRepo.save(ourUser);
            if (ourUsersResult.getId() > 0) {
                resp.setOurUsers((ourUsersResult));
                resp.setMassage("User registered successfully");
                resp.setStatusCode(200);
            }

        } catch (Exception e) {
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

}
