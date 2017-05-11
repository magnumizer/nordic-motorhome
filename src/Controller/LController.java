package Controller;//Magnus Svendsen DAT16i

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class LController implements Initializable
{
    ObservableList<String> positions =
            FXCollections.observableArrayList(
                    "Admin",
                    "Sales Assistant",
                    "Auto Mechanic"
            );

    //region FXML
    @FXML
    private ComboBox<String> positionBox;
    @FXML
    private TextField nameField;
    @FXML
    private PasswordField passwordField;
    //endregion

    private LoginHandler loginHandler = new LoginHandler();
    private StageHandler stageHandler = new StageHandler();

    public void onLoginBtnPressed(ActionEvent actionEvent)
    {
        if (!positionBox.getSelectionModel().isEmpty())
        {
            if (!nameField.getText().equals(""))
            {
                if (!passwordField.getText().equals(""))
                {
                    if (loginHandler.handlePassword(positionBox.getValue(), nameField.getText(), passwordField.getText()))
                    {
                        //if password is true/correct, move onto next screen based on position
                        System.out.println("Password accepted.");
                        stageHandler.handleSceneChange(positionBox.getValue());
                    }
                    else
                    {
                        //if password is false/incorrect, clear the password field
                        if (Main.primaryStage.isShowing())
                        {
                            passwordField.clear();
                            stageHandler.displayAlert("Error logging in", "One or more fields are incorrect", "Please try again");
                        }
                    }
                }
                else
                {
                    stageHandler.displayAlert("Password not specified", "Password can't be blank", "Please enter a valid password");
                }
            }
            else
            {
                stageHandler.displayAlert("Name not specified", "Name is missing", "Please enter a username");
            }
        }
        else
        {
            stageHandler.displayAlert("Position not selected", "Position is missing", "Please select a position");
        }
    }


    public void onExitBtnPressed(ActionEvent actionEvent)
    {
        stageHandler.closeProgram();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        positionBox.setItems(positions);

        //get accounts data from db later, for now add them here
        AccountHandler.adminAccounts.put("test", "123");
        AccountHandler.salesAssistantAccounts.put("test", "123");
        AccountHandler.autoMechanicAccounts.put("test", "123");
    }
}

