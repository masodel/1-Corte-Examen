package ni.edu.uam._corteexamen.Controller;

import ni.edu.uam._corteexamen.model.Employee;
import ni.edu.uam._corteexamen.service.EmployeeService;
import ni.edu.uam._corteexamen.util.NavigationUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeListController implements Initializable {

    @FXML private TableView<Employee> tblEmployees;
    @FXML private TableColumn<Employee, String> colId;
    @FXML private TableColumn<Employee, String> colFirstName;
    @FXML private TableColumn<Employee, String> colLastName;
    @FXML private TableColumn<Employee, String> colPosition;
    @FXML private TableColumn<Employee, Double> colSalary;
    @FXML private TextField txtSearch;
    @FXML private Label lblTotalRecords;

    private final EmployeeService employeeService = EmployeeService.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTableColumns();
        setupFilterAndBinding();
    }

    private void setupTableColumns() {
        colId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        colFirstName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
        colLastName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
        colPosition.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPosition()));

        colSalary.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getSalary())
        );

        // Formato de moneda para el salario ($#,##0.00)
        colSalary.setCellFactory(col -> new TableCell<Employee, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("$ %,.2f", item));
                }
            }
        });
    }

    private void setupFilterAndBinding() {
        FilteredList<Employee> filteredData = new FilteredList<>(employeeService.getEmployees(), p -> true);

        // Filtro dinámico en tiempo real
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(employee -> {
                if (newValue == null || newValue.trim().isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase().trim();

                return employee.getFirstName().toLowerCase().contains(lowerCaseFilter)
                        || employee.getLastName().toLowerCase().contains(lowerCaseFilter)
                        || employee.getPosition().toLowerCase().contains(lowerCaseFilter)
                        || employee.getId().toLowerCase().contains(lowerCaseFilter);
            });
            updateTotalCount(filteredData.size());
        });

        tblEmployees.setItems(filteredData);
        updateTotalCount(filteredData.size());
    }

    private void updateTotalCount(int count) {
        lblTotalRecords.setText("Total: " + count + " registro(s)");
    }

    @FXML
    private void handleGoToForm() {
        NavigationUtil.loadView("/com/app/view/EmployeeFormView.fxml");
    }
}