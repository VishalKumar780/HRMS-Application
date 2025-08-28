package com.example.hrms.service;
import com.example.hrms.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO createEmployee(EmployeeDTO dto);
    EmployeeDTO updateEmployee(Long id, EmployeeDTO dto);
    EmployeeDTO getEmployee(Long id);
    List<EmployeeDTO> getAllEmployees();
    void deleteEmployee(Long id);
}
