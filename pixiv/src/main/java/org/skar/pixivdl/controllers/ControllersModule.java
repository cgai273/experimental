package org.skar.pixivdl.controllers;

import com.google.inject.AbstractModule;
import org.skar.pixivdl.net.NetModule;

public class ControllersModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new NetModule());
    }
}
