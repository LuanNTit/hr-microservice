package com.luan.employeeservice.mapper;

import com.luan.employeeservice.dto.EmployeeDTO;
import com.luan.employeeservice.model.EmployeeEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface EmployeeMapper {
    EmployeeDTO mapToEmployeeDTO(EmployeeEntity employeeEntity);
    EmployeeEntity mapToEmployeeEntity(EmployeeDTO employeeDTO);
    List<EmployeeDTO> mapToEmployeeDTOs(List<EmployeeEntity> employeeEntities);
    List<EmployeeEntity> mapToEmployeeEntities(List<EmployeeDTO> employeeDTOs);
}
