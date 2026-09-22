module ni.edu.uam._corteexamen {
    requires javafx.controls;
    requires javafx.fxml;

    // ESTA LÍNEA ES LA QUE FALTA O RESUELVE EL ERROR:
    requires static lombok;

    // Accesos y aperturas para JavaFX y PropertyValueFactory de la tabla
    opens ni.edu.uam._corteexamen to javafx.fxml;
    opens ni.edu.uam._corteexamen.controller to javafx.fxml;
    opens ni.edu.uam._corteexamen.model to javafx.base;

    exports ni.edu.uam._corteexamen;
    exports ni.edu.uam._corteexamen.controller;
    exports ni.edu.uam._corteexamen.model;
}