package com.example.hrms.dto;
import java.time.LocalDate;

public class PayrollDTO {
    private Long id;
    private Long employeeId;
    private Double basicSalary;
    private Double bonus;
    private Double deductions;
    private Double totalSalary;
    private LocalDate salaryDate;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public Double getBasicSalary() {
		return basicSalary;
	}
	public void setBasicSalary(Double basicSalary) {
		this.basicSalary = basicSalary;
	}
	public Double getDeductions() {
		return deductions;
	}
	public void setDeductions(Double deductions) {
		this.deductions = deductions;
	}
	public Double getBonus() {
		return bonus;
	}
	public void setBonus(Double bonus) {
		this.bonus = bonus;
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
