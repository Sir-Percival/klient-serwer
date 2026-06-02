package dev.walichnowski.springbootrestapis.service;

import dev.walichnowski.springbootrestapis.entity.Employee;
import dev.walichnowski.springbootrestapis.exception.EmployeeNotFoundException;
import dev.walichnowski.springbootrestapis.repository.EmployeeRepository;
import dev.walichnowski.springbootrestapis.request.EmployeeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService
{
    private final EmployeeRepository repository;

    public List<Employee> findAll()
    {
        return repository.findAll();
    }

    public List<Employee> findAllOrderByAgeDesc()
    {
        return repository.findAllByOrderByAgeDesc();
    }

    public List<Employee> findAllOlderThan(int age)
    {
        return repository.findAllOlderThan(age);
    }

    public List<Employee> findByLastName(String lastName)
    {
        return repository.findByLastName(lastName);
    }

    public Employee findById(long id)
    {
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found #" + id));
    }

    public Employee save(EmployeeRequest request)
    {
        return repository.save(convertToEmployee(0, request));
    }

    public Employee update(long id, EmployeeRequest request)
    {
        return repository.save(convertToEmployee(id, request));
    }

    public void deleteById(long id)
    {
        repository.deleteById(id);
    }

    private Employee convertToEmployee(long id, EmployeeRequest request)
    {
        return new Employee(id, request.getFirstName(), request.getLastName(),
                request.getEmail(), request.getAge());
    }
}
