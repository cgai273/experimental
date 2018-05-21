package org.skar.pixivdl;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import okhttp3.Response;
import org.skar.pixivdl.context.AppContext;
import org.skar.pixivdl.context.AppContextModule;
import org.skar.pixivdl.net.ApiRequests;
import org.skar.pixivdl.net.RestClient;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    static AppContext appContext;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Injector injector = Guice.createInjector(new AppContextModule());
        AppContext ctx = injector.getInstance(AppContext.class);
        setAppContext(ctx);

        URL mainView = getClass().getResource("/view/main/Main.fxml");
        if (mainView == null) {
            throw new IOException("Can't find path for main template");
        }
        Parent root = FXMLLoader.load(mainView);
        Scene scene = new Scene(root, 300, 275);
        primaryStage.setTitle("Pixiv DL");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    static public void setAppContext(AppContext ctx) {
        appContext = ctx;
    }

    static public AppContext appContext() {
        return appContext;
    }
}