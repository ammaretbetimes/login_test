package com.example.login.Repository;

import com.example.login.Model.LoginModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<LoginModel,Long> {
    LoginModel findByUsernameAndPassword(String username, String password);

    Optional<LoginModel> findByUsername(String subject);
}
