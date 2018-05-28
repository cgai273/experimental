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
    private final SessionStore sessionStore;


    @FXML
    TextField searchinput;

    @FXML
    Button searchhitaction;

    @FXML
    TextField saveLocationInput;

    public SearchController() {
        settingStore = Main.appContext().getSettingStore();
        pageStore = Main.appContext().getPageStore();
        sessionStore = Main.appContext().getSessionStore();
    }

    @FXML
    public void handleSearchSubmit(ActionEvent e) {
        String keyword = searchinput.getText();

        if (keyword != null && keyword.length() > 0) {
            Page page = sessionStore.searchIllustration(keyword);
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

    // TODO: Cache page thumb images.
    @FXML
    public void handlePrevPage(ActionEvent e) {

    }

    // TODO: Cache page thumb images.
    @FXML
    public void handleNextPage(ActionEvent e) {
        if (pageStore.next() == null) {
            Page p = pageStore.getCurrentPage();
            if (p.getNextUrl() != null) {
                Page nextPage = sessionStore.getNextPage(p.getNextUrl());
                logger.info("Next page is {}", nextPage.getNextUrl());
                pageStore.addPage(nextPage);
                pageStore.next();
            }
        }
    }

}
