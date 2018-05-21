package org.skar.pixivdl.context;

import org.skar.pixivdl.net.RestClient;
import org.skar.pixivdl.controllers.ApiController;

import javax.inject.Inject;

public class AppContext {

    private ApiController apiController;
    private RestClient client;

    @Inject
    public AppContext(
            RestClient client,
            ApiController apiController) {
        this.client = client;
        this.apiController = apiController;
    }

    public RestClient getRestClient() {
        return client;
    }

    public ApiController getApiController() { return apiController; }
}