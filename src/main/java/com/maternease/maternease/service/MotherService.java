
package com.maternease.maternease.service;

import com.maternease.maternease.dto.response.MProfileDetailsDTO;
public interface MotherService {
    MProfileDetailsDTO getMotherProfile(String motherId);
}
