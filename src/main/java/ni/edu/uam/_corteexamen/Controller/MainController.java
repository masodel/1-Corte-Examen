package ni.edu.uam._corteexamen.controller;

import ni.edu.uam._corteexamen.util.NavigationUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML private StackPane contentContainer;
    @FXML private Button btnNavForm;
    @FXML private Button btnNavList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NavigationUtil.setMainContent(contentContainer);
        handleNavForm();
    }

    @FXML
    private void handleNavForm() {
        NavigationUtil.loadView("/ni/edu/uam/view/EmployeeFormView.fxml");
        updateActiveButton(btnNavForm, btnNavList);
    }

    @FXML
    private void handleNavList() {
        NavigationUtil.loadView("/ni/edu/uam/view/EmployeeListView.fxml");
        updateActiveButton(btnNavList, btnNavForm);
    }

    private void updateActiveButton(Button active, Button inactive) {
        active.getStyleClass().removeAll("active");
        inactive.getStyleClass().removeAll("active");
        active.getStyleClass().add("active");
    }
}