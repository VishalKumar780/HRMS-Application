package com.example.hrms.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hrms.dto.PerformanceReviewDTO;
import com.example.hrms.model.Employee;
import com.example.hrms.model.PerformanceReview;
import com.example.hrms.repository.EmployeeRepository;
import com.example.hrms.repository.PerformanceReviewRepository;

import java.util.List;

@Service
public class PerformanceReviewService {

    @Autowired
    private PerformanceReviewRepository reviewRepo;

    @Autowired
    private EmployeeRepository employeeRepo;

    public PerformanceReview addReview(PerformanceReviewDTO dto) {
        Employee employee = employeeRepo.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        PerformanceReview review = new PerformanceReview();
        review.setEmployee(employee);
        review.setReviewDate(dto.getReviewDate());
        review.setKpiScore(dto.getKpiScore());
        review.setRemarks(dto.getRemarks());
        review.setReviewer(dto.getReviewer());

        return reviewRepo.save(review);
    }

    public List<PerformanceReview> getAllReviews() {
        return reviewRepo.findAll();
    }

    public List<PerformanceReview> getReviewsByEmployee(Long employeeId) {
        return reviewRepo.findByEmployeeId(employeeId);
    }
}

