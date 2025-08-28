package com.example.hrms.service;
import com.example.hrms.dto.AttendanceDTO;
import java.util.List;

public interface AttendanceService {
    AttendanceDTO checkIn(Long employeeId);
    AttendanceDTO checkOut(Long employeeId);
    List<AttendanceDTO> getAttendanceByEmployee(Long employeeId);
}
