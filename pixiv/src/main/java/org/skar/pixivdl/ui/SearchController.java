package org.skar.pixivdl.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.skar.pixivdl.Main;
import org.skar.pixivdl.models.PageStore;
import org.skar.pixivdl.models.SessionStore;
import org.skar.pixivdl.entity.Page;

public class SearchController {
    private final PageStore pageStore;

    @FXML
    TextField searchinput;

    @FXML
    Button searchhitaction;

    public SearchController() {
        pageStore = Main.appContext().getPageStore();
    }

    @FXML
    public void handleSearchSubmit(ActionEvent e) {
        String keyword = searchinput.getText();

        if (keyword != null && keyword.length() > 0) {
            SessionStore controller = Main.appContext().getSessionStore();
            Page page = controller.searchIllustration(keyword);
            pageStore.init(page);
        }
    }

}
