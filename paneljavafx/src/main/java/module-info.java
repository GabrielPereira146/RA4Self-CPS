module br.unesp.rc {
    requires javafx.controls;
    requires javafx.fxml;

    opens br.unesp.rc to javafx.fxml;
    exports br.unesp.rc;
}
