package backend.service;

import entity.Account;
import entity.Department;
import entity.Gender;
import entity.Position;

import java.util.List;

public interface IQLNSService {
    List<Account> getAccounts();
    List<Department> getDepartments();
    List<Position> getPositions();

    boolean xoaTheoAccId(int accountId);
    boolean suaTheoAccId(int accountId, String userName, int departmentId, int positionId);
    boolean themAccount(Account account);

    boolean checkEmailExist(String email);
    boolean checkUserNameExist(String userName);

    boolean checkAccountIdExist(int accountId);
}
