module fxml.ftpfx {
    requires javafx.controls;
    requires javafx.fxml;
	requires commons.net;

    opens fxml.ftpfx to javafx.fxml;
    exports fxml.ftpfx;
}