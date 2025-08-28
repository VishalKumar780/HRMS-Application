package com.example.hrms.controller;

import com.example.hrms.dto.LeaveDTO;
import com.example.hrms.service.LeaveService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

	@Autowired
    private LeaveService leaveService;

    @PostMapping
    public ResponseEntity<LeaveDTO> applyLeave(@RequestBody LeaveDTO dto) {
        return ResponseEntity.ok(leaveService.applyLeave(dto));
    }

    @GetMapping
    public ResponseEntity<List<LeaveDTO>> getAllLeaves() {
        return ResponseEntity.ok(leaveService.getAllLeaves());
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<LeaveDTO>> getLeavesByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(leaveService.getLeavesByEmployee(employeeId));
    }

    @PutMapping("/{leaveId}/approve")
    public ResponseEntity<LeaveDTO> approveLeave(@PathVariable Long leaveId) {
        return ResponseEntity.ok(leaveService.approveLeave(leaveId));
    }

    @PutMapping("/{leaveId}/reject")
    public ResponseEntity<LeaveDTO> rejectLeave(@PathVariable Long leaveId) {
        return ResponseEntity.ok(leaveService.rejectLeave(leaveId));
    }
}

