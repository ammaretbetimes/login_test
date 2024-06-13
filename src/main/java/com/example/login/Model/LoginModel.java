package com.example.login.Model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

import java.time.LocalDateTime;

@Entity
@Table(name = "slm_mdm_users_info")
public class LoginModel {
    @Id
    private Long userId;

    @Column(name = "username",nullable = false, length = 50,unique = true)
    private String username;

    @Column(name = "password",nullable = false, length = 50)
    private String password;

    @Column(name = "forget_password",length = 50)
    private String forgotPassword;

    @Column(name = "display_name",length = 150)
    private String displayName;

    @Column(name = "user_role",length = 50)
    private String userRole;

    @Column(name = "credit_no")
    private Float creditNo;

    @Column(name = "record_status",length = 1)
    private Character recordStatus;

    @Column(name = "use_status",length = 1)
    private Character useStatus;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "created_by",length = 50)
    private String createdBy;

    @Column(name = "updated_time")
    private LocalDateTime updatedTime;

    @Column(name = "updated_by",length = 50)
    private String updatedBy;

    @Column(name = "last_failed_login_at")
    private LocalDateTime lastFailedLoginAt;

    @Column(name = "unblocked_at")
    private LocalDateTime unblockedAt;

    @Column(name = "failed_login_attempts",nullable = false)
    private Short failedLoginAttempts;

    @Column(name = "accepted_terms",nullable = false)
    private Short acceptedTerms;

    public LoginModel() {
    }

    public LoginModel(Long userId, String username, String password, String forgotPassword, String displayName, String userRole, Float creditNo, Character recordStatus, Character useStatus, LocalDateTime createdTime, String createdBy, LocalDateTime updatedTime, String updatedBy, LocalDateTime lastFailedLoginAt, LocalDateTime unblockedAt, Short failedLoginAttempts, Short acceptedTerms) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.forgotPassword = forgotPassword;
        this.displayName = displayName;
        this.userRole = userRole;
        this.creditNo = creditNo;
        this.recordStatus = recordStatus;
        this.useStatus = useStatus;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
        this.updatedTime = updatedTime;
        this.updatedBy = updatedBy;
        this.lastFailedLoginAt = lastFailedLoginAt;
        this.unblockedAt = unblockedAt;
        this.failedLoginAttempts = failedLoginAttempts;
        this.acceptedTerms = acceptedTerms;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getForgotPassword() {
        return forgotPassword;
    }

    public void setForgotPassword(String forgotPassword) {
        this.forgotPassword = forgotPassword;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Float getCreditNo() {
        return creditNo;
    }

    public void setCreditNo(Float creditNo) {
        this.creditNo = creditNo;
    }

    public Character getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(Character recordStatus) {
        this.recordStatus = recordStatus;
    }

    public Character getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Character useStatus) {
        this.useStatus = useStatus;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getLastFailedLoginAt() {
        return lastFailedLoginAt;
    }

    public void setLastFailedLoginAt(LocalDateTime lastFailedLoginAt) {
        this.lastFailedLoginAt = lastFailedLoginAt;
    }

    public LocalDateTime getUnblockedAt() {
        return unblockedAt;
    }

    public void setUnblockedAt(LocalDateTime unblockedAt) {
        this.unblockedAt = unblockedAt;
    }

    public Short getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(Short failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }

    public Short getAcceptedTerms() {
        return acceptedTerms;
    }

    public void setAcceptedTerms(Short acceptedTerms) {
        this.acceptedTerms = acceptedTerms;
    }
}
