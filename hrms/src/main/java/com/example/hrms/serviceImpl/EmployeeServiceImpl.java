package com.example.hrms.serviceImpl;

import com.example.hrms.dto.EmployeeDTO;
import com.example.hrms.model.Department;
import com.example.hrms.model.Employee;
import com.example.hrms.repository.DepartmentRepository;
import com.example.hrms.repository.EmployeeRepository;
import com.example.hrms.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;
    @Autowired
    private DepartmentRepository deptRepo;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO dto) {
    	Department dept = deptRepo.findById(dto.getDepartmentId()).orElseThrow();
        Employee emp = new Employee();
        emp.setFullName(dto.getFullName());
        emp.setEmail(dto.getEmail());
        emp.setPhone(dto.getPhone());
        emp.setSalary(dto.getSalary());
        emp.setDesignation(dto.getDesignation());
        emp.setDepartment(dept);

        Employee saved = employeeRepo.save(emp);
        return mapToDTO(saved);
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        Employee emp = employeeRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with ID: " + id));

        emp.setFullName(dto.getFullName());
        emp.setPhone(dto.getPhone());
        emp.setEmail(dto.getEmail());
        emp.setSalary(dto.getSalary());
        emp.setDesignation(dto.getDesignation());
        emp.setDepartment(deptRepo.findById(dto.getDepartmentId()).orElseThrow());

        return mapToDTO(employeeRepo.save(emp));
    }

    @Override
    public EmployeeDTO getEmployee(Long id) {
        Employee emp = employeeRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with ID: " + id));
        return mapToDTO(emp);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepo.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepo.deleteById(id);
    }

    private EmployeeDTO mapToDTO(Employee emp) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(emp.getId());
        dto.setFullName(emp.getFullName());
        dto.setEmail(emp.getEmail());
        dto.setPhone(emp.getPhone());
        dto.setSalary(emp.getSalary());
        dto.setDesignation(emp.getDesignation());
        dto.setDepartmentId(emp.getDepartment().getId());
        
        return dto;
    }
}
