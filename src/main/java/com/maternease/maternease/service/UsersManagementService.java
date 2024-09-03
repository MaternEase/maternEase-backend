package com.maternease.maternease.service;

import com.maternease.maternease.dto.ReqRes;
import com.maternease.maternease.entity.OurUsers;
import com.maternease.maternease.entity.OurUsersId;
import com.maternease.maternease.repository.OurUsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UsersManagementService {

    @Autowired
    private OurUsersRepo ourUsersRepo;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public ReqRes login(ReqRes loginRequest) {
        ReqRes response = new ReqRes();
        try {
            Optional<OurUsers> existingUserByEmail = ourUsersRepo.findByEmail(loginRequest.getEmail());
            if (!existingUserByEmail.isPresent()) {
                response.setStatusCode(400);
                response.setMassage("Email not exists!");
                return response;
            }

            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
                );
            } catch (BadCredentialsException e) {
                response.setStatusCode(400);
                response.setMassage("Incorrect password!");
                return response;
            }

            var user = ourUsersRepo.findByEmail(loginRequest.getEmail()).orElseThrow();
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRole(user.getRole());
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hrs");
            response.setMassage("Successfully logged in");

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMassage(e.getMessage());
        }
        return response;
    }

    public ReqRes refreshToken(ReqRes refreshTokenReqiest) {
        ReqRes response = new ReqRes();
        try {
            String ourEmail = jwtUtils.extractUsername(refreshTokenReqiest.getToken());
            OurUsers users = ourUsersRepo.findByEmail(ourEmail).orElseThrow();
            if (jwtUtils.isTokenValid(refreshTokenReqiest.getToken(), users)) {
                var jwt = jwtUtils.generateToken(users);
                response.setToken(jwt);
                response.setRefreshToken(refreshTokenReqiest.getToken());
                response.setExpirationTime("24Hr");
                response.setMassage("Successfully Refreshed Token");
            }
            response.setStatusCode(200);
            return response;

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMassage(e.getMessage());
            return response;
        }
    }

    public ReqRes getUsersById(String role,int id) {
        ReqRes reqRes = new ReqRes();
        try {
            OurUsersId ourUsersId = new OurUsersId(role, id);
            OurUsers ourUsersById = ourUsersRepo.findById(ourUsersId).orElseThrow(() -> new RuntimeException("User Not Found"));
            reqRes.setOurUsers(ourUsersById);
            reqRes.setStatusCode(200);
            reqRes.setMassage("User with id '" + id + "' found successfully");
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMassage("Error occurred: " + e.getMessage());
        }
        return reqRes;
    }

    public ReqRes updateUser(String role, Integer userId, OurUsers updatedUser) {
        ReqRes reqRes = new ReqRes();
        try {
            OurUsersId ourUsersId = new OurUsersId(role, userId);
            Optional<OurUsers> userOptional = ourUsersRepo.findById(ourUsersId);
            if (userOptional.isPresent()) {
                OurUsers existingUser = userOptional.get();
                existingUser.setEmail(updatedUser.getEmail());
                existingUser.setFullName(updatedUser.getFullName());
                existingUser.setRole(updatedUser.getRole());

                if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                    existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                }

                OurUsers savedUser = ourUsersRepo.save(existingUser);
                reqRes.setOurUsers(savedUser);
                reqRes.setStatusCode(200);
                reqRes.setMassage("User updated successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMassage("User not found for update");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMassage("Error occurred while updating user: " + e.getMessage());
        }
        return reqRes;
    }

//
//    public ReqRes getAllUsers() {
//        ReqRes reqRes = new ReqRes();
//
//        try {
//            List<OurUsers> result = usersRepo.findAll();
//            if (!result.isEmpty()) {
//                reqRes.setOurUsersList(result);
//                reqRes.setStatusCode(200);
//                reqRes.setMassage("Successful");
//            } else {
//                reqRes.setStatusCode(404);
//                reqRes.setMassage("No users found");
//            }
//            return reqRes;
//        } catch (Exception e) {
//            reqRes.setStatusCode(500);
//            reqRes.setMassage("Error occurred: " + e.getMessage());
//            return reqRes;
//        }
//    }

    public ReqRes midwifeRegister(ReqRes req) {
        ReqRes resp = new ReqRes();

        try {
            if (req.getPassword() == null || req.getPassword().isEmpty()) {
                resp.setStatusCode(400);
                resp.setMassage("Password cannot be null or empty");
                return resp;
            }

//            Optional<OurUsers> existingUserByName = usersRepo.findByName(req.getName());
//            if (existingUserByName.isPresent()) {
//                resp.setStatusCode(400);
//                resp.setMessage("Username is taken!");
//                return resp;
//            }

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
            if (ourUsersResult.getOurUsersId().getId()>0) {
                resp.setOurUsers((ourUsersResult));
                resp.setMassage("User registered successfully");
                resp.setStatusCode(200);
            }

        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    public ReqRes doctorRegister(ReqRes req) {
        ReqRes resp = new ReqRes();

        try {
            if (req.getPassword() == null || req.getPassword().isEmpty()) {
                resp.setStatusCode(400);
                resp.setMassage("Password cannot be null or empty");
                return resp;
            }

//            Optional<OurUsers> existingUserByName = usersRepo.findByName(req.getName());
//            if (existingUserByName.isPresent()) {
//                resp.setStatusCode(400);
//                resp.setMessage("Username is taken!");
//                return resp;
//            }

            Optional<OurUsers> existingUserByEmail = ourUsersRepo.findByEmail(req.getEmail());
            if (existingUserByEmail.isPresent()) {
                resp.setStatusCode(400);
                resp.setMassage("Email is already registered!");
                return resp;
            }

            OurUsers ourUser = new OurUsers();
            OurUsersId ourUsersId = new OurUsersId(req.getRole(), 0); // Assuming id is auto-generated

            ourUser.setOurUsersId(ourUsersId);
            ourUser.setEmail(req.getEmail());
            ourUser.setPassword(passwordEncoder.encode(req.getPassword()));

            OurUsers ourUsersResult = ourUsersRepo.save(ourUser);
            if (ourUsersResult.getOurUsersId().getId()>0) {
                resp.setOurUsers((ourUsersResult));
                resp.setMassage("User registered successfully");
                resp.setStatusCode(200);
            }

        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    public ReqRes getAllMidwifes() {
        ReqRes reqRes = new ReqRes();

        try {
            List<OurUsers> result = ourUsersRepo.findByRole( "MIDWIFE");
            if (!result.isEmpty()){
                reqRes.setOurUsersList(result);
                reqRes.setStatusCode(200);
                reqRes.setMassage("Successful");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMassage("No Users Found");
            }
            return reqRes;
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMassage("Error occured: " + e.getMessage());
            return reqRes;
        }
    }

    public ReqRes getAllDoctors() {
        ReqRes reqRes = new ReqRes();

        try {
            List<OurUsers> result = ourUsersRepo.findByRoleOrRoleOrRoleOrRoleOrRole( "ADMIN", "MOTHER", "DOCTOR", "MIDWIFE", "CHILD");
            if (!result.isEmpty()){
                reqRes.setOurUsersList(result);
                reqRes.setStatusCode(200);
                reqRes.setMassage("Successful");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMassage("No Users Found");
            }
            return reqRes;
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMassage("Error occured: " + e.getMessage());
            return reqRes;
        }
    }

    public ReqRes getMyInfo(String email) {
        ReqRes reqRes = new ReqRes();
        try {
            Optional<OurUsers> userOptional = ourUsersRepo.findByEmail(email);
            if (userOptional.isPresent()) {
                reqRes.setOurUsers(userOptional.get());
                reqRes.setStatusCode(200);
                reqRes.setMassage("successful");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMassage("User not found for update");
            }

        }catch (Exception e){
            reqRes.setStatusCode(500);
            reqRes.setMassage("Error occurred while getting user info: " + e.getMessage());
        }
        return reqRes;
    }

    public ReqRes deleteUser(String role, Integer userId) {
        ReqRes reqRes = new ReqRes();
        try {
            OurUsersId ourUsersId = new OurUsersId(role, userId);
            Optional<OurUsers> userOptional = ourUsersRepo.findById(ourUsersId);
            if (userOptional.isPresent()) {
                ourUsersRepo.deleteById(ourUsersId);
                reqRes.setStatusCode(200);
                reqRes.setMassage("User deleted successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMassage("User not found for deletion");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMassage("Error occurred while deleting user: " + e.getMessage());
        }
        return reqRes;
    }

}

