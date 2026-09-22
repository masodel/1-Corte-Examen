package ni.edu.uam._corteexamen.controller;

import ni.edu.uam._corteexamen.model.Employee;
import ni.edu.uam._corteexamen.service.EmployeeService;
import ni.edu.uam._corteexamen.util.NavigationUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class EmployeeFormController implements Initializable {

    @FXML private TextField txtFirstName;
    @FXML private TextField txtLastName;
    @FXML private ComboBox<String> cmbPosition;
    @FXML private TextField txtSalary;

    private final EmployeeService employeeService = EmployeeService.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbPosition.setItems(FXCollections.observableArrayList(
                "Desarrollador Junior",
                "Desarrollador Senior",
                "Analista QA",
                "Diseñador UI/UX",
                "Gerente de Proyecto",
                "Administrador de Base de Datos"
        ));
    }

    @FXML
    private void handleSave() {
        String firstName = txtFirstName.getText().trim();
        String lastName = txtLastName.getText().trim();
        String position = cmbPosition.getValue();
        String salaryText = txtSalary.getText().trim();

        // 1. Validación de campos vacíos
        if (firstName.isEmpty() || lastName.isEmpty() || position == null || salaryText.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Campos Obligatorios", "Todos los campos con asterisco (*) son obligatorios.");
            return;
        }

        // 2. Validación de caracteres alfabéticos en nombres y apellidos
        if (!firstName.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
            showAlert(Alert.AlertType.ERROR, "Formato Incorrecto", "El campo 'Nombres' solo admite letras y espacios.");
            return;
        }

        if (!lastName.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
            showAlert(Alert.AlertType.ERROR, "Formato Incorrecto", "El campo 'Apellidos' solo admite letras y espacios.");
            return;
        }

        // 3. Validación numérica del salario
        double salary;
        try {
            salary = Double.parseDouble(salaryText);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Salario Inválido", "El salario debe ser un valor numérico válido (ej: 1250.50).");
            return;
        }

        // 4. Validación de rango de salario razonable
        if (salary <= 0) {
            showAlert(Alert.AlertType.ERROR, "Salario No Permitido", "El salario debe ser estrictamente mayor a 0.");
            return;
        }

        if (salary > 50000) {
            showAlert(Alert.AlertType.ERROR, "Salario Fuera de Rango", "El salario no puede superar los $50,000.00.");
            return;
        }

        // Creación del objeto usando el patrón Builder de Lombok
        String generatedId = "EMP-" + (100 + new Random().nextInt(900));
        Employee newEmployee = Employee.builder()
                .id(generatedId)
                .firstName(firstName)
                .lastName(lastName)
                .position(position)
                .salary(salary)
                .build();

        // Persistencia en memoria
        employeeService.addEmployee(newEmployee);

        // Alerta de éxito con navegación directa al listado
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Empleado registrado con éxito bajo el código " + generatedId, ButtonType.OK);
        alert.setTitle("Registro Exitoso");
        alert.setHeaderText(null);
        alert.showAndWait();

        // Redirige al listado de empleados para verificar el ingreso
        NavigationUtil.loadView("/ni/edu/uam/view/EmployeeListView.fxml");
    }

    @FXML
    private void handleClear() {
        txtFirstName.clear();
        txtLastName.clear();
        cmbPosition.getSelectionModel().clearSelection();
        txtSalary.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}