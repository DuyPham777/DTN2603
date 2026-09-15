package backend.repository;

import entity.Account;

import java.util.List;

public interface IQLNSRepository {
    List<Account> getAccounts();

    boolean xoaTheoAccId(int accountId);
    boolean suaTheoAccId(int accountId, String userName, int departmentId, int positionId);
    boolean themAccount(String email, String userName, String fullName, int departmentId, int positionId);
}
