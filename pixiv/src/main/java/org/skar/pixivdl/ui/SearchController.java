package org.skar.pixivdl.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import org.skar.pixivdl.Main;
import org.skar.pixivdl.models.PageStore;
import org.skar.pixivdl.models.SessionStore;
import org.skar.pixivdl.entity.Page;
import org.skar.pixivdl.models.SettingStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class SearchController {
    final Logger logger = LoggerFactory.getLogger(SearchController.class);

    private final PageStore pageStore;
    private final SettingStore settingStore;


    @FXML
    TextField searchinput;

    @FXML
    Button searchhitaction;

    @FXML
    TextField saveLocationInput;

    public SearchController() {
        settingStore = Main.appContext().getSettingStore();
        pageStore = Main.appContext().getPageStore();
    }

    @FXML
    public void handleSearchSubmit(ActionEvent e) {
        String keyword = searchinput.getText();

        if (keyword != null && keyword.length() > 0) {
            SessionStore controller = Main.appContext().getSessionStore();
            Page page = controller.searchIllustration(keyword);
            logger.info("Next page is {}", page.getNextUrl());
            pageStore.init(page);
        }
    }

    @FXML
    public void handleSaveLocationSelect(ActionEvent e) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);

        if (selectedDirectory != null) {
            settingStore.setSaveLocation(selectedDirectory);
            saveLocationInput.setText(selectedDirectory.getAbsolutePath());
        }
    }

}
