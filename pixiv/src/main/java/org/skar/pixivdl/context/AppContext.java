package org.skar.pixivdl.context;

import org.skar.pixivdl.models.PageStore;
import org.skar.pixivdl.models.SessionStore;
import org.skar.pixivdl.net.RestClient;

import javax.inject.Inject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppContext {
    private static final int BACKGROUND_THREAD_COUNT = 12;

    private SessionStore sessionStore;
    private PageStore pageStore;
    private RestClient restClient;

    @Inject
    public AppContext(
            RestClient restClient,
            SessionStore sessionStore,
            PageStore pageStore) {
        this.sessionStore = sessionStore;
        this.pageStore = pageStore;
        this.restClient = restClient;
    }

    public RestClient getRestClient() {return restClient;}
    public SessionStore getSessionStore() { return sessionStore; }
    public PageStore getPageStore() { return pageStore; }
}