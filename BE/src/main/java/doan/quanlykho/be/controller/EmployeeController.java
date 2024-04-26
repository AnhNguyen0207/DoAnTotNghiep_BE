package doan.quanlykho.be.controller;

import doan.quanlykho.be.entity.Employee;
import doan.quanlykho.be.service.IEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@CrossOrigin("*")
@PreAuthorize("hasAnyAuthority('admin')")
@AllArgsConstructor
public class EmployeeController {
    private final IEmployeeService employeeService;
    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.updateEmployeeById(employee));
    }
}
