package upeu.edu.pe.employees.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import upeu.edu.pe.employees.entity.Employee;
import upeu.edu.pe.employees.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public List<Employee> show() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee showOne(@PathVariable("id") Integer id){
        return employeeService.findById(id);
    }

    @PostMapping("/")
    public Integer createEmployee(@RequestBody Employee employee){
        employeeService.createOrUpdate(employee);
        return employee.getIdEmployees();
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeService.delete(id);
        return "Eliminado correctamente";
    }
}











