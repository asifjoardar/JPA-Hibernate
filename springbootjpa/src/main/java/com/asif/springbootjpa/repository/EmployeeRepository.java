package com.asif.springbootjpa.repository;

import com.asif.springbootjpa.models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    // fetching an emp
    // persist
    // updating(e)
    // find all emps
}
