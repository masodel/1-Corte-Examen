package ni.edu.uam._corteexamen.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class NavigationUtil {
    private static StackPane mainContent;

    public static void setMainContent(StackPane content) {
        mainContent = content;
    }

    public static void loadView(String fxmlPath) {
        if (mainContent == null) {
            throw new IllegalStateException("El contenedor principal no ha sido inicializado.");
        }
        try {
            FXMLLoader loader = new FXMLLoader(NavigationUtil.class.getResource(fxmlPath));
            Node node = loader.load();
            mainContent.getChildren().setAll(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}