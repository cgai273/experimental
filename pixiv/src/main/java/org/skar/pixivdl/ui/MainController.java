package org.skar.pixivdl.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    StackPane pageContainer;

    @FXML
    Pane authcontainer;

    @FXML
    Pane searchcontainer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.loadAuthPane();
            this.loadSearchPane();
            this.loadPagePane();
        } catch (IOException e) {
            authcontainer.getChildren().add(new Label("Unable to load auth control"));
        }

    }

    private void loadAuthPane() throws IOException {
        URL resource = getClass().getResource("/view/auth/Auth.fxml");
        AnchorPane authControl =  (new FXMLLoader(resource)).load();
        authcontainer.getChildren().add(authControl);
    }

    private void loadSearchPane() throws IOException {
        URL resource = getClass().getResource("/view/search/Search.fxml");
        AnchorPane searchControl = (new FXMLLoader(resource)).load();
        searchcontainer.getChildren().add(searchControl);
    }

    private void loadPagePane() throws IOException {
        URL resource = getClass().getResource("/view/page/Page.fxml");
        ScrollPane page = (new FXMLLoader(resource)).load();
        pageContainer.getChildren().add(page);
    }
}
