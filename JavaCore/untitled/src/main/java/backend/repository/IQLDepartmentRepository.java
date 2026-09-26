package backend.repository;

import entity.Department;

import java.util.List;

public interface IQLDepartmentRepository {
    List<Department> getDepartments();
    boolean suaDepartmentNameTheoId(Integer departmentId,String departmentName);
    boolean xoaDepartmentTheoId(Integer departmentId);
    boolean themDepartment(String departmentName);
    boolean isDepartmentExist(int id);
}
