package com.example.hrms.controller;

import com.example.hrms.dto.PerformanceReviewDTO;
import com.example.hrms.model.PerformanceReview;
import com.example.hrms.serviceImpl.PerformanceReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class PerformanceReviewController {

    @Autowired
    private PerformanceReviewService reviewService;

    @PostMapping
    public PerformanceReview addReview(@RequestBody PerformanceReviewDTO dto) {
        return reviewService.addReview(dto);
    }

    @GetMapping
    public List<PerformanceReview> getAll() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/employee/{id}")
    public List<PerformanceReview> getByEmployee(@PathVariable Long id) {
        return reviewService.getReviewsByEmployee(id);
    }
}
