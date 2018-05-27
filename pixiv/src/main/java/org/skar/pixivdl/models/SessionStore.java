package org.skar.pixivdl.models;

import com.google.gson.Gson;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.skar.pixivdl.entity.Page;
import org.skar.pixivdl.entity.User;
import org.skar.pixivdl.net.RestClient;
import org.skar.pixivdl.storage.FileIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import java.nio.file.Paths;

public class SessionStore {
    final Logger logger = LoggerFactory.getLogger(SessionStore.class);

    private static final String SESSION_FILE = "session.json";
    private ObjectProperty<User> currentUser = new SimpleObjectProperty<>();

    private final RestClient client;

    @Inject
    public SessionStore(RestClient client) {
        this.client = client;

        if (currentUser.get() == null && Paths.get(SESSION_FILE).toFile().exists()) {
            logger.info("Not user logged in, looking for session file...");
            loadSession();
        }
    }

    public ObjectProperty<User> currentUserProperty() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser.get() != null && currentUser.get().getAccessToken() != null;
    }

    public User login(String username, String password) {
        User loggedInUser = this.client.login(username, password);
        if (loggedInUser != null) {
            currentUser.set(loggedInUser);
            logger.info("User logged in, token: {}", currentUser.get().getAccessToken());
            saveSession(currentUser.get());

            return currentUser.get();
        }

        return null;
    }

    public void saveSession(User user) {
        FileIO.writeJson(user.serialize(), SESSION_FILE);
    }

    public void loadSession() {
       String json = FileIO.readJson(SESSION_FILE);
       User user = new Gson().fromJson(json, User.class);
       logger.info("Found and loaded session file, token: {}", user.getAccessToken());
       currentUser.set(user);
    }

    public Page searchIllustration(String keyword) {
        if (!isLoggedIn()) {
            logger.error("Need to login in order to search");
            return null;
        }

        return client.searchIllustration(keyword, currentUser.get().getAccessToken());
    }
}
