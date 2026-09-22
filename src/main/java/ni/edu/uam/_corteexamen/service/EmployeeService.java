package ni.edu.uam._corteexamen.service;

import ni.edu.uam._corteexamen.model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmployeeService {
    private static EmployeeService instance;
    private final ObservableList<Employee> employeeList;

    private EmployeeService() {
        this.employeeList = FXCollections.observableArrayList();
        // Datos semilla de demostración
        this.employeeList.add(new Employee("EMP-101", "Carlos", "Gómez", "Desarrollador Senior", 2800.00));
        this.employeeList.add(new Employee("EMP-102", "María", "López", "Analista QA", 1950.50));
    }

    public static synchronized EmployeeService getInstance() {
        if (instance == null) {
            instance = new EmployeeService();
        }
        return instance;
    }

    public ObservableList<Employee> getEmployees() {
        return employeeList;
    }

    public void addEmployee(Employee employee) {
        this.employeeList.add(employee);
    }
}