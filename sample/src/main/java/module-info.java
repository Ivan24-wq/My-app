module sample.sample {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires eu.hansolo.tilesfx;
    requires java.desktop;

    opens sample.sample to javafx.fxml;
    exports sample.sample;
}