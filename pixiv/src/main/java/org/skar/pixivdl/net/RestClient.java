package org.skar.pixivdl.net;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.skar.pixivdl.controllers.ApiController;
import org.skar.pixivdl.entity.Page;
import org.skar.pixivdl.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class RestClient {
    final Logger logger = LoggerFactory.getLogger(ApiController.class);
    private final OkHttpClient client = new OkHttpClient();

    // This is used after login
    private OkHttpClient apiClient;

    public RestClient() {

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
            return null;
        } catch (IOException e) {
            logger.error("Search Illustration request failed with %s", e.toString());
        }

        return null;
    }
}
