package com.example.buoi1.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "groupaccount")   // tên bảng thật trong DB của bạn là groupaccount (không có dấu _)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupAccount {

    @EmbeddedId
    private Key id;

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class Key implements Serializable {
        @Column(name = "group_id")
        private Integer groupId;

        @Column(name = "account_id")
        private Integer accountId;
    }

    @ManyToOne
    @MapsId("groupId")
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "join_date", updatable = false)
    private LocalDate joinDate;

    @PrePersist
    public void prePersist() {
        if (joinDate == null) {
            joinDate = LocalDate.now();
        }
    }
}
