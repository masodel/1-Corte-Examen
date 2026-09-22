package ni.edu.uam._corteexamen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL fxmlLocation = getClass().getResource("/ni/edu/uam/view/MainView.fxml");
        if (fxmlLocation == null) {
            throw new IllegalStateException("No se encontró /ni/edu/uam/view/MainView.fxml en resources");
        }
        Parent root = FXMLLoader.load(fxmlLocation);

        Scene scene = new Scene(root, 950, 620);

        URL cssLocation = getClass().getResource("/ni/edu/uam/css/style.css");
        if (cssLocation != null) {
            scene.getStylesheets().add(cssLocation.toExternalForm());
        }

        primaryStage.setTitle("Gestión de Empleados");
        primaryStage.setMinWidth(850);
        primaryStage.setMinHeight(550);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}