package com.maternease.maternease.service.IMPL;

import com.maternease.maternease.dto.AntenatalRiskConditionDTO;
import com.maternease.maternease.dto.OurUsersDTO;
import com.maternease.maternease.dto.ResponseDTO;
import com.maternease.maternease.dto.response.DMotherTableDTO;
import com.maternease.maternease.dto.response.EMotherTableDTO;
import com.maternease.maternease.entity.*;
import com.maternease.maternease.exception.MotherNotFoundException;
import com.maternease.maternease.repository.*;
import com.maternease.maternease.service.MidwifeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MidwifeServiceIMPL implements MidwifeService {

    @Autowired
    private MotherRepo motherRepo;

    @Autowired
    private AntenatalRiskConditionRepo antenatalRiskConditionRepo;

    @Autowired
    private OurUsersRepo ourUsersRepo;

    @Autowired
    private ChildProfileRepo childProfileRepo;

    @Autowired
    private ImmunizationRepo immunizationRepo;

    @Autowired
    private HealthChartRepo healthChartRepo;

    @Autowired
    private ChildRepo childRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public String generateMotherId() {
        // Find the latest Mother based on ID in descending order
        Mother latestMother = motherRepo.findTopByOrderByMotherIdDesc();

        // Default value if no existing record is found
        int newIdNumber = 1;

        // If a record exists, parse the numeric part and increment it
        if (latestMother != null) {
            String lastId = latestMother.getMotherId(); // Example: AS256

            // Ensure that the string is at least 2 characters long (for the "AS" prefix)
            if (lastId != null && lastId.length() > 2) {
                try {
                    String numericPart = lastId.substring(2); // Extract the numeric part "256"
                    newIdNumber = Integer.parseInt(numericPart) + 1;
                } catch (NumberFormatException e) {
                    // Handle the case where the numeric part is not a valid number
                    System.out.println("Error parsing numeric part of motherId: " + lastId);
                }
            }
        }

        // Return the new ID, prefixing with "AS"
        return "AS" + newIdNumber;
    }

    private String generateChildId(String motherId) {
        // Find the number of children already associated with this motherId
        int childCount = childRepo.countByMotherId(motherId);

        // Increment the count to generate the next child number
        int nextChildNumber = childCount + 1;

        // Combine the motherId with the nextChildNumber to form the childId
        return motherId + "-" + nextChildNumber;
    }




    @Override
    public ResponseDTO registerMother(OurUsersDTO ourUsersDTO) {

        modelMapper.typeMap(OurUsersDTO.class, OurUsers.class).addMappings(mapper -> {
            mapper.skip(OurUsers::setId);  // Skip mapping for the ID field

        });

        // Map OurUsersDTO to OurUsers entity
        OurUsers newUser = modelMapper.map(ourUsersDTO,OurUsers.class);



        newUser.setRole("MOTHER");
        newUser.setPassword(passwordEncoder.encode(ourUsersDTO.getNic()));  // Set initial password to NIC

        // Save the new user entity in OurUsers
        OurUsers savedUser = ourUsersRepo.save(newUser);

        //Create AntenatalRiskCondition
        AntenatalRiskCondition antenatalRiskCondition = new AntenatalRiskCondition();
        antenatalRiskCondition.setFullName(savedUser.getFullName());
        antenatalRiskCondition.setAge(savedUser.getAge());
        antenatalRiskCondition.setContactNo(savedUser.getContactNo());
        antenatalRiskCondition.setAddress(savedUser.getAddress());

        // Save the AntenatalRiskCondition entry
        AntenatalRiskCondition savedRiskConditions = antenatalRiskConditionRepo.save(antenatalRiskCondition);

        //Create and link Mother entity to OurUsers and AntenatalRiskCondition
        Mother mother = new Mother();
        mother.setMotherId(generateMotherId()); // Generate custom motherId
        mother.setNic(savedUser.getNic());
        mother.setContactNo(savedUser.getContactNo());
        mother.setStatus(0); // Expected Mother
        mother.setOurUsers(savedUser);
        mother.setAntenatalRiskCondition(savedRiskConditions);

        //Save the Mother entity
        Mother savedMother = motherRepo.save(mother);

        //Prepare and return response
        ResponseDTO response = new ResponseDTO();
        response.setResponseCode("200");
        response.setResponseMzg("Mother registered successfully.");
        return response;
    }



    @Override
    public ResponseDTO registerChild(OurUsersDTO ourUsersDTO) {

        // Check if the mother exists
        String motherName = findMotherName(ourUsersDTO.getMotherId());

        if (motherName == null) {
            throw new MotherNotFoundException("Mother not found with ID: " + ourUsersDTO.getMotherId());
        }

        // Generate the custom childId
        String childID = generateChildId(ourUsersDTO.getMotherId());


        // Manually map fields from OurUsersDTO to OurUsers entity
        OurUsers newUser = new OurUsers();
        newUser.setFullName(ourUsersDTO.getFullName());
        newUser.setRole("CHILD");
        newUser.setPassword(passwordEncoder.encode(ourUsersDTO.getNic())); // Encode NIC as password
        newUser.setNic(ourUsersDTO.getNic());
        newUser.setAddress(ourUsersDTO.getAddress());
        newUser.setContactNo(ourUsersDTO.getContactNo());
        newUser.setGender(ourUsersDTO.getGender());
        newUser.setDob(ourUsersDTO.getDob());
        newUser.setAge(ourUsersDTO.getAge());
        newUser.setChildId(childID); // Assign childId
        newUser.setClinicName(ourUsersDTO.getClinicName());


        OurUsers savedChild = ourUsersRepo.save(newUser);


        //Create ChildProfile entity and save it

        ChildProfile childProfile = new ChildProfile();

        childProfile.setBabyName(savedChild.getFullName());
        childProfile.setMotherName(motherName);
        ChildProfile savedChildProfile = childProfileRepo.save(childProfile);

        // Create Immunization entity and save it
        Immunization immunization = new Immunization();
        Immunization savedImmunization = immunizationRepo.save(immunization);

        // Create HealthChart entity and save it
        HealthChart healthChart = new HealthChart();
        HealthChart savedHealthChart = healthChartRepo.save(healthChart);

        // Create Child entity and link to OurUsers and other child-related entities
        Child child = new Child();
        child.setGuardianName(ourUsersDTO.getGuardianName());
        child.setBirth_order(ourUsersDTO.getBirth_order());
        child.setMotherId(ourUsersDTO.getMotherId());
        child.setOurUsers(savedChild);
        child.setChildProfile(savedChildProfile);
        child.setImmunization(savedImmunization);
        child.setHealthChart(savedHealthChart);

        // Save the Child entity
        Child savedChild1 = childRepo.save(child);

        // Prepare and return success response
        ResponseDTO response = new ResponseDTO();
        response.setResponseCode("200");
        response.setResponseMzg("Child registered successfully.");
        return response;
    }







    @Override
    public AntenatalRiskConditionDTO getAntenatalRiskAssessmentDetails(String motherId) {

        Mother mother = motherRepo.findById(motherId)
                .orElseThrow(() -> new MotherNotFoundException("Mother not found with id: " + motherId));

        Integer arConditionId = mother.getAntenatalRiskCondition().getId();

        Optional<AntenatalRiskCondition> antenatalRiskCondition = antenatalRiskConditionRepo.findById(arConditionId);

        return modelMapper.map(antenatalRiskCondition, AntenatalRiskConditionDTO.class);



    }

    @Override
    public ResponseDTO updateAntenatalRiskAssessmentDetails(String motherId, AntenatalRiskConditionDTO antenatalRiskConditionDTO) {

        // Configure ModelMapper locally to skip 'id' field
        modelMapper.typeMap(AntenatalRiskConditionDTO.class, AntenatalRiskCondition.class)
                .addMappings(mapper -> mapper.skip(AntenatalRiskCondition::setId));

        Mother mother = motherRepo.findById(motherId)
                .orElseThrow(() -> new MotherNotFoundException("Mother not found with id: " + motherId));

        AntenatalRiskCondition antenatalRiskCondition = mother.getAntenatalRiskCondition();
//        Optional<AntenatalRiskCondition> antenatalRiskCondition = antenatalRiskConditionRepo.findById(arConditionId);

        modelMapper.map(antenatalRiskConditionDTO, antenatalRiskCondition);

        antenatalRiskConditionRepo.save(antenatalRiskCondition);

// Prepare and return success response
        ResponseDTO response = new ResponseDTO();
        response.setResponseCode("200");
        response.setResponseMzg("Update data  successfully.");
        return response;
    }


    private String findMotherName(String motherId) {
        return motherRepo.findByMotherId(motherId)
                .map(Mother::getOurUsers)
                .map(OurUsers::getFullName)
                .orElse(null);
    }



    @Override
    public List<EMotherTableDTO> getAllExpectedMother() {
        List<Mother> expectedMothers = motherRepo.findAllByStatus(0);

        return expectedMothers.stream().map(mother -> {
            EMotherTableDTO eMotherTableDTO = new EMotherTableDTO();

            // Map the relevant fields from the Mother entity to the DTO
            eMotherTableDTO.setMotherId(mother.getMotherId());
            eMotherTableDTO.setName(mother.getOurUsers().getFullName());
            eMotherTableDTO.setAge(mother.getOurUsers().getAge());
            eMotherTableDTO.setCondition(mother.isRiskCondition());
            eMotherTableDTO.setReferToDoctor(mother.isRefdoc()); // Refer to doctor if refdoc is greater than 0
            eMotherTableDTO.setContactNo(mother.getContactNo());  // Assuming you need to fetch this date from somewhere

            return eMotherTableDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<DMotherTableDTO> getAllDeliveredMother() {
        List<Mother> deliveredMothers =motherRepo.findAllByStatus(1);
        return deliveredMothers.stream().map(mother -> {
            DMotherTableDTO dMotherTableDTO = new DMotherTableDTO();

//            // Map the relevant fields from the Mother entity to the DTO
            dMotherTableDTO.setMotherId(mother.getMotherId());
            dMotherTableDTO.setName(mother.getOurUsers().getFullName());
            dMotherTableDTO.setAge(mother.getOurUsers().getAge());
            dMotherTableDTO.setCondition(mother.isRiskCondition());
            dMotherTableDTO.setReferToDoctor(mother.isRefdoc()); // Refer to doctor if refdoc is greater than 0
            dMotherTableDTO.setDelivered_date(mother.getDelivered_date());

            return dMotherTableDTO;
        }).collect(Collectors.toList());
    }


}
