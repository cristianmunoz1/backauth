package com.Backauth.Backauth.core.dominio;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String userId;

    private String userIdTipe;

    private String userName;

    private String userLastname;

    private String userPhoneNumber;

    private String userEmail;

    private String userPassword;

    private Integer roleId;
}
