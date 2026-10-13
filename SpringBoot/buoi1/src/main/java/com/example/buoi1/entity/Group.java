package com.example.buoi1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "`group`")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Group {

    @Id
    @Column(name = "group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer groupId;

    @Column(name = "group_name", nullable = false, length = 100)
    private String groupName;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Account creatorId;

    @Column(name = "create_date", updatable = false)
    private LocalDate createDate;

    @PrePersist
    public void prePersist() {
        if (createDate == null) {
            createDate = LocalDate.now();
        }
    }
}
