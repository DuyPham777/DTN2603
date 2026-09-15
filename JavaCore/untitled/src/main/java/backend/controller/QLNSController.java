package backend.controller;

import backend.service.IQLNSService;
import backend.service.impl.QLNSServiceImpl;
import entity.Account;

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

    public boolean xoaTheoAccId(int accountId) {
        return service.xoaTheoAccId(accountId);
    }

    public boolean suaTheoAccId(int accountId, String userName, int departmentId, int positionId) {
        return service.suaTheoAccId(accountId, userName, departmentId, positionId);
    }

    public boolean themAccount(String email, String userName, String fullName, int departmentId, int positionId) {
        return service.themAccount(email, userName, fullName, departmentId, positionId);
    }
}
