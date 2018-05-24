package org.skar.pixivdl.context;

import org.skar.pixivdl.models.PageStore;
import org.skar.pixivdl.models.SessionStore;

import javax.inject.Inject;

public class AppContext {

    private SessionStore sessionStore;
    private PageStore pageStore;

    @Inject
    public AppContext(
            SessionStore sessionStore,
            PageStore pageStore) {
        this.sessionStore = sessionStore;
        this.pageStore = pageStore;
    }

    public SessionStore getSessionStore() { return sessionStore; }
    public PageStore getPageStore() { return pageStore; }
}