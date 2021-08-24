module de.dhbw.fs120 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.json;


    opens de.dhbw.fs120 to javafx.fxml;
    exports de.dhbw.fs120;
}