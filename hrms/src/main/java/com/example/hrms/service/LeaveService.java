package com.example.hrms.service;
import com.example.hrms.dto.LeaveDTO;
import java.util.List;

public interface LeaveService {
    LeaveDTO applyLeave(LeaveDTO leaveDTO);
    List<LeaveDTO> getAllLeaves();
    List<LeaveDTO> getLeavesByEmployee(Long employeeId);
    LeaveDTO approveLeave(Long leaveId);
    LeaveDTO rejectLeave(Long leaveId);
}

