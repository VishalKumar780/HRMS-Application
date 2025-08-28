package com.example.hrms.dto;
import java.time.LocalDate;


public class PerformanceReviewDTO {
    private Long employeeId;
    private LocalDate reviewDate;
    private int kpiScore;
    private String remarks;
    private String reviewer;
    
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public LocalDate getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(LocalDate reviewDate) {
		this.reviewDate = reviewDate;
	}
	public int getKpiScore() {
		return kpiScore;
	}
	public void setKpiScore(int kpiScore) {
		this.kpiScore = kpiScore;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getReviewer() {
		return reviewer;
	}
	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}
}
