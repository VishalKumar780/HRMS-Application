package com.example.hrms.serviceImpl;

import com.example.hrms.dto.DepartmentDTO;
import com.example.hrms.dto.EmployeeDTO;
import com.example.hrms.dto.LeaveDTO;
import com.example.hrms.dto.PayrollDTO;
import com.example.hrms.service.DepartmentService;
import com.example.hrms.service.EmployeeService;
import com.example.hrms.service.LeaveService;
import com.example.hrms.service.PayrollService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfService {
	
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private PayrollService payrollService;
	@Autowired
	private LeaveService leaveService;
	@Autowired
	private DepartmentService departmentService;
	
	public ByteArrayInputStream generateEmployeeReport(List<EmployeeDTO> employees) {
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // Title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("Employee Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            // Table with columns
            PdfPTable table = new PdfPTable(7); // number of columns
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Column widths
            float[] columnWidths = {1f, 2f, 3f, 2f, 2f, 2f,2f};
            table.setWidths(columnWidths);

            // Table header
            addTableHeader(table, "ID");
            addTableHeader(table, "Full Name");
            addTableHeader(table, "Email");
            addTableHeader(table, "Phone");
            addTableHeader(table, "Department");
            addTableHeader(table, "Designation");
            addTableHeader(table, "Salary");

            // Table rows
            for (EmployeeDTO emp : employees) {
                table.addCell(emp.getDepartmentId().toString());
                table.addCell(emp.getFullName());
                table.addCell(emp.getEmail());
                table.addCell(emp.getPhone());
                table.addCell(emp.getDepartmentId().toString());
                table.addCell(emp.getDesignation());
                table.addCell(emp.getSalary().toString());
            }

            document.add(table);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
	
	
    // ===== Payroll PDF Table =====
    public ByteArrayInputStream generatePayrollPdf(List<PayrollDTO> payrolls) {
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);

            Paragraph title = new Paragraph("Payroll Report", headFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(6); // 6 columns
            table.setWidthPercentage(100);
            table.setWidths(new int[]{4, 4, 3, 3, 4, 4});

            // Table headers
            addTableHeader(table, "Employee ID");
            addTableHeader(table, "Basic Salary");
            addTableHeader(table, "Bonus");
            addTableHeader(table, "Deductions");
            addTableHeader(table, "Total Salary");
            addTableHeader(table, "Salary Date");

            // Table rows
            for (PayrollDTO payroll : payrolls) {
                table.addCell(payroll.getEmployeeId().toString());
                table.addCell(String.valueOf(payroll.getBasicSalary()));
                table.addCell(String.valueOf(payroll.getBonus()));
                table.addCell(String.valueOf(payroll.getDeductions()));
                table.addCell(String.valueOf(payroll.getTotalSalary()));
                table.addCell(String.valueOf(payroll.getSalaryDate()));
            }

            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    // ===== Leaves PDF Table =====
    public ByteArrayInputStream generateLeavesPdf(List<LeaveDTO> leaves) {
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);

            Paragraph title = new Paragraph("Leaves Report", headFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(5); // 5 columns
            table.setWidthPercentage(100);
            table.setWidths(new int[]{4, 4, 4, 4, 4});

            // Table headers
            addTableHeader(table, "Employee ID");
            addTableHeader(table, "Start Date");
            addTableHeader(table, "End Date");
            addTableHeader(table, "Leave Type");
            addTableHeader(table, "Status");

            // Table rows
            for (LeaveDTO leave : leaves) {
                table.addCell(leave.getEmployeeId().toString());
                table.addCell(String.valueOf(leave.getStartDate()));
                table.addCell(String.valueOf(leave.getEndDate()));
                table.addCell(leave.getReason());
                table.addCell(leave.getStatus().toString());
            }

            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    // ===== Helper: Table Header =====
    private void addTableHeader(PdfPTable table, String headerTitle) {
        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        PdfPCell hcell = new PdfPCell(new Phrase(headerTitle, headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        hcell.setBackgroundColor(Color.LIGHT_GRAY);
        table.addCell(hcell);
    }
}
