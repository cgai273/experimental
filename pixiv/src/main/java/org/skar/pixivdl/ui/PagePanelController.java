package org.skar.pixivdl.ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import org.skar.pixivdl.Main;
import org.skar.pixivdl.entity.Illust;
import org.skar.pixivdl.entity.Page;
import org.skar.pixivdl.models.PageStore;
import org.skar.pixivdl.models.SessionStore;
import org.skar.pixivdl.models.SettingStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PagePanelController {
    final Logger logger = LoggerFactory.getLogger(PagePanelController.class);

    private final PageStore pageStore;
    private final SessionStore sessionStore;
    private final SettingStore settingStore;

    @FXML
    FlowPane illustContainer;

    public PagePanelController() {
        pageStore = Main.appContext().getPageStore();
        sessionStore = Main.appContext().getSessionStore();
        settingStore = Main.appContext().getSettingStore();

        pageStore.getCurrentPageIndex().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                logger.info("Page index updated");
                Page p = pageStore.getCurrentPage();
                clearCurrentPage();
                try {
                    renderCurrentPage(p);
                } catch(IOException e) {
                    logger.error(e.toString());
                }
            }
        });
    }

    private void clearCurrentPage() {
        illustContainer.getChildren().clear();
    }

    private void renderCurrentPage(Page p) throws IOException {
        if (p == null) return;
        URL illustTemplate = getClass().getResource("/view/illust/Illust.fxml");


        List<Pane> illustNodes = new ArrayList<>();
        for(Illust e : p.getIllusts()) {
            if (settingStore.isFavFilterActive()) {
                if (e.getTotalBookmarks() < settingStore.getFavFilterCount()) {
                    continue;
                }
            }

            FXMLLoader loader = new FXMLLoader(illustTemplate);
            Pane illustPane = loader.load();
            IllustController ctrl = loader.getController();
            ctrl.initialize(e);
            illustNodes.add(illustPane);
        }
        illustContainer.getChildren().addAll(illustNodes);
    }
}
