package backend.controller;

import backend.service.IQLNSService;
import backend.service.impl.QLNSServiceImpl;
import entity.Account;
import entity.Department;
import entity.Gender;
import entity.Position;

import java.util.List;

public class QLNSController {
    private IQLNSService service;

    public QLNSController(){
        service = new QLNSServiceImpl();
    }

    public List<Account> getAccounts() {
        List<Account> accounts = service.getAccounts();
        return accounts;
    }

    public List<Department> getDepartments() {
        List<Department> departments = service.getDepartments();
        return departments;
    }

    public List<Position> getPositions() {
        List<Position> positions = service.getPositions();
        return positions;
    }

    public boolean xoaTheoAccId(int accountId) {
        return service.xoaTheoAccId(accountId);
    }

    public boolean suaTheoAccId(int accountId, String userName, int departmentId, int positionId) {
        return service.suaTheoAccId(accountId, userName, departmentId, positionId);
    }

    public boolean themAccount(Account account) {
        return service.themAccount(account);
    }

    public boolean checkEmailExist(String email) {
        return service.checkEmailExist(email);
    }

    public boolean checkUserNameExist(String userName) {
        return service.checkUserNameExist(userName);
    }

    public boolean checkAccountIdExist(int accountId) {
        return service.checkAccountIdExist(accountId);
    }
}
