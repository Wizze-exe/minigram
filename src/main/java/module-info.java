module com.clone.minigram {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires javafx.swing;

    opens com.clone.minigram to javafx.fxml;
    exports com.clone.minigram;

}