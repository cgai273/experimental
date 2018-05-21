package org.skar.pixivdl.controllers;

import com.google.gson.Gson;
import okhttp3.Request;
import okhttp3.Response;
import org.skar.pixivdl.entity.Page;
import org.skar.pixivdl.entity.User;
import org.skar.pixivdl.net.ApiRequests;
import org.skar.pixivdl.net.RestClient;
import org.skar.pixivdl.storage.FileIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import java.io.IOException;
import java.nio.file.Paths;

public class ApiController {
    final Logger logger = LoggerFactory.getLogger(ApiController.class);

    private static final String SESSION_FILE = "session.json";
    User user;

    private final RestClient client;

    @Inject
    public ApiController(RestClient client) {
        this.client = client;

        if (user == null && Paths.get(SESSION_FILE).toFile().exists()) {
            logger.info("Not user logged in, looking for session file...");
            loadSession();
        }
    }

    public boolean isLoggedIn() {
        return this.user.getAccessToken() != null;
    }

    public User login(String username, String password) {
        User loggedInUser = this.client.login(username, password);
        if (loggedInUser != null) {
            user = loggedInUser;
            logger.info("User logged in, token: %s", user.getAccessToken());
            saveSession(user);

            return user;
        }

        return null;
    }

    public void saveSession(User user) {
        FileIO.writeJson(user.serialize(), SESSION_FILE);
    }

    public void loadSession() {
       String json = FileIO.readJson(SESSION_FILE);
       User user = new Gson().fromJson(json, User.class);
       logger.info("Found and loaded session file, token: %s", user.getAccessToken());
       this.user = user;
    }

    public void searchIllustration(String keyword) {
        Request r = ApiRequests.illustrationSearch(keyword);
        if (!isLoggedIn()) {
            logger.error("Need to login in order to search");
            return;
        }

        Page page = client.searchIllustration(keyword, user.getAccessToken());

    }
}
