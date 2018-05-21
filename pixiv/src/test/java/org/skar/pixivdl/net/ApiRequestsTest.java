package org.skar.pixivdl.net;

import static com.google.common.truth.Truth.assertThat;

import okhttp3.Request;
import org.junit.Test;

public class ApiRequestsTest {

    @Test public void testSearchIllustration_defaultUrlExact() {
        Request r = ApiRequests.illustrationSearch("abc");
        String urlString = r.url().toString();
        assertThat(urlString).startsWith("https://app-api.pixiv.net/v1/search/illust?");
        assertThat(urlString).contains("word=abc");
        assertThat(urlString).contains("search_target=partial_match_for_tags");
        assertThat(urlString).contains("sort=date_desc");
        assertThat(urlString).contains("filter=for_ios");
    }

    @Test public void testSearchIllustration_withUnicodeKeyword() {
        Request r = ApiRequests.illustrationSearch("かみかぜ");
        assertThat(r.toString()).contains("word=%E7%B8%BA%E4%B9%9D%E2%88%A9%E7%B8%BA%E4%B9%9D%E2%97%8F");
    }

    @Test public void testAuthRequest() {
        Request r = ApiRequests.authRequest("username", "password");
        assertThat(r.method()).isEqualTo("POST");
        assertThat(r.headers().toString()).contains("Content-Type: application/x-www-form-urlencoded");
    }
}
