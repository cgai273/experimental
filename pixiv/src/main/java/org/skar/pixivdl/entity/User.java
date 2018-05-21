package org.skar.pixivdl.entity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONObject;
import org.skar.pixivdl.storage.FileIO;

import java.nio.file.Path;

public class User {
    String accessToken;
    String tokenType;
    String refreshToken;
    String deviceToken;

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public User(String accessToken, String tokenType, String refreshToken) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.refreshToken = refreshToken;
    }

    public String serialize() {
        Gson gson = new Gson();
        String json = gson.toJson(this, new TypeToken<User>(){}.getType());
        return json;
    }
}
