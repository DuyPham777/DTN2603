package com.example.buoi1.dto;

import com.example.buoi1.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private Integer id;
    private String username;
    private String fullname;
    private String email;
    private String departmentName;

//    public AccountDTO(Account account){
//        this.id = account.getId();
//        this.username = account.getUsername();
//        this.fullname = account.getFullName();
//        this.email = account.getEmail();
//        String depName = Objects.nonNull(account.getDepartment()) ? account.getDepartment().getDepartmentName() : "";
//        this.departmentName = depName;
//    }
}
