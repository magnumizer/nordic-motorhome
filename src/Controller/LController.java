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
import java.sql.Date;
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
                    if (loginHandler.handleLogin(positionBox.getValue(), nameField.getText(), passwordField.getText()))
                    {
                        //if password is true/correct, move onto next screen based on position
                        System.out.println("Password accepted.");
                        stageHandler.handleSceneChange(positionBox.getValue());
                    }
                    else
                    {
                        //if password is false/incorrect, clear the password field, notify user
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
                    passwordField.requestFocus();
                }
            }
            else
            {
                stageHandler.displayAlert("Name not specified", "Name is missing", "Please enter a username");
                nameField.requestFocus();
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

        //get data from db later, for now add here

        if (!Employee.checkIfUserExists("test0"))
        {
            Admin admin = new Admin("ad", "ad", new Date(1,1,1), "ad", 1, "ad");
            admin.setUsername("test0");
            admin.setPassword("123");

            Employee.allEmployees.add(admin);

        }
    }
}

