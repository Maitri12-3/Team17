package com.db.api.dto;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO
{
    private Long id;
    @NotEmpty
    private String name;

    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;

    @NotEmpty(message = "Password should not be empty")
    private String password;
}


