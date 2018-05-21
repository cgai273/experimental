package org.skar.pixivdl.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.skar.pixivdl.Main;
import org.skar.pixivdl.controllers.ApiController;
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
            ApiController controller = Main.appContext().getApiController();
            Page page = controller.searchIllustration(keyword);
        }
    }
}
