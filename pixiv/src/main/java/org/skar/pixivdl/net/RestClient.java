package org.skar.pixivdl.net;

import okhttp3.*;
import org.json.JSONObject;
import org.skar.pixivdl.models.SessionStore;
import org.skar.pixivdl.entity.Page;
import org.skar.pixivdl.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class RestClient {
    final Logger logger = LoggerFactory.getLogger(SessionStore.class);

    private final OkHttpClient client = new OkHttpClient();

    // This is used after login
    private OkHttpClient apiClient;



    public RestClient() {
    }

    public void asyncImageDownload(String imageUrl, Callback cb) throws IOException {
        client.newCall(ApiRequests.imageDownload(imageUrl)).enqueue(cb);
    }

    public Response send(Request r) throws IOException {
        Response response = client.newCall(r).execute();

        return response;
    }

    public User login(String username, String password) {
        Request r = ApiRequests.authRequest(username, password);
        Response res;
        try {
            res =  send(r);
            String jsonData = res.body().string();
            JSONObject Jobject = new JSONObject(jsonData);
            JSONObject jsonRes = Jobject.getJSONObject("response");
            User user = new User(jsonRes.getString("access_token"), jsonRes.getString("token_type"),
                    jsonRes.getString("refresh_token"));

            return user;

        } catch(IOException e) {
            return null;
        }
    }

    public Page searchIllustration(String keyword, String accessToken) {
        try {
            Request r = ApiRequests.illustrationSearch(keyword, accessToken);
            Response res = send(r);
            JSONObject Jobject = new JSONObject(res.body().string());
            return Page.parsePage(Jobject);
        } catch (IOException e) {
            logger.error("Search Illustration request failed with {}", e);
        }

        return null;
    }
}
