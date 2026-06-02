package dev.walichnowski.springbootrestapis.repository;

import dev.walichnowski.springbootrestapis.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>
{
    List<Employee> findByLastName(String lastName);
    List<Employee> findAllByOrderByAgeDesc();

    @NativeQuery("SELECT * FROM employees WHERE age > ?1")
    List<Employee> findAllOlderThan(int age);
}