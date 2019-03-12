package model;

import model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDAO {
    Optional findOne(Integer id);
    List findAll();
    Optional findByName(String name);
    void delete(Employee employee);
    void save(Employee employee);

}
