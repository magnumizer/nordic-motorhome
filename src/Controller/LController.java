package Controller;//Magnus Svendsen DAT16i

import Handler.LoginHandler;
import Handler.StageHandler;
import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class LController
{
    //region FXML
    @FXML
    private TextField nameField;
    @FXML
    private PasswordField passwordField;
    //endregion

    private LoginHandler loginHandler = new LoginHandler();
    private StageHandler stageHandler = new StageHandler();

    public void onLoginBtnPressed(ActionEvent actionEvent)
    {
        if (!nameField.getText().equals(""))
        {
            if (!passwordField.getText().equals(""))
            {
                if (loginHandler.handleLogin(nameField.getText(), passwordField.getText()))
                {
                    //if password is true/correct, move onto next screen based on position
                    System.out.println("Password accepted.");
                    stageHandler.handleSceneChange();
                }
                else
                {
                    //if password is false/incorrect, clear the password field, notify user
                    if (Main.primaryStage.isShowing())
                    {
                        passwordField.clear();
                        stageHandler.displayError("Error logging in", "One or more fields are incorrect", "Please try again");
                    }
                }
            }
            else
            {
                stageHandler.displayError("Password not specified", "Password can't be blank", "Please enter a valid password");
                passwordField.requestFocus();
            }
        }
        else
        {
            stageHandler.displayError("Name not specified", "Name is missing", "Please enter a username");
            nameField.requestFocus();
        }
    }

    public void onExitBtnPressed(ActionEvent actionEvent)
    {
        stageHandler.closeProgram();
    }

}

