package org.skar.pixivdl.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import org.skar.pixivdl.Main;
import org.skar.pixivdl.entity.Illust;
import org.skar.pixivdl.net.RestClient;
import org.skar.pixivdl.storage.FileIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;


public class IllustController {

    final Logger logger = LoggerFactory.getLogger(IllustController.class);
    final RestClient client;
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

    public IllustController() {
        client = Main.appContext().getRestClient();
    }

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
            File file = new File(response.request().url().toString());
//            try {
//                Files.copy(inputStream, Paths.get(FileIO.extractFileNameFromUrl(response.request().url().toString())));
//            } catch (IOException e) {e.toString();};
            image.setImage(img);
        }
    }

    public void initialize(Illust illust) {
        logger.info("loaded {} {}", illust.getAuthorName(), illust.getImageUrlThumb());

        title.setText(illust.getTitle());
        views.setText(Long.toString(illust.getTotalViews()));
        favorites.setText(Long.toString(illust.getTotalBookmarks()));
        authorLink.setText(illust.getAuthorName());

        try {
            // TODO: Download image and set it
            client.asyncImageDownload(illust.getImageUrlThumb(), new UpdateThumbImage(imageView));
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }
}
