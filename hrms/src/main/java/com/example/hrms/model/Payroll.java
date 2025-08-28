package com.example.hrms.model;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double basicSalary;
    private Double bonus;
    private Double deductions;
    private Double totalSalary;
    private LocalDate salaryDate;

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

	public Double getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(Double basicSalary) {
		this.basicSalary = basicSalary;
	}

	public Double getBonus() {
		return bonus;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}

	public Double getDeductions() {
		return deductions;
	}

	public void setDeductions(Double deductions) {
		this.deductions = deductions;
	}

	public Double getTotalSalary() {
		return totalSalary;
	}

	public void setTotalSalary(Double totalSalary) {
		this.totalSalary = totalSalary;
	}

	public LocalDate getSalaryDate() {
		return salaryDate;
	}

	public void setSalaryDate(LocalDate salaryDate) {
		this.salaryDate = salaryDate;
	}
}
