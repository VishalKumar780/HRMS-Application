package com.example.hrms.controller;

import com.example.hrms.dto.PayrollDTO;
import com.example.hrms.service.PayrollService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payrolls")
public class PayrollController {

	@Autowired
    private PayrollService payrollService;

    @PostMapping
    public ResponseEntity<PayrollDTO> generatePayroll(@RequestBody PayrollDTO dto) {
        return ResponseEntity.ok(payrollService.generatePayroll(dto));
    }

    @GetMapping
    public ResponseEntity<List<PayrollDTO>> getAllPayrolls() {
        return ResponseEntity.ok(payrollService.getPayrolls());
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<PayrollDTO>> getPayrollsByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(payrollService.getPayrollsByEmployee(employeeId));
    }
}
