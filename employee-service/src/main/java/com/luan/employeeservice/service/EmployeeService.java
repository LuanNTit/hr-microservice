package com.luan.employeeservice.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Page;

import com.luan.employeeservice.dto.EmployeeDTO;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
	List<EmployeeDTO> getAllEmployees();
	EmployeeDTO saveEmployee(EmployeeDTO employee);
	EmployeeDTO updateEmployee(Long id, EmployeeDTO employee);
	EmployeeDTO getEmployeeById(long id);
	void deleteEmployeeById(long id);
	List<EmployeeDTO> searchEmployee(String name);
	Page<EmployeeDTO> getAllEmployees(int page, int size, String sortField, String sortDirection, int minAge, int maxAge, String startDate, String endDate) throws ParseException;
}
