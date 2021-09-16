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
    public List<Employee> show() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> showOne(@PathVariable("id") Integer id){
        Employee employee = null;
        Map<String, Object> response = new HashMap<>();

        try {
            employee = this.employeeService.findById(id);

            if (employee == null){
                response.put("message", "El empleado con ID: " + id.toString() + " no existe en la Base de datos.");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<Employee>(employee, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("message", "Error al realizar la consulta");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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











