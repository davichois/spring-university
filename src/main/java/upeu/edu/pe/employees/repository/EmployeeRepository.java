package upeu.edu.pe.employees.repository;

import org.springframework.data.repository.CrudRepository;
import upeu.edu.pe.employees.entity.Employee;


public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
