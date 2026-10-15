package com.example.buoi1.dto;

import com.example.buoi1.entity.Group;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO {
    private Integer groupId;
    private String groupName;
    private String creatorName;
    private LocalDate createDate;

    public GroupDTO(Group group) {
        this.groupId = group.getGroupId();
        this.groupName = group.getGroupName();
        this.creatorName = Objects.nonNull(group.getCreatorId()) ? group.getCreatorId().getFullName() : "";
        this.createDate = group.getCreateDate();
    }
}
