package com.example.hrms.serviceImpl;
import com.example.hrms.dto.AttendanceDTO;
import com.example.hrms.model.Attendance;
import com.example.hrms.model.Employee;
import com.example.hrms.repository.AttendanceRepository;
import com.example.hrms.repository.EmployeeRepository;
import com.example.hrms.service.AttendanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public AttendanceDTO checkIn(Long employeeId) {
        LocalDate today = LocalDate.now();

        if (attendanceRepository.findByEmployeeIdAndDate(employeeId, today).isPresent()) {
            throw new RuntimeException("Already checked in today");
        }

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Attendance attendance = new Attendance();
                attendance.setEmployee(employee);
                attendance.setDate(today);
                attendance.setCheckInTime(LocalDateTime.now());
                

        return mapToDTO(attendanceRepository.save(attendance));
    }

    @Override
    public AttendanceDTO checkOut(Long employeeId) {
        LocalDate today = LocalDate.now();

        Attendance attendance = attendanceRepository.findByEmployeeIdAndDate(employeeId, today)
                .orElseThrow(() -> new RuntimeException("Check-in not found for today"));

        if (attendance.getCheckOutTime() != null) {
            throw new RuntimeException("Already checked out today");
        }

        attendance.setCheckOutTime(LocalDateTime.now());

        Duration duration = Duration.between(attendance.getCheckInTime(), attendance.getCheckOutTime());
        double hours = duration.toMinutes() / 60.0;
        attendance.setTotalHours(hours);

        return mapToDTO(attendanceRepository.save(attendance));
    }

    @Override
    public List<AttendanceDTO> getAttendanceByEmployee(Long employeeId) {
        return attendanceRepository.findByEmployeeId(employeeId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private AttendanceDTO mapToDTO(Attendance attendance) {
        AttendanceDTO dto = new AttendanceDTO();
        dto.setId(attendance.getId());
        dto.setDate(attendance.getDate());
        dto.setCheckInTime(attendance.getCheckInTime());
        dto.setCheckOutTime(attendance.getCheckOutTime());
        dto.setTotalHours(attendance.getTotalHours());
        if (attendance.getEmployee() != null) {
            dto.setEmployeeId(attendance.getEmployee().getId());
        } 
        else {
            throw new RuntimeException("Employee is null in Attendance entity while mapping to DTO");
        }
        return dto;
    }
}
