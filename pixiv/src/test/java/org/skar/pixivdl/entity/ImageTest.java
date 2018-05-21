package org.skar.pixivdl.entity;
import static com.google.common.truth.Truth.assertThat;

import okhttp3.Request;
import org.json.JSONObject;
import org.junit.Test;
import org.skar.pixivdl.storage.FileIO;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class ImageTest {
    @Test public void testCreate_singlepage() throws URISyntaxException {
        URL jsonURL = getClass().getResource("/fissures/singlepage_image.json");
        String json = FileIO.readJson(Paths.get(jsonURL.toURI()).toString());
        JSONObject img = new JSONObject(json);
        Image image = Image.parseImage(img);
        assertThat(image.getImageUrlThumb()).isEqualTo("https://aadsfas.jpg");
        assertThat(image.getImageUrlOriginal()).isEqualTo("https://blah/123124124.jpg");
        assertThat(image.getTitle()).isEqualTo("testTitle");
    }
}
