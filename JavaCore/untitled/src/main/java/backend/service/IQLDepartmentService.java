package backend.service;

import entity.Department;

import java.util.List;

public interface IQLDepartmentService {
    List<Department> getDepartments();
    boolean suaDepartmentNameTheoId(Integer departmentId,String departmentName);
    boolean xoaDepartmentTheoId(Integer departmentId);
    boolean themDepartment(String departmentName);
    boolean isDepartmentExist(int id);
}
