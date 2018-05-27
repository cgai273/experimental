package org.skar.pixivdl.entity;

import static com.google.common.truth.Truth.assertThat;

import org.json.JSONObject;
import org.junit.Test;
import org.skar.pixivdl.storage.FileIO;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class PageTest {

    @Test public void testCreate() throws URISyntaxException {
        URL jsonURL = getClass().getResource("/fissures/page.json");
        String json = FileIO.readJson(Paths.get(jsonURL.toURI()).toString());
        JSONObject pageJSON = new JSONObject(json);
        Page p = Page.parsePage(pageJSON);
        assertThat(p.getIllusts().size()).isEqualTo(2);
        assertThat(p.getIllusts().get(0)).isInstanceOf(Illust.class);
        assertThat(p.getNextUrl()).isEqualTo("https://nextst?word=%E3%81%AA%E3%81%AE%E3%81%AF&search_target=partial_match_for_tags&sort=date_desc&filter=for_ios&offset=30");
    }
}
