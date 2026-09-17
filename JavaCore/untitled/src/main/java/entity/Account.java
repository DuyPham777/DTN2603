package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Account {
    private int accountId;
    private String email;
    private String userName;
    private String fullName;
    private Gender gender;
    private Department department;
    private Position position;
    private LocalDate createDate;

}