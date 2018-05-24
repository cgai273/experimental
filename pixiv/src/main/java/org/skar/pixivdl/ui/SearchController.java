package org.skar.pixivdl.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.skar.pixivdl.Main;
import org.skar.pixivdl.models.SessionStore;
import org.skar.pixivdl.entity.Page;

public class SearchController {

    @FXML
    TextField searchinput;

    @FXML
    Button searchhitaction;


    @FXML
    public void handleSearchSubmit(ActionEvent e) {
        String keyword = searchinput.getText();

        if (keyword != null && keyword.length() > 0) {
            SessionStore controller = Main.appContext().getSessionStore();
            Page page = controller.searchIllustration(keyword);

            if (page != null) {
                renderPage(page);
            }
        }
    }

    public void renderPage(Page page) {
    }
}
