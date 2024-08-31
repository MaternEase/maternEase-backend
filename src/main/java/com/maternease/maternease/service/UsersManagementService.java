package com.maternease.maternease.service;

import com.maternease.maternease.dto.ReqRes;
import com.maternease.maternease.entity.Users;
import com.maternease.maternease.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class UsersManagementService {

    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ReqRes register(ReqRes registrationRequest) {
        ReqRes resp = new ReqRes();
        try {
            Optional<Users> existingUserByEmail = usersRepo.findByEmail(registrationRequest.getEmail());
            if (existingUserByEmail.isPresent()) {
                resp.setStatusCode(400);
                resp.setMassage("Email is already registered!");
                return resp;
            }

            Users ourUser = new Users();
            ourUser.setEmail(registrationRequest.getEmail());
            ourUser.setFirstName(registrationRequest.getFirstName());
            ourUser.setRole(registrationRequest.getRole());
            ourUser.setLastName(registrationRequest.getLastName());
            ourUser.setCity(registrationRequest.getCity());
            ourUser.setNic(registrationRequest.getNic());
            ourUser.setStatus(registrationRequest.getStatus());
            ourUser.setHomeNumber(registrationRequest.getHomeNumber());
            ourUser.setLane(registrationRequest.getLane());
            ourUser.setPostalCode(registrationRequest.getPostalCode());
            ourUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            Users ourUsersResult = usersRepo.save(ourUser);
            if (ourUsersResult.getId() > 0) {
                resp.setOurUsers(String.valueOf(ourUsersResult));
                resp.setMassage("User registered successfully");
                resp.setStatusCode(200);
            }
        } catch (Exception e) {
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    public ReqRes login(ReqRes loginRequest) {
        ReqRes response = new ReqRes();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
            var user = usersRepo.findByEmail(loginRequest.getEmail()).orElseThrow();
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRole(user.getRole());
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hrs");
            response.setMassage("Successfully logged in");
        } catch (BadCredentialsException e) {
            response.setStatusCode(400);
            response.setMassage("Incorrect password!");
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMassage(e.getMessage());
        }
        return response;
    }

    public ReqRes refreshToken(ReqRes refreshTokenRequest) {
        ReqRes response = new ReqRes();
        try {
            String ourEmail = jwtUtils.extractUsername(refreshTokenRequest.getToken());
            Users users = usersRepo.findByEmail(ourEmail).orElseThrow();
            if (jwtUtils.isTokenValid(refreshTokenRequest.getToken(), users)) {
                var jwt = jwtUtils.generateToken(users);
                response.setToken(jwt);
                response.setRefreshToken(refreshTokenRequest.getToken());
                response.setExpirationTime("24Hr");
                response.setMassage("Successfully Refreshed Token");
                response.setStatusCode(200);
            } else {
                response.setStatusCode(400);
                response.setMassage("Invalid Token");
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMassage(e.getMessage());
        }
        return response;
    }

    public ReqRes getUsersById(int id) {
        ReqRes reqRes = new ReqRes();
        try {
            Users usersById = usersRepo.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
            reqRes.setOurUsers(String.valueOf(usersById));
            reqRes.setStatusCode(200);
            reqRes.setMassage("Users with id '" + id + "' found successfully");
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMassage("Error occurred: " + e.getMessage());
        }
        return reqRes;
    }

    public ReqRes updateUser(Integer userId, Users updatedUser) {
        ReqRes reqRes = new ReqRes();
        try {
            Users existingUser = usersRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found for update"));
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());

            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }

            Users savedUser = usersRepo.save(existingUser);
            reqRes.setOurUsers(String.valueOf(savedUser));
            reqRes.setStatusCode(200);
            reqRes.setMassage("User updated successfully");
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMassage("Error occurred while updating user: " + e.getMessage());
        }
        return reqRes;
    }

    public ReqRes getMyInfo(String email) {
        ReqRes reqRes = new ReqRes();
        try {
            Users user = usersRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
            reqRes.setOurUsers(String.valueOf(user));
            reqRes.setStatusCode(200);
            reqRes.setMassage("Successful");
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMassage("Error occurred while getting user info: " + e.getMessage());
        }
        return reqRes;
    }
}

