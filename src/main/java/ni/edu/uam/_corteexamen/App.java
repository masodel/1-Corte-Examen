package ni.edu.uam._corteexamen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/app/view/MainView.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 950, 620);
        scene.getStylesheets().add(getClass().getResource("/com/app/css/styles.css").toExternalForm());

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