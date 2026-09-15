package backend.service.impl;

import backend.repository.IQLNSRepository;
import backend.repository.impl.QLNSRepositoryImpl;
import backend.service.IQLNSService;
import entity.Account;

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
    public boolean xoaTheoAccId(int accountId) {
        return repository.xoaTheoAccId(accountId);
    }

    @Override
    public boolean suaTheoAccId(int accountId, String userName, int departmentId, int positionId) {
        return repository.suaTheoAccId(accountId, userName, departmentId, positionId);
    }

    @Override
    public boolean themAccount(String email, String userName, String fullName, int departmentId, int positionId) {
        return repository.themAccount(email, userName, fullName, departmentId, positionId);
    }
}
