package com.example.hrms.service;
import com.example.hrms.dto.PayrollDTO;
import java.util.List;

public interface PayrollService {
    PayrollDTO generatePayroll(PayrollDTO dto);
    List<PayrollDTO> getPayrolls();
    List<PayrollDTO> getPayrollsByEmployee(Long employeeId);
}
