package Controller;//Magnus Svendsen DAT16i

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class LController implements Initializable
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

    public void info(ActionEvent actionEvent)
    {
        //get data from db later, for now add here

        if (!Employee.checkIfUserExists("test0"))
        {
            LocalDate localDate = LocalDate.now();
            Admin admin = new Admin("ad", "ad", localDate, "ad", 1, "ad", "test0", "123");

            Employee.allEmployees.add(admin);

        }

        if (!Employee.checkIfUserExists("sa"))
        {
            SalesAssistant salesAssistant = new SalesAssistant("sa", "sa", LocalDate.now(), "sa", 0, "sa", "sa", "sa");
            Employee.allEmployees.add(salesAssistant);
        }

        if (!Employee.checkIfUserExists("am"))
        {
            AutoMechanic autoMechanic = new AutoMechanic("am", "am", LocalDate.now(), "am", 0, "am", "am", "am");
            Employee.allEmployees.add(autoMechanic);
        }


        //add example customer for now
        Customer customer = new Customer("alfred", "asd", LocalDate.now(), "cxz", 345, "zxc");
        Customer.allCustomers.add(customer);

        Customer customer1 = new Customer("tom", "asd", LocalDate.now(), "cxz", 345, "zxc");
        Customer.allCustomers.add(customer1);

        Customer customer2 = new Customer("sofia", "asd", LocalDate.now(), "cxz", 345, "zxc");
        Customer.allCustomers.add(customer2);

        //add example motorhome for now
        Motorhome motorhome1 = new Motorhome("example1", "ad", "ad", 500f);
        Motorhome.allMotorhomes.add(motorhome1);

        Motorhome motorhome2 = new Motorhome("example2", "ad", "ad", 500f);
        Motorhome.allMotorhomes.add(motorhome2);

        Motorhome motorhome3 = new Motorhome("example3", "ad", "ad", 500f);
        Motorhome.allMotorhomes.add(motorhome3);

        //add accessories here for now, get from db later (crud in admin?)
        Accessory bikeRack = new Accessory("Bike Rack", 100, 35);
        Accessory bedLinen = new Accessory("Bed Linen", 10, 150);
        Accessory childSeat = new Accessory("Child Seat", 50, 80);
        Accessory picnicTable = new Accessory("Picnic Table", 150, 10);
        Accessory chair = new Accessory("Chair", 50, 200);

        Accessory.allAccessories.put(bikeRack.getType(), bikeRack);
        Accessory.allAccessories.put(bedLinen.getType(), bedLinen);
        Accessory.allAccessories.put(childSeat.getType(), childSeat);
        Accessory.allAccessories.put(picnicTable.getType(), picnicTable);
        Accessory.allAccessories.put(chair.getType(), chair);

        System.out.println("data added to program");
    }

    public void onExitBtnPressed(ActionEvent actionEvent)
    {
        stageHandler.closeProgram();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {


    }
}

