package com.example.hrms.serviceImpl;
import com.example.hrms.dto.LeaveDTO;
import com.example.hrms.model.Employee;
import com.example.hrms.model.Leave;
import com.example.hrms.model.enums.LeaveStatus;
import com.example.hrms.repository.EmployeeRepository;
import com.example.hrms.repository.LeaveRepository;
import com.example.hrms.service.LeaveService;
import com.example.hrms.util.EmailUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveServiceImpl implements LeaveService {

	@Autowired
    private LeaveRepository leaveRepository;
	@Autowired
    private EmployeeRepository employeeRepository;
	@Autowired
	private EmailUtil emailUtil;


    @Override
    public LeaveDTO applyLeave(LeaveDTO dto) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Leave leave = new Leave();
        leave.setStartDate(dto.getStartDate());
        leave.setEndDate(dto.getEndDate());
        leave.setReason(dto.getReason());
        leave.setStatus(LeaveStatus.PENDING);
        leave.setEmployee(employee);
                
        return mapToDTO(leaveRepository.save(leave));
    }

    @Override
    public List<LeaveDTO> getAllLeaves() {
        return leaveRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<LeaveDTO> getLeavesByEmployee(Long empId) {
        return leaveRepository.findByEmployeeId(empId)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public LeaveDTO approveLeave(Long leaveId) {
        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave not found"));
        leave.setStatus(LeaveStatus.APPROVED);
        String email = leave.getEmployee().getEmail();
        emailUtil.sendEmail(email, "Leave Approved", "Your leave has been approved.");
        return mapToDTO(leaveRepository.save(leave));
    }

    @Override
    public LeaveDTO rejectLeave(Long leaveId) {
        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave not found"));
        leave.setStatus(LeaveStatus.REJECTED);
        String email = leave.getEmployee().getEmail();
        emailUtil.sendEmail(email, "Leave Rejected", "Your leave has been rejected.");
        return mapToDTO(leaveRepository.save(leave));
    }

    private LeaveDTO mapToDTO(Leave leave) {
        LeaveDTO dto = new LeaveDTO();
        dto.setId(leave.getId());
        dto.setEmployeeId(leave.getEmployee().getId());
        dto.setStartDate(leave.getStartDate());
        dto.setEndDate(leave.getEndDate());
        dto.setReason(leave.getReason());
        dto.setStatus(leave.getStatus().name());
        return dto;
    }
}
