package org.skar.pixivdl.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    Pane authcontainer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            URL resource = getClass().getResource("/view/auth/Auth.fxml");
            AnchorPane authControl =  FXMLLoader.load(resource);
            authcontainer.getChildren().add(authControl);
        } catch (IOException e) {
            authcontainer.getChildren().add(new Label("Unable to load auth control"));
        }

    }
}
