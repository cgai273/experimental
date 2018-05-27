package org.skar.pixivdl.net;

import com.google.common.collect.*;
import okhttp3.*;
import okhttp3.internal.http2.Header;
import okio.BufferedSink;

import javax.annotation.Nullable;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class ApiRequests {
    private static String API_SCHEME = "https";
    private static String API_URL_BASE = "app-api.pixiv.net";
    private static String API_CLIENT_ID = "KzEZED7aC0vird8jWyHM38mXjNTY";
    private static String API_CLIENT_SECRET = "W9JZoJe00qPvJsiyCGT3CCtC6ZUtdpKpzMbNlUGP";

    private static String API_OS_FILTER = "for_ios";

    public static String API_SEARCH_TARGET_PARTIAL_MARCH_FOR_TAGS = "partial_match_for_tags";
    public static String API_SORT_ORDER_DATE_DESC = "date_desc";

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType FORM_URL = MediaType.parse("application/x-www-form-urlencoded");

    // this does not use common header
    public static Request authRequest(String username, String password) {
        if (username == null || password == null) {
            throw new IllegalArgumentException("Username and password cannot be null");
        }

        Multimap<String, String> dataMap = ArrayListMultimap.create();

        dataMap.put("client_id", API_CLIENT_ID);
        dataMap.put("client_secret", API_CLIENT_SECRET);
        dataMap.put("get_secure_url", "1");
        dataMap.put("grant_type", "password");
        dataMap.put("username", username);
        dataMap.put("password", password);

        RequestBody body = RequestBody.create(FORM_URL, mapToUrlEncoded(dataMap));

        return new Request.Builder()
                .url("https://oauth.secure.pixiv.net/auth/token")
                .post(body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
    }

    public static Request illustrationSearch(String keyword, String accessToken) {
        return illustrationSearch(keyword, null, null, accessToken);
    }

    public static Request illustrationSearch(String keyword, String searchTarget, String sortOrder, String accessToken) {
        if (keyword == null) {
            throw new IllegalArgumentException("Keyword cannot be null");
        }

        if (searchTarget == null) {
            searchTarget = API_SEARCH_TARGET_PARTIAL_MARCH_FOR_TAGS;
        }

        if (sortOrder == null) {
            sortOrder = API_SORT_ORDER_DATE_DESC;
        }

        HttpUrl url =  getBaseUrl().addPathSegments("v1/search/illust")
                .addQueryParameter("word",  keyword)
                .addQueryParameter("search_target", searchTarget)
                .addQueryParameter("sort", sortOrder)
                .addQueryParameter("filter", API_OS_FILTER).build();

        return new Request.Builder()
                .url(url)
                .headers(getCommonHeaders(accessToken).build())
                .build();
    }

    public static Request imageDownload(String imageUrl) {
        return new Request.Builder()
                .url(imageUrl)
                .addHeader("Referer", "http://www.pixiv.net/")
                .build();
    }

    private static HttpUrl.Builder getBaseUrl() {
        return new HttpUrl.Builder()
                .scheme(API_SCHEME)
                .host(API_URL_BASE);
    }


    private static Headers.Builder getCommonHeaders(String accessToken) {
        if (accessToken == null) {
            throw new IllegalArgumentException("Access token cannot be null for api call");
        }
        return new Headers.Builder()
                .add("User-Agent", "PixivIOSApp/7.1.11 (iOS 9.0; iPhone8,2)")
                .add("App-OS", "ios")
                .add("Accept-Language", "en-us")
                .add("App-OS-Version", "9.3.3")
                .add("App-Version", "7.1.11")
                .add("Authorization", String.format("Bearer %s", accessToken));
    }

    private static String mapToUrlEncoded(Multimap<String, String> map) {
        String[] data =  map.entries().stream()
                .map((entry) -> String.format("%s=%s", entry.getKey(), entry.getValue())).toArray(String[]::new);

        return String.join("&", data);
    }
}
