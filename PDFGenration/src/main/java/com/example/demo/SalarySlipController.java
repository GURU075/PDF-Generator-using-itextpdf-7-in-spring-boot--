package com.example.demo;

	

	import java.io.ByteArrayInputStream;
	import java.io.IOException;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.core.io.InputStreamResource;
	import org.springframework.http.ResponseEntity;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.util.LinkedMultiValueMap;
	import org.springframework.util.MultiValueMap;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.http.HttpHeaders;
	import org.springframework.http.MediaType;
//
//	import com.HRMS.model.EmployeeMaster;
//	import com.HRMS.service.EmpAllowanceService;
//	import com.HRMS.service.EmpDeductionService;
//	import com.HRMS.service.EmployeeService;
//	import com.HRMS.service.SalarySlipService;

	import jakarta.persistence.EntityNotFoundException;

	@Controller
	public class SalarySlipController {
		
		@Autowired
		private EmpAllowanceService empAllowanceService;
		
		@Autowired
		private EmpDeductionService empDeductionService;
		
		@Autowired
		private EmployeeService employeeService;
		
		@Autowired
		private SalarySlipService salarySlipService;

		@GetMapping("/generatePDF")
		public String showEmployeeIdForm() {
			return "SalarySlip/SalarySlip1";

		}
		  
		  @GetMapping("/generate/{empId}")
		    public ResponseEntity<InputStreamResource> generateSalarySlip(@PathVariable("empId") Long empId, Model model) throws IOException {
			        ByteArrayInputStream bis = salarySlipService.generateSalarySlip(empId);

				        HttpHeaders headers = new HttpHeaders();
			        headers.add("Content-Disposition", "inline; filename=employee.pdf");

			        return ResponseEntity
			                .ok()
			                .headers(headers)
			                .contentType(MediaType.APPLICATION_PDF)
			                .body(new InputStreamResource(bis));
	        
			       
			    }
		  
		  
		

	}

}
