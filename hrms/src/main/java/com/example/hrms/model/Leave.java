package com.example.hrms.model;
import jakarta.persistence.*;
import java.time.LocalDate;
import com.example.hrms.model.enums.LeaveStatus;

@Entity
@Table(name = "leavetable")
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;

    @Enumerated(EnumType.STRING)
    private LeaveStatus status;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    
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
    
    public LeaveStatus getStatus() {
    	return status;
    }
    
    public void setStatus(LeaveStatus status) {
    	this.status = status;
    }
    
	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

    
}
