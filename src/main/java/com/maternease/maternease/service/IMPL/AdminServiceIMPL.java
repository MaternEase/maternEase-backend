package com.maternease.maternease.service.IMPL;

import com.maternease.maternease.dto.ClinicDTO;
import com.maternease.maternease.dto.HealthChartDTO;
import com.maternease.maternease.dto.ReqRes;
import com.maternease.maternease.dto.ResponseDTO;
import com.maternease.maternease.dto.request.MidwifeClinicAssignmentDTO;
import com.maternease.maternease.entity.Clinic;
import com.maternease.maternease.entity.HealthChart;
import com.maternease.maternease.entity.Midwife;
import com.maternease.maternease.entity.OurUsers;
import com.maternease.maternease.repository.ClinicRepo;
import com.maternease.maternease.repository.MidwifeRepo;
import com.maternease.maternease.repository.OurUsersRepo;
import com.maternease.maternease.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminServiceIMPL implements AdminService {

    @Autowired
    private OurUsersRepo ourUsersRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClinicRepo clinicRepo;

    @Autowired
    private MidwifeRepo midwifeRepo;

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

    @Override
    public List<ClinicDTO> getAllClinics() {
        return clinicRepo.findAll().stream()
                .map(clinic -> modelMapper.map(clinic, ClinicDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReqRes> getAllMidwives() {
        return ourUsersRepo.findByRole("MIDWIFE").stream()
                .map(user -> modelMapper.map(user, ReqRes.class))
                .collect(Collectors.toList());
    }

    @Override
//    public List<ReqRes> getAssignableMidwives() {
//        return midwifeRepo.findByRole("MIDWIFE").stream()
//                .filter(midwife -> midwife.getAssignedClinics().size() < 3)
//                .map(user -> modelMapper.map(user, ReqRes.class))
//                .collect(Collectors.toList());
//    }

//    public List<ReqRes> getAssignableMidwives() {
//        return midwifeRepo.findAssignableMidwives().stream()
//                .map(midwife -> modelMapper.map(midwife, ReqRes.class))
//                .collect(Collectors.toList());
//    }

    public List<ReqRes> getAssignableMidwives() {
        int maxClinicAssignments = 3; // Specify the max clinic assignments allowed
        return midwifeRepo.findAssignableMidwives(maxClinicAssignments).stream()
                .map(midwife -> modelMapper.map(midwife, ReqRes.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ClinicDTO> getClinicsNeedingMidwives() {
        return clinicRepo.findAll().stream()
                .filter(clinic -> clinic.getAssignedMidwives().size() < 2)
                .map(clinic -> modelMapper.map(clinic, ClinicDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseDTO assignMidwifeToClinic(MidwifeClinicAssignmentDTO assignment) {
        ResponseDTO response = new ResponseDTO();

        Optional<Midwife> midwifeOpt = midwifeRepo.findById(assignment.getMidwifeId());
        Optional<Clinic> clinicOpt = clinicRepo.findById(assignment.getClinicId());

        if (midwifeOpt.isEmpty() || clinicOpt.isEmpty()) {
            response.setResponseCode("404");
            response.setResponseMzg("Midwife or Clinic not found.");
            return response;
        }

        Midwife midwife = midwifeOpt.get();
        Clinic clinic = clinicOpt.get();

        // Check midwife assignment eligibility
        if (midwife.getAssignedClinics().size() >= 3) {
            response.setResponseCode("400");
            response.setResponseMzg("Midwife already assigned to maximum number of clinics.");
            return response;
        }

        // Check clinic's midwife count
        // Check if the clinic has reached the midwife assignment limit
        if (clinic.getAssignedMidwives().size() >= 3) {
            response.setResponseCode("400");
            response.setResponseMzg("Clinic already has the maximum number of midwives.");
            return response;
        }

        // Check if midwife is already assigned to this clinic
        if (midwife.getAssignedClinics().contains(clinic)) {
            response.setResponseCode("400");
            response.setResponseMzg("Midwife is already assigned to this clinic.");
            return response;
        }

        // Proceed with assignment
        midwife.getAssignedClinics().add(clinic);
        clinic.getAssignedMidwives().add(midwife);

        midwifeRepo.save(midwife);
        clinicRepo.save(clinic);

        response.setResponseCode("200");
        response.setResponseMzg("Midwife assigned to the clinic successfully.");
        return response;
    }
}
