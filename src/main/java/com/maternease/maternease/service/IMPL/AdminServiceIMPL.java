package com.maternease.maternease.service.IMPL;

import com.maternease.maternease.dto.ClinicDTO;
import com.maternease.maternease.dto.HealthChartDTO;
import com.maternease.maternease.dto.ReqRes;
import com.maternease.maternease.dto.ResponseDTO;
import com.maternease.maternease.entity.Clinic;
import com.maternease.maternease.entity.HealthChart;
import com.maternease.maternease.entity.OurUsers;
import com.maternease.maternease.repository.ClinicRepo;
import com.maternease.maternease.repository.OurUsersRepo;
import com.maternease.maternease.service.AdminService;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private ClinicRepo clinicRepo;

    @Autowired
    private ModelMapper modelMapper;


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

    @Override
    public ResponseDTO registerClinic(ClinicDTO clinicDTO) {
        ResponseDTO response = new ResponseDTO();
        // Check if clinicName is already registered
        Optional<Clinic> existingClinic = Optional.ofNullable(clinicRepo.findByClinicName(clinicDTO.getClinicName()));
        if (existingClinic.isPresent()) {
            response.setResponseCode("400");
            response.setResponseMzg("Clinic with the name '" + clinicDTO.getClinicName() + "' is already registered.");
            return response;
        }

        modelMapper.typeMap(ClinicDTO.class, Clinic.class)
                .addMappings(mapper -> mapper.skip(Clinic::setClinicId));

        // Map ClinicDTO to Clinic entity
        Clinic clinic = modelMapper.map(clinicDTO, Clinic.class);

        // Save the new Clinic
        clinicRepo.save(clinic);

        // Set successful response
        response.setResponseCode("200");
        response.setResponseMzg("Clinic registered successfully.");
        return response;
    }
    

}
