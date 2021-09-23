package upeu.edu.pe.employees.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import upeu.edu.pe.employees.entity.Employee;
import upeu.edu.pe.employees.service.EmployeeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public ResponseEntity<?> show() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Employee> employees = employeeService.findAll();
            response.put("message", "success");
            response.put("error", "false");
            response.put("body", employees);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("message", "Error al realizar la consulta");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> showOne(@PathVariable("id") Integer id){
        Map<String, Object> response = new HashMap<>();
        try {
            Employee employee = this.employeeService.findById(id);

            if (employee == null){
                response.put("message", "El empleado con ID: " + id.toString() + " no existe en la Base de datos.");
                response.put("error", "true");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }

            response.put("message", "success");
            response.put("error", "false");
            response.put("body", employee);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } catch (DataAccessException e){
            response.put("message", "Error al realizar la consulta");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?>  createEmployee(@RequestBody Employee employee){
        Map<String, Object> response = new HashMap<>();
        try {

            if (employee == null){
                response.put("message", "more content");
                response.put("error", "true");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
            }

            employeeService.createOrUpdate(employee);
            response.put("message", "created user correct");
            response.put("error", "false");
            response.put("body", employee);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

        }catch (DataAccessException e){
            response.put("message", "Error al realizar la consulta");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee update(@RequestBody Employee employee, @PathVariable Integer id) {
        Employee employeeCurrent = this.employeeService.findById(id);
        employeeCurrent.setFirstName(employee.getFirstName());
        employeeCurrent.setLastName(employee.getLastName());
        this.employeeService.createOrUpdate(employeeCurrent);
        return employeeCurrent;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>  deleteEmployee(@PathVariable("id") Integer id){
        Map<String, Object> response = new HashMap<>();
        try {
            Employee employee = this.employeeService.findById(id);

            if (employee == null){
                response.put("message", "El empleado con ID: " + id.toString() + " no existe en la Base de datos.");
                response.put("error", "true");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }

            employeeService.delete(id);
            response.put("message", "user delete success");
            response.put("error", "false");
            response.put("body", employee);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("message", "Error al realizar la consulta");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}











