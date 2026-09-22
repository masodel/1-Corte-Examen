module ni.edu.uam._corteexamen {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam._corteexamen to javafx.fxml;
    exports ni.edu.uam._corteexamen;
}