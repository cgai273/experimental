package org.skar.pixivdl.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.skar.pixivdl.Main;
import org.skar.pixivdl.entity.Illust;
import org.skar.pixivdl.models.SettingStore;
import org.skar.pixivdl.net.RestClient;
import org.skar.pixivdl.storage.FileIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;


public class IllustController {
    final Logger logger = LoggerFactory.getLogger(IllustController.class);
    final RestClient client;

    SettingStore settingStore;
    Illust illust;

    @FXML
    ImageView imageView;

    @FXML
    Label title;

    @FXML
    Text views;

    @FXML
    Text favorites;

    @FXML
    Hyperlink authorLink;

    @FXML
    Button download;


    class UpdateThumbImage implements Callback {
        private ImageView image;


        public UpdateThumbImage(ImageView image) {
            this.image = image;
        }

        @Override
        public void onFailure(Call call, IOException e) {
            logger.error(e.toString());
        }

        @Override
        public void onResponse(Call call, Response response) {
            InputStream inputStream = response.body().byteStream();
            Image img = new Image(inputStream);
            image.setSmooth(true);
            image.setPreserveRatio(true);
            image.setImage(img);
        }
    }

    class SaveOriginalImage implements Callback {
        private File directory;


        public SaveOriginalImage(File directory) {
            this.directory = directory;
        }

        @Override
        public void onFailure(Call call, IOException e) {
            logger.error(e.toString());
        }

        @Override
        public void onResponse(Call call, Response response) {
            InputStream inputStream = response.body().byteStream();
            String fileName = FileIO.extractFileNameFromUrl(response.request().url().toString());
            Path p = Paths.get(directory.getAbsolutePath(), fileName);
            FileIO.copyImageInputStream(inputStream, p);
        }
    }


    @FXML protected void handleSaveImage(ActionEvent evt) {
        File saveDir = settingStore.getSaveLocation();
        if (saveDir != null) {
            for(String url: illust.getImageUrlOriginal()) {
                try {
                    client.asyncImageDownload(url, new SaveOriginalImage(saveDir));
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }

    public IllustController() {
        client = Main.appContext().getRestClient();
        settingStore = Main.appContext().getSettingStore();
    }


    public void initialize(Illust illust) {
        logger.info("loaded {} {}", illust.getAuthorName(), illust.getImageUrlThumb());

        this.illust = illust;
        title.setText(illust.getTitle());
        views.setText(Long.toString(illust.getTotalViews()));
        favorites.setText(Long.toString(illust.getTotalBookmarks()));
        authorLink.setText(illust.getAuthorName());

        try {
            client.asyncImageDownload(illust.getImageUrlThumb(), new UpdateThumbImage(imageView));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
