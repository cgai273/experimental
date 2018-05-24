package org.skar.pixivdl.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.skar.pixivdl.Main;
import org.skar.pixivdl.entity.User;
import org.skar.pixivdl.models.SessionStore;


public class AuthController {
    @FXML
    TextField usernameinput;

    @FXML
    PasswordField passwordinput;

    @FXML
    Button loginaction;

    @FXML
    Button logoutaction;

    @FXML
    Button refreshtokenaction;

    @FXML
    TextArea autherrordisplay;


    @FXML protected void handleUserLogin(ActionEvent event) {
        if (validate()) {
            SessionStore sessionStore = Main.appContext().getSessionStore();

            User user = sessionStore.login(usernameinput.getText(), passwordinput.getText());

            autherrordisplay.setText(user.getAccessToken());
        }
    }


    private boolean validate() {
        return usernameinput.getText().length() > 0
                && passwordinput.getText().length() > 0;
    }
}
