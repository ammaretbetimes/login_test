package com.example.login.security;

import com.example.login.DTO.StatusDTO;
import com.example.login.Model.LoginModel;
import com.example.login.Repository.LoginRepository;
import com.example.login.Service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
            String token = authorizationHeader.substring(TOKEN_PREFIX.length());
            try {
                Claims claims = jwtService.decodeToken(token);

                if (claims != null) {
                    String subject = claims.getSubject();
                    Optional<LoginModel> user = loginRepository.findByUsername(subject);
                    if (user.isPresent()) {
                        request.setAttribute("username", user.get().getUsername());
                    }
                }
            } catch (ExpiredJwtException e) {
                handleTokenError(response, "Token Expired", HttpStatus.UNAUTHORIZED);
                return;
            } catch (JwtException e) {
                handleTokenError(response, "Invalid Token", HttpStatus.BAD_REQUEST);
                return;
            }
        }
//        else {
//            handleTokenError(response, "JWT Token Missing", HttpStatus.BAD_REQUEST);
//            return;
//        }

        filterChain.doFilter(request, response);
    }

    private void handleTokenError(HttpServletResponse response, String description ,HttpStatus status) throws IOException {
        response.setStatus(status.value());
        response.setContentType("application/json");

        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setStatus(String.valueOf(status.value()));
        statusDTO.setDescription(description);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(statusDTO);

        response.getWriter().write(json);
    }
}
