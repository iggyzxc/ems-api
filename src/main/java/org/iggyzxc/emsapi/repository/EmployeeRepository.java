package org.iggyzxc.emsapi.repository;

import org.iggyzxc.emsapi.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}