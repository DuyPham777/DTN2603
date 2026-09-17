package backend.service.impl;

import backend.repository.IQLNSRepository;
import backend.repository.impl.QLNSRepositoryImpl;
import backend.service.IQLNSService;
import entity.Account;
import entity.Department;
import entity.Position;

import java.util.List;

public class QLNSServiceImpl implements IQLNSService {
    private IQLNSRepository repository;

    public QLNSServiceImpl(){
        repository = new QLNSRepositoryImpl();
    }

    @Override
    public List<Account> getAccounts() {
        List<Account> accounts = repository.getAccounts();
        return accounts;
    }

    @Override
    public List<Department> getDepartments() {
        List<Department> departments = repository.getDepartments();
        return departments;
    }

    @Override
    public List<Position> getPositions() {
        List<Position> positions = repository.getPositions();
        return positions;
    }

    @Override
    public boolean xoaTheoAccId(int accountId) {
        return repository.xoaTheoAccId(accountId);
    }

    @Override
    public boolean suaTheoAccId(int accountId, String userName, int departmentId, int positionId) {
        return repository.suaTheoAccId(accountId, userName, departmentId, positionId);
    }

    @Override
    public boolean themAccount(Account account) {
        return repository.themAccount(account);
    }


    @Override
    public boolean checkEmailExist(String email) {
        return repository.checkEmailExist(email);
    }

    @Override
    public boolean checkUserNameExist(String userName) {
        return repository.checkUserNameExist(userName);
    }

    @Override
    public boolean checkAccountIdExist(int accountId) {
        return repository.checkAccountIdExist(accountId);
    }

}
