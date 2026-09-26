package backend.service.impl;

import backend.repository.IQLDepartmentRepository;
import backend.service.IQLDepartmentService;
import entity.Department;

import java.util.List;

public class QLDepartmentServiceImpl implements IQLDepartmentService {
    private IQLDepartmentRepository repository;
    public QLDepartmentServiceImpl() {
        repository = new backend.repository.impl.QLDepartmentRepositoryImpl();// khoi tao gia tri
    }
    @Override
    public List<Department> getDepartments() {
        List<Department> departments = repository.getDepartments();
        return departments;
    }

    @Override
    public boolean suaDepartmentNameTheoId(Integer departmentId, String departmentName) {
        return repository.suaDepartmentNameTheoId(departmentId, departmentName);
    }

    @Override
    public boolean xoaDepartmentTheoId(Integer departmentId) {
        return repository.xoaDepartmentTheoId(departmentId);
    }

    @Override
    public boolean themDepartment(String departmentName) {
        return repository.themDepartment(departmentName);
    }

    @Override
    public boolean isDepartmentExist(int id) {
        return repository.isDepartmentExist(id);
    }
}
