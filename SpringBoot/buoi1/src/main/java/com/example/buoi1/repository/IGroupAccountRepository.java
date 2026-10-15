package com.example.buoi1.repository;

import com.example.buoi1.entity.GroupAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IGroupAccountRepository extends JpaRepository<GroupAccount, GroupAccount.Key> {

    // các group mà account đang tham gia
    List<GroupAccount> findByAccount_Id(Integer accountId);

    // các account đang có trong group
    List<GroupAccount> findByGroup_GroupId(Integer groupId);
}
