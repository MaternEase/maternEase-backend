package com.maternease.maternease.service.IMPL;

import com.maternease.maternease.dto.ClinicDTO;
import com.maternease.maternease.dto.ReqRes;
import com.maternease.maternease.dto.ResponseDTO;
import com.maternease.maternease.dto.request.MidwifeClinicAssignmentDTO;
import com.maternease.maternease.dto.response.ClinicNameDTO;
import com.maternease.maternease.entity.Clinic;
import com.maternease.maternease.entity.ClinicSchedules;
import com.maternease.maternease.entity.Midwife;
import com.maternease.maternease.entity.OurUsers;
import com.maternease.maternease.repository.ClinicRepo;
import com.maternease.maternease.repository.ClinicScheduleRepo;
import com.maternease.maternease.repository.MidwifeRepo;
import com.maternease.maternease.repository.OurUsersRepo;
import com.maternease.maternease.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
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
    private ClinicScheduleRepo clinicScheduleRepo;

    @Autowired
    private ModelMapper modelMapper;


    private String generateMidId() {
        Optional<Midwife> latestMidwife = midwifeRepo.findFirstByOrderByMidIdDesc();
        String latestId = latestMidwife.isPresent() ? latestMidwife.get().getMidId() : "MID0000";
        int num = Integer.parseInt(latestId.substring(3)) + 1;
        return "MID" + String.format("%04d", num);
    }

//    private String generateClinicId() {
//        Optional<Clinic> latestClinic = clinicRepo.findFirstByOrderByMidIdDesc();
//        String latestId = latestClinic.isPresent() ? latestClinic.get().getClinicId() : "MID0000";
//        int num = Integer.parseInt(latestId.substring(3)) + 1;
//        return "MID" + String.format("%04d", num);
//    }


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

            OurUsers savedUser = ourUsersRepo.save(ourUser);

            // Create and save the Midwife
            Midwife newMidwife = new Midwife();
            newMidwife.setOurUsers(savedUser);
            newMidwife.setMidId(generateMidId());
            Midwife savedMidwife = midwifeRepo.save(newMidwife);

            if (savedMidwife.getId() > 0) {
                resp.setOurUsers(savedUser);
                resp.setMidwife(savedMidwife);
                resp.setMassage("Midwife registered successfully");
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
//                .filter(clinic -> clinic.getAssignedMidwives().size() < 2)
                .filter(clinic -> {
                    int assignedCount = (clinic.getMidwifeOne() != null ? 1 : 0) + clinic.getReservedMidwives().size();
                    return assignedCount < 3; // Assuming max of 3 midwives per clinic
                })
                .map(clinic -> modelMapper.map(clinic, ClinicDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseDTO assignMidwifeToClinic(MidwifeClinicAssignmentDTO assignment) {
//        ResponseDTO response = new ResponseDTO();
//
//        Optional<Midwife> midwifeOpt = midwifeRepo.findById(assignment.getMidwifeId());
//        Optional<Clinic> clinicOpt = clinicRepo.findById(assignment.getClinicId());
//
//        if (midwifeOpt.isEmpty() || clinicOpt.isEmpty()) {
//            response.setResponseCode("404");
//            response.setResponseMzg("Midwife or Clinic not found.");
//            return response;
//        }
//
//        Midwife midwife = midwifeOpt.get();
//        Clinic clinic = clinicOpt.get();
//
//        // Check midwife assignment eligibility
//        if (midwife.getAssignedClinics().size() >= 3) {
//            response.setResponseCode("400");
//            response.setResponseMzg("Midwife already assigned to maximum number of clinics.");
//            return response;
//        }
//
//        // Check clinic's midwife count
//        // Check if the clinic has reached the midwife assignment limit
//        int assignedCount = (clinic.getMainMidwife() != null ? 1 : 0) + clinic.getReservedMidwives().size();
//        if (assignedCount >= 3) {
//            response.setResponseCode("400");
//            response.setResponseMzg("Clinic already has the maximum number of midwives.");
//            return response;
//        }
//
//        // Check if midwife is already assigned to this clinic
//        if (midwife.getAssignedClinics().contains(clinic)) {
//            response.setResponseCode("400");
//            response.setResponseMzg("Midwife is already assigned to this clinic.");
//            return response;
//        }

        // Proceed with assignment
//        midwife.getAssignedClinics().add(clinic);
//        clinic.getAssignedMidwives().add(midwife);

        // Assign as main midwife if none exists, else as reserved midwife
//        if (clinic.getMainMidwife() == null) {
//            clinic.setMainMidwife(midwife);
//        } else {
//            clinic.getReservedMidwives().add(midwife);
//        }
//
//        midwifeRepo.save(midwife);
//        clinicRepo.save(clinic);
//
//        response.setResponseCode("200");
//        response.setResponseMzg("Midwife assigned to the clinic successfully.");
//        return response;
        return new ResponseDTO();
    }

    @Override
    public List<ClinicNameDTO> getAllClinicNames() {
        List<Clinic> clinics = clinicRepo.findAll();
        return clinics.stream()
                .map(clinic -> {
                    ClinicNameDTO clinicNameDTO = new ClinicNameDTO();
                    clinicNameDTO.setClinicName(clinic.getClinicName());
                    return clinicNameDTO;
                })
                .collect(Collectors.toList());
    }
    @Scheduled(cron = "0 37 20 1 * ?")
    @Override
    public boolean isFirstClinicScheduled() {
        boolean isCreated = false;
        List<Clinic> clinicList = clinicRepo.findAll();
        try {
            for (Clinic clinic : clinicList) {
                ClinicSchedules clinicSchedules = new ClinicSchedules();

                // Get the current date and next month
                LocalDate today = LocalDate.now();
                LocalDate nextMonth = today.plusMonths(1).withDayOfMonth(1);

                // Get the first day of next month and the day of the week
                Calendar calendar = Calendar.getInstance();
                calendar.set(nextMonth.getYear(), nextMonth.getMonthValue() - 1, 1);  // Month is 0-based

                int firstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK);
                int clinicDayOfWeek = clinic.getClinicDay();  // Get the day of the week (e.g., Monday = 2, Sunday = 1)

                // Calculate the days to add to get to the correct weekday of the first week
                int daysToAdd = (clinicDayOfWeek - firstDayOfMonth + 7) % 7;
                calendar.add(Calendar.DAY_OF_MONTH, daysToAdd);  // Move to the correct day in the first week

                // Adjust to the correct week (1, 2, 3, or 4)
                calendar.add(Calendar.WEEK_OF_MONTH, clinic.getFirstClinicWeek() - 1);  // Adjust for the clinic's week

                clinicSchedules.setClinicDate(calendar.getTime());
                clinicSchedules.setClinic(clinic);
                clinicSchedules.setClinicType("Regular Clinic");

                System.out.println("Opening schedules for: " + nextMonth.getMonth() + " " + nextMonth.getYear());
                clinicScheduleRepo.save(clinicSchedules);
                isCreated = true;
            }
        }catch (Exception e){
            System.out.println("Error creating the schedule");
            isCreated = false;
        }
        return isCreated;
    }
}
