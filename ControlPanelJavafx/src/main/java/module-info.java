module br.unesp.rc.shhc.panel {
    requires javafx.controls;
    requires javafx.fxml;

    opens br.unesp.rc.shhc.panel to javafx.fxml;
    exports br.unesp.rc.shhc.panel;
}
