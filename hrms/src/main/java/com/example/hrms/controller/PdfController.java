package com.example.hrms.controller;

import com.example.hrms.dto.EmployeeDTO;
import com.example.hrms.dto.LeaveDTO;
import com.example.hrms.dto.PayrollDTO;
import com.example.hrms.serviceImpl.EmployeeServiceImpl;
import com.example.hrms.serviceImpl.LeaveServiceImpl;
import com.example.hrms.serviceImpl.PayrollServiceImpl;
import com.example.hrms.serviceImpl.PdfService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class PdfController {

	@Autowired
    private PdfService pdfService;
	@Autowired
    private EmployeeServiceImpl employeeService;
	@Autowired
    private PayrollServiceImpl payrollService;
	@Autowired
    private LeaveServiceImpl leaveService;

    // ================= Employee Report =================
    @GetMapping("/employees")
    public ResponseEntity<byte[]> downloadEmployeeReport() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        
        ByteArrayInputStream bis = pdfService.generateEmployeeReport(employees);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=employees.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(bis.readAllBytes());
    }

    // ================= Payroll Report =================
    @GetMapping("/payrolls")
    public ResponseEntity<byte[]> downloadPayrollReport() {
        List<PayrollDTO> payrolls = payrollService.getPayrolls();
        ByteArrayInputStream bis = pdfService.generatePayrollPdf(payrolls);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=payrolls.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(bis.readAllBytes());
    }

    // ================= Leaves Report =================
    @GetMapping("/leaves")
    public ResponseEntity<byte[]> downloadLeavesReport() {
        List<LeaveDTO> leaves = leaveService.getAllLeaves();
        ByteArrayInputStream bis = pdfService.generateLeavesPdf(leaves);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=leaves.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(bis.readAllBytes());
    }
}
