module gameoflife {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens gameoflife to javafx.fxml;
    exports gameoflife;
}
