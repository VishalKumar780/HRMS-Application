package com.example.hrms.model;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class PerformanceReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Employee employee;

    private LocalDate reviewDate;

    private int kpiScore; // out of 100

    private String remarks;

    private String reviewer; // HR/Admin username
    
    public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
    
    public Employee getEmployee() {
    	return employee;
    }
    
    public void setEmployee(Employee employee) {
    	this.employee = employee;
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
