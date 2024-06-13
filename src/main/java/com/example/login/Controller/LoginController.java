package com.example.login.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.login.DTO.LoginDTO;
import com.example.login.DTO.StatusDTO;
import com.example.login.Model.LoginModel;
import com.example.login.Service.JwtService;
import com.example.login.Service.LoginService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<StatusDTO> CheckLogin(@RequestBody LoginDTO loginDTO){
        return loginService.CheckLogin(loginDTO);
    }


    @GetMapping("/auth")
    public ResponseEntity<StatusDTO> CheckAuth(@RequestHeader("Authorization") String authorizationHeader){
        DecodedJWT jwt = JWT.decode(authorizationHeader.substring(7));
            return loginService.CheckAuth(jwt);
    }

    @GetMapping("/get")
    public List<LoginModel> GetAllUser(){
        return loginService.GetAllUser();
    }

}
