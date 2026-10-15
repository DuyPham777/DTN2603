package com.example.buoi1.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class GroupForm {
    private String groupName;
    private Integer creatorId;
}
