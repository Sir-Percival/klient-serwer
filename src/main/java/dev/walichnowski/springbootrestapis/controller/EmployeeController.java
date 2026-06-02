package dev.walichnowski.springbootrestapis.controller;

import dev.walichnowski.springbootrestapis.entity.Employee;
import dev.walichnowski.springbootrestapis.request.EmployeeRequest;
import dev.walichnowski.springbootrestapis.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Employees controller", description = "Operations related to employees")
@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController
{
    private final EmployeeService service;

    @GetMapping
    @Operation(summary = "Get all employees", description = "Retrieve a list of all employees")
    public List<Employee> findAll()
    {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Fetch single employee by Id")
    public Employee getEmployeeById(@PathVariable @Min(1) long id)
    {
        return service.findById(id);
    }

    @GetMapping("/age")
    @Operation(summary = "Get all employees ordered by age desc")
    public List<Employee> getAllByAgeDesc()
    {
        return service.findAllOrderByAgeDesc();
    }

    @GetMapping("/age/{age}")
    @Operation(summary = "Get all employees older than given age")
    public List<Employee> getAllOlderThan(@Min(18) @PathVariable int age)
    {
        return service.findAllOlderThan(age);
    }

    @Operation(summary = "Create a new employee")
    @PostMapping
    public Employee addEmployee(@Valid @RequestBody EmployeeRequest request)
    {
        return service.save(request);
    }

    @Operation(summary = "Update an employee")
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable @Min(1) long id, @Valid @RequestBody EmployeeRequest request)
    {
        return service.update(id, request);
    }

    @Operation(summary = "Delete an employee")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable @Min(1) long id)
    {
        service.deleteById(id);
    }
}
