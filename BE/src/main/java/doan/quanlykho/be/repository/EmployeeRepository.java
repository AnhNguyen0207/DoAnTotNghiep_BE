package doan.quanlykho.be.repository;

import doan.quanlykho.be.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


}
