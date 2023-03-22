module com.clone.minigram {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.clone.minigram to javafx.fxml;
    exports com.clone.minigram;
}