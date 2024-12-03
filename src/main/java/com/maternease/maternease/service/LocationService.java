package com.maternease.maternease.service;

import com.maternease.maternease.dto.request.LocationRequestDTO;
import com.maternease.maternease.dto.ResponseDTO;

import java.util.List;

public interface LocationService {
    ResponseDTO saveLocation(LocationRequestDTO locationDTO);

//    LocationRequestDTO getLocationById(int locationId);

    ResponseDTO updateLocation(int locationId, LocationRequestDTO locationDTO);

    LocationRequestDTO getLocationByUserId(int userId);
}
