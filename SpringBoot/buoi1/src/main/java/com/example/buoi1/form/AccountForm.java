package com.example.buoi1.form;

import com.example.buoi1.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountForm {
    private String username;
    private String fullname;
    private String email;
    private String password;
    private Gender gender;
    private Integer departmentId;
    private Integer positionId;
}
