package org.skar.pixivdl.entity;
import static com.google.common.truth.Truth.assertThat;

import org.json.JSONObject;
import org.junit.Test;
import org.skar.pixivdl.storage.FileIO;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class IllustTest {
    @Test public void testCreate_singlepage() throws URISyntaxException {
        URL jsonURL = getClass().getResource("/fissures/singleimage_illust.json");
        String json = FileIO.readJson(Paths.get(jsonURL.toURI()).toString());
        JSONObject img = new JSONObject(json);
        Illust illust = Illust.parseIllust(img);
        assertThat(illust.getImageUrlThumb()).isEqualTo("https://aadsfas.jpg");
        assertThat(illust.getImageUrlOriginal().size()).isEqualTo(1);
        assertThat(illust.getImageUrlOriginal().get(0)).isEqualTo("https://blah/123124124.jpg");
        assertThat(illust.getTitle()).isEqualTo("testTitle");
    }

    @Test public void testCreate_multipage() throws URISyntaxException {
        URL jsonURL = getClass().getResource("/fissures/multiimages_illust.json");
        String json = FileIO.readJson(Paths.get(jsonURL.toURI()).toString());
        JSONObject img = new JSONObject(json);
        Illust illust = Illust.parseIllust(img);
        assertThat(illust.getImageUrlThumb()).isEqualTo("https://asdfasdf804_psdaf.jpg");
        assertThat(illust.getImageUrlOriginal().size()).isEqualTo(2);
        assertThat(illust.getImageUrlOriginal().get(0)).isEqualTo("https://original.png");
        assertThat(illust.getImageUrlOriginal().get(1)).isEqualTo("https://original2.png");
        assertThat(illust.getTitle()).isEqualTo("testtitle");
    }
}
