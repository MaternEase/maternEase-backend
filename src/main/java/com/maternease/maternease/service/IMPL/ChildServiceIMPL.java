package com.maternease.maternease.service.IMPL;

import com.maternease.maternease.dto.AntenatalRiskConditionDTO;
import com.maternease.maternease.dto.HealthChartDTO;
import com.maternease.maternease.dto.MotherDTO;
import com.maternease.maternease.dto.ResponseDTO;
import com.maternease.maternease.dto.request.UpdateChildProfileDTO;
import com.maternease.maternease.dto.response.ResChildProfileDTO;
import com.maternease.maternease.entity.*;
import com.maternease.maternease.exception.ChildNotFoundException;
import com.maternease.maternease.exception.MotherNotFoundException;
import com.maternease.maternease.repository.*;
import com.maternease.maternease.service.ChildService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;
@Service
public class ChildServiceIMPL implements ChildService {

    @Autowired
    private MotherRepo motherRepo;

    @Autowired
    private OurUsersRepo ourUsersRepo;

    @Autowired
    private ChildProfileRepo childProfileRepo;

    @Autowired
    private ChildRepo childRepo;

    @Autowired
    private ClinicRepo clinicRepo;

    @Autowired
    private HealthChartRepo healthChartRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ResChildProfileDTO getChildProfile(String childId) {
        String[] parts = childId.split("-");
        String motherId = parts[0];

        OurUsers ourUsers = ourUsersRepo.findByChildId(childId)
                .orElseThrow(() -> new ChildNotFoundException("Child not found with id :" + childId));

        // Fetching the child using ourUsers reference to ensure ChildProfile is loaded
        Child child = childRepo.findByOurUsers_Id(ourUsers.getId())
                .orElseThrow(() -> new ChildNotFoundException("Child not found for user id: " + ourUsers.getId()));

        // Making sure that childProfile is fetched from the child entity
        ChildProfile childProfile = child.getChildProfile();

        // Check if childProfile was successfully fetched
        if (childProfile == null) {
            throw new ChildNotFoundException("Child Profile not found for the given child ID");
        }

        // Preparing the DTO for response
        ResChildProfileDTO resChildProfileDTO = new ResChildProfileDTO();
        modelMapper.map(childProfile, resChildProfileDTO); // Assuming your DTO can be populated directly

        // Additional details from associated entities
        resChildProfileDTO.setChildFullName(ourUsers.getFullName());
        resChildProfileDTO.setFieldClinicName(ourUsers.getClinicName());

        Mother mother = motherRepo.findById(motherId)
                .orElseThrow(() -> new MotherNotFoundException("Mother not found with id: " + motherId));

        resChildProfileDTO.setMotherFullName(mother.getOurUsers().getFullName());
        resChildProfileDTO.setAddress(mother.getOurUsers().getAddress());
        resChildProfileDTO.setMotherRegistrationNo(mother.getMotherId());
        resChildProfileDTO.setMotherRegitrationDate(mother.getOurUsers().getCreatedAt());
        resChildProfileDTO.setMotherAge(calculateAge(mother.getOurUsers().getDob(), new Date()));

        Clinic clinic = clinicRepo.findByClinicName(ourUsers.getClinicName());
        if (clinic != null) {
            resChildProfileDTO.setMohArea(clinic.getMOHArea());
            resChildProfileDTO.setPhmArea(clinic.getPHMArea());
            resChildProfileDTO.setGramaNiladhariDivision(clinic.getGramaniDivisions());
        }

        return resChildProfileDTO;
    }

    @Override
    public ResponseDTO updateChildProfile(String childId, UpdateChildProfileDTO updateChildProfileDTO) {
        // Fetch the child user details
        OurUsers ourUsers = ourUsersRepo.findByChildId(childId)
                .orElseThrow(() -> new ChildNotFoundException("Child not found with id :" + childId));

        // Using the user ID from ourUsers to find the corresponding Child entity
        Child child = childRepo.findByOurUsers_Id(ourUsers.getId())
                .orElseThrow(() -> new ChildNotFoundException("Child not found for user id: " + ourUsers.getId()));

        // Retrieve the existing ChildProfile
        ChildProfile childProfile = childProfileRepo.findById(child.getChildProfile().getId())
                .orElseThrow(() -> new ChildNotFoundException("Child Profile not found for id: " + child.getChildProfile().getId()));

        // Map the DTO to the existing ChildProfile entity
        modelMapper.map(updateChildProfileDTO, childProfile);

        // Save the updated profile
        childProfileRepo.save(childProfile);

        //Prepare and return success response
        ResponseDTO response = new ResponseDTO();
        response.setResponseCode("200");
        response.setResponseMzg("Update data  successfully.");
        return response;
    }

    @Override
    public ResponseDTO updateHealthChart(String childId, HealthChartDTO healthChartDTO) {
        // Configure ModelMapper locally to skip 'id' field
        modelMapper.typeMap(HealthChartDTO.class, HealthChart.class)
                .addMappings(mapper -> mapper.skip(HealthChart::setId));

        // Fetch the child user details
        OurUsers ourUsers = ourUsersRepo.findByChildId(childId)
                .orElseThrow(() -> new ChildNotFoundException("Child not found with id :" + childId));

        // Using the user ID from ourUsers to find the corresponding Child entity
        Child child = childRepo.findByOurUsers_Id(ourUsers.getId())
                .orElseThrow(() -> new ChildNotFoundException("Child not found for user id: " + ourUsers.getId()));

        HealthChart healthChart = healthChartRepo.findById(child.getHealthChart().getId())
                .orElseThrow(() -> new ChildNotFoundException("Child Profile not found for id: " + child.getHealthChart().getId()));


        modelMapper.map(healthChartDTO,healthChart);

        //save details to healthchart entity
        healthChartRepo.save(healthChart);

        //Prepare and return success response
        ResponseDTO response = new ResponseDTO();
        response.setResponseCode("200");
        response.setResponseMzg("Update data  successfully.");
        return response;
    }

    @Override
    public HealthChartDTO getHealthChartDetails(String childId) {
        // Configure ModelMapper locally to skip 'id' field
        modelMapper.typeMap(HealthChartDTO.class, HealthChart.class)
                .addMappings(mapper -> mapper.skip(HealthChart::setId));

        // Fetch the child user details
        OurUsers ourUsers = ourUsersRepo.findByChildId(childId)
                .orElseThrow(() -> new ChildNotFoundException("Child not found with id :" + childId));

        // Using the user ID from ourUsers to find the corresponding Child entity
        Child child = childRepo.findByOurUsers_Id(ourUsers.getId())
                .orElseThrow(() -> new ChildNotFoundException("Child not found for user id: " + ourUsers.getId()));

        HealthChart healthChart = healthChartRepo.findById(child.getHealthChart().getId())
                .orElseThrow(() -> new ChildNotFoundException("Child Profile not found for id: " + child.getHealthChart().getId()));


        HealthChartDTO healthChartDTO = new HealthChartDTO();
        modelMapper.map(healthChart, healthChartDTO);


        return healthChartDTO;
    }


    private int calculateAge(Date dob, Date currentDate) {
        if (dob != null) {
            LocalDate birthdate = new GregorianCalendar(dob.getYear() + 1900, dob.getMonth(), dob.getDate()).toZonedDateTime().toLocalDate();
            LocalDate now = new GregorianCalendar(currentDate.getYear() + 1900, currentDate.getMonth(), currentDate.getDate()).toZonedDateTime().toLocalDate();
            return Period.between(birthdate, now).getYears();
        } else {
            return 0;
        }
    }
}
