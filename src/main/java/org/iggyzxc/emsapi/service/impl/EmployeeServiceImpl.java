package org.iggyzxc.emsapi.service.impl;

import lombok.AllArgsConstructor;
import org.iggyzxc.emsapi.dto.EmployeeDto;
import org.iggyzxc.emsapi.entity.Employee;
import org.iggyzxc.emsapi.exception.ResourceNotFoundException;
import org.iggyzxc.emsapi.mapper.EmployeeMapper;
import org.iggyzxc.emsapi.repository.EmployeeRepository;
import org.iggyzxc.emsapi.service.EmployeeService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.MAPPER.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.MAPPER.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(()->
                new ResourceNotFoundException("Employee with id " + id + " does not exist"));
        return EmployeeMapper.MAPPER.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
       List<Employee> employeeList = employeeRepository.findAll();
       return employeeList.stream()
               .map(EmployeeMapper.MAPPER::mapToEmployeeDto)
               .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Employee with id " + id + " does not exist"));

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());

        Employee updatedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.MAPPER.mapToEmployeeDto(updatedEmployee);

    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Employee with id " + id + " does not exist"));

        employeeRepository.delete(employee);
    }
}
