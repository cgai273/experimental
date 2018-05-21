package org.skar.pixivdl.context;

import org.skar.pixivdl.net.RestClient;
import org.skar.pixivdl.controllers.ApiController;

import javax.inject.Inject;

public class AppContext {

    private ApiController apiController;

    @Inject
    public AppContext(ApiController apiController) {
        this.apiController = apiController;
    }

    public ApiController getApiController() { return apiController; }
}