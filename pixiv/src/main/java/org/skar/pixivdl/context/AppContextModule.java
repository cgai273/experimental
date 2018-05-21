package org.skar.pixivdl.context;

import com.google.inject.AbstractModule;
import org.skar.pixivdl.Main;
import org.skar.pixivdl.controllers.ControllersModule;
import org.skar.pixivdl.net.NetModule;

public class AppContextModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new ControllersModule());
    }
}
