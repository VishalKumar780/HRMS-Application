package com.example.hrms.serviceImpl;

import com.example.hrms.dto.PayrollDTO;
import com.example.hrms.model.Employee;
import com.example.hrms.model.Payroll;
import com.example.hrms.repository.EmployeeRepository;
import com.example.hrms.repository.PayrollRepository;
import com.example.hrms.service.PayrollService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PayrollServiceImpl implements PayrollService {

	@Autowired
    private PayrollRepository payrollRepository;
	@Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public PayrollDTO generatePayroll(PayrollDTO dto) {
        if (dto.getEmployeeId() == null) {
            throw new IllegalArgumentException("Employee ID must not be null");
        }

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        double total = dto.getBasicSalary() + dto.getBonus() - dto.getDeductions();

        Payroll payroll = new Payroll();
        payroll.setEmployee(employee);
        payroll.setBasicSalary(dto.getBasicSalary());
        payroll.setBonus(dto.getBonus());
        payroll.setDeductions(dto.getDeductions());
        payroll.setTotalSalary(total);
        payroll.setSalaryDate(dto.getSalaryDate());

        return mapToDTO(payrollRepository.save(payroll));
    }

    
    @Override
    public List<PayrollDTO> getPayrolls() {
        return payrollRepository.findAll().stream()
                .map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<PayrollDTO> getPayrollsByEmployee(Long employeeId) {
        return payrollRepository.findByEmployeeId(employeeId).stream()
                .map(this::mapToDTO).collect(Collectors.toList());
    }

    private PayrollDTO mapToDTO(Payroll payroll) {
        PayrollDTO dto = new PayrollDTO();
        dto.setId(payroll.getId());
        dto.setEmployeeId(payroll.getEmployee().getId());
        dto.setBasicSalary(payroll.getBasicSalary());
        dto.setBonus(payroll.getBonus());
        dto.setDeductions(payroll.getDeductions());
        dto.setTotalSalary(payroll.getTotalSalary());
        dto.setSalaryDate(payroll.getSalaryDate());
        return dto;
    }
}
