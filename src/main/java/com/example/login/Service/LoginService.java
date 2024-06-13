package com.example.login.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.login.DTO.LoginDTO;
import com.example.login.DTO.StatusDTO;
import com.example.login.Model.LoginModel;
import com.example.login.Repository.LoginRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class LoginService {
    @Autowired
    LoginRepository loginRepository;
    @Autowired
    JwtService jwtService;

    public ResponseEntity<StatusDTO> CheckLogin(LoginDTO loginDTO){
        try {
            LoginModel loginModelCheck = loginRepository.findByUsernameAndPassword(loginDTO.getUsername(),loginDTO.getPassword());
            StatusDTO statusDTO = new StatusDTO();
            if (loginModelCheck == null) {
                statusDTO.setStatus(String.valueOf(HttpStatus.UNAUTHORIZED));
                statusDTO.setDescription("unauthorized");
                return new ResponseEntity<>(statusDTO,HttpStatus.UNAUTHORIZED);
            }else {
                statusDTO.setStatus(String.valueOf(HttpStatus.OK));
                String jwtToken = jwtService.generateAccessToken(loginModelCheck.getUsername());
                statusDTO.setDescription(jwtToken);
                return new ResponseEntity<>(statusDTO,HttpStatus.OK);
            }

        }catch (Exception e) {
            StatusDTO statusDTO = new StatusDTO();
            statusDTO.setStatus(String.valueOf(HttpStatus.BAD_REQUEST));
            statusDTO.setDescription(e.getMessage());
            return new ResponseEntity<>(statusDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public List<LoginModel> GetAllUser() {
        List<LoginModel> loginModels = loginRepository.findAll();
        return loginModels;
    }

    public ResponseEntity<StatusDTO> CheckAuth(DecodedJWT token) {
        try {
            StatusDTO statusDTO = new StatusDTO();
            String username = token.getSubject();

            statusDTO.setStatus(String.valueOf(HttpStatus.OK));
            statusDTO.setDescription("Login successful user " + username);
            return new ResponseEntity<>(statusDTO, HttpStatus.OK);

        }catch (Exception e){
            StatusDTO statusDTO = new StatusDTO();
            statusDTO.setStatus(String.valueOf(HttpStatus.BAD_REQUEST));
            statusDTO.setDescription(e.getMessage());
            return new ResponseEntity<>(statusDTO  ,HttpStatus.BAD_REQUEST);

        }

    }
}
