package com.maternease.maternease.service.IMPL;

import com.maternease.maternease.dto.request.LocationRequestDTO;
import com.maternease.maternease.dto.ResponseDTO;
import com.maternease.maternease.entity.Location;
import com.maternease.maternease.entity.OurUsers;
import com.maternease.maternease.exception.LocationNotFoundException;
import com.maternease.maternease.repository.LocationRepo;
import com.maternease.maternease.repository.OurUsersRepo;
import com.maternease.maternease.service.LocationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocationServiceIMPL implements LocationService {

    @Autowired
    private LocationRepo locationRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OurUsersRepo ourUsersRepo;

    @Override
    public ResponseDTO saveLocation(LocationRequestDTO locationDTO) {
        // Retrieve the user based on the userId from locationDTO
        OurUsers user = ourUsersRepo.findById(locationDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the user already has a location
        Optional<Location> existingLocationOpt = locationRepo.findByOurUsers(user);
        Location location;


            // If the location doesn't exist, create a new one
            location = new Location();
            location.setLatitude(locationDTO.getLatitude());
            location.setLongitude(locationDTO.getLongitude());
            location.setAddress(locationDTO.getAddress());
            location.setOurUsers(user);  // Set the user for the location
        

        // Save the location (either new or updated)
        locationRepo.save(location);

        // Return success response
        ResponseDTO response = new ResponseDTO();
        response.setResponseCode("200");
        response.setResponseMzg("Location saved successfully.");
        return response;
    }


    @Override
    public LocationRequestDTO getLocationByUserId(int userId) {
        // Find the user by userId
        OurUsers user = ourUsersRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Find the location associated with the user
        Location location = locationRepo.findByOurUsers(user)
                .orElseThrow(() -> new RuntimeException("Location not found for the user"));

        // Map Location to LocationRequestDTO
        LocationRequestDTO locationDTO = modelMapper.map(location, LocationRequestDTO.class);

        // Manually set userId and role from the OurUsers entity
        locationDTO.setUserId(user.getId());  // Assuming 'id' is the userId
        locationDTO.setRole(user.getRole());  // Assuming 'role' is the role field

        return locationDTO;
    }


    @Override
    public ResponseDTO updateLocation(int locationId, LocationRequestDTO locationDTO) {
        Location location = locationRepo.findById(locationId)
                .orElseThrow(() -> new LocationNotFoundException("Location not found with id: " + locationId));

        modelMapper.map(locationDTO, location);
        locationRepo.save(location);

        ResponseDTO response = new ResponseDTO();
        response.setResponseCode("200");
        response.setResponseMzg("Location updated successfully.");
        return response;
    }
}
