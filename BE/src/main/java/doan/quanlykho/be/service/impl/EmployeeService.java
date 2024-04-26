package doan.quanlykho.be.service.impl;

import doan.quanlykho.be.entity.Employee;
import doan.quanlykho.be.repository.EmployeeRepository;
import doan.quanlykho.be.service.IEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService implements IEmployeeService {

    private final EmployeeRepository employeeRepository;
    @Override
    public Employee updateEmployeeById(Employee employee) {
        Employee employeeUpdate = employeeRepository.findById(employee.getId()).get();
        if (employeeUpdate != null) {
            employeeUpdate.setFullName(employee.getFullName());
            employeeUpdate.setImage(employee.getImage());
            employeeUpdate.setEmail(employee.getEmail());
            employeeUpdate.setPhone(employee.getPhone());
            employeeUpdate.setAddress(employee.getAddress());
        }
        return employeeRepository.save(employeeUpdate);
    }
}
