package Controller;//Magnus Svendsen DAT16i

import Model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class AController implements Initializable
{
    ObservableList<String> positions =
            FXCollections.observableArrayList(
                    "Admin",
                    "Sales Assistant",
                    "Auto Mechanic"
            );

    //region FXML
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, String> customerNameCol;
    @FXML
    private TableColumn<Customer, String> customerCPRCol;
    @FXML
    private TableColumn<Customer, String> customerBirthdayCol;
    @FXML
    private TableColumn<Customer, String> customerAddressCol;
    @FXML
    private TableColumn<Customer, Integer> customerTlfCol;
    @FXML
    private TableColumn<Customer, String> customerEmailCol;
    @FXML
    private TextField nameField;
    @FXML
    private TextField cprField;
    @FXML
    private DatePicker birthdayPicker;
    @FXML
    private TextField addressField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField tlfField;
    @FXML
    private ComboBox<String> positionBox;
    @FXML
    private TextField usernameField;
    @FXML
    private Label confirmLabel;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmField;
    //endregion

    private StageHandler stageHandler = new StageHandler();

    public void onLogOutBtnPressed(ActionEvent actionEvent)
    {
        stageHandler.logOut();
    }

    public void onAddStaffBtnPressed(ActionEvent actionEvent)
    {
        if (!nameField.getText().equals(""))
        {
            if (!cprField.getText().equals(""))
            {
                if (birthdayPicker.getValue() != null)
                {
                    if (!addressField.getText().equals(""))
                    {
                        if (!emailField.getText().equals(""))
                        {
                            if (!tlfField.getText().equals(""))
                            {
                                if (!positionBox.getSelectionModel().isEmpty())
                                {
                                    if (!usernameField.getText().equals(""))
                                    {
                                        if (!passwordField.getText().equals(""))
                                        {
                                            if (!confirmField.getText().equals(""))
                                            {
                                                if (!Employee.checkIfUserExists(usernameField.getText()))
                                                {
                                                    if (passwordField.getText().equals(confirmField.getText()))
                                                    {
                                                        confirmLabel.setStyle("-fx-text-fill: black");
                                                        Date date = Date.valueOf(birthdayPicker.getValue());
                                                        int number = Integer.parseInt(tlfField.getText());

                                                        switch (positionBox.getValue())
                                                        {
                                                            case "Admin":
                                                                Admin admin = new Admin(nameField.getText(), cprField.getText(), date, addressField.getText(), number, emailField.getText());
                                                                admin.setUsername(usernameField.getText());
                                                                admin.setPassword(passwordField.getText());
                                                                Employee.allEmployees.add(admin);
                                                                stageHandler.displayAlert("Success", "Admin successfully added to the system", "Press OK to continue");
                                                                break;
                                                            case "Sales Assistant":
                                                                SalesAssistant salesAssistant = new SalesAssistant(nameField.getText(), cprField.getText(), date, addressField.getText(), number, emailField.getText());
                                                                salesAssistant.setUsername(usernameField.getText());
                                                                salesAssistant.setPassword(passwordField.getText());
                                                                Employee.allEmployees.add(salesAssistant);
                                                                stageHandler.displayAlert("Success", "Sales Assistant successfully added to the system", "Press OK to continue");
                                                                break;
                                                            case "Auto Mechanic":
                                                                AutoMechanic autoMechanic = new AutoMechanic(nameField.getText(), cprField.getText(), date, addressField.getText(), number, emailField.getText());
                                                                autoMechanic.setUsername(usernameField.getText());
                                                                autoMechanic.setPassword(passwordField.getText());
                                                                Employee.allEmployees.add(autoMechanic);
                                                                stageHandler.displayAlert("Success", "Auto Mechanic successfully added to the system", "Press OK to continue");
                                                                break;
                                                            default:
                                                                System.out.println("Error in positionBox reference.");
                                                                break;
                                                        }

                                                        clearFields();
                                                    }
                                                    else
                                                    {
                                                        stageHandler.displayAlert("Password not confirmed", "Ensure password confirmation is identical to password", "Please confirm password");
                                                        confirmLabel.setStyle("-fx-text-fill: red");
                                                        confirmField.requestFocus();
                                                        confirmField.selectAll();
                                                    }
                                                }
                                                else
                                                {
                                                    stageHandler.displayAlert("Error creating staff member", "User already exists", "Please enter a different username");
                                                    usernameField.requestFocus();
                                                }
                                            }
                                            else
                                            {
                                                stageHandler.displayAlert("Password not confirmed", "Confirm Password field is blank", "Please confirm password");
                                                confirmField.requestFocus();
                                            }
                                        }
                                        else
                                        {
                                            stageHandler.displayAlert("Password not specified", "Password can't be blank", "Please enter a password");
                                            passwordField.requestFocus();
                                        }
                                    }
                                    else
                                    {
                                        stageHandler.displayAlert("Username not specified", "Username is missing", "Please enter a username");
                                        usernameField.requestFocus();
                                    }
                                }
                                else
                                {
                                    stageHandler.displayAlert("Position not specified", "Position is missing", "Please select a position");
                                    positionBox.show();
                                }
                            }
                            else
                            {
                                stageHandler.displayAlert("Phone number not specified", "Phone number is missing", "Please enter a phone number");
                                tlfField.requestFocus();
                            }
                        }
                        else
                        {
                            stageHandler.displayAlert("E-mail not specified", "E-mail is missing", "Please enter an e-mail address");
                            emailField.requestFocus();
                        }
                    }
                    else
                    {
                        stageHandler.displayAlert("Address not specified", "Address is missing", "Please enter an address");
                        addressField.requestFocus();
                    }
                }
                else
                {
                    stageHandler.displayAlert("Date of birth not specified", "Date of birth is missing", "Please enter a date of birth");
                    birthdayPicker.show();
                }
            }
            else
            {
                stageHandler.displayAlert("CPR not specified", "CPR is missing", "Please enter a CPR number");
                cprField.requestFocus();
            }
        }
        else
        {
            stageHandler.displayAlert("Name not specified", "Name is missing", "Please enter a name");
            nameField.requestFocus();
        }
    }

    private void clearFields()
    {
        nameField.clear();
        cprField.clear();
        birthdayPicker.setValue(null);
        addressField.clear();
        tlfField.clear();
        emailField.clear();
        positionBox.getSelectionModel().clearSelection();
        usernameField.clear();
        passwordField.clear();
        confirmField.clear();
    }

    private void centerAlignTables()
    {
        customerNameCol.setStyle("-fx-alignment: CENTER");
        customerCPRCol.setStyle("-fx-alignment: CENTER");
        customerBirthdayCol.setStyle("-fx-alignment: CENTER");
        customerAddressCol.setStyle("-fx-alignment: CENTER");
        customerTlfCol.setStyle("-fx-alignment: CENTER");
        customerEmailCol.setStyle("-fx-alignment: CENTER");
    }

    private void setupTableColumns()
    {
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        customerCPRCol.setCellValueFactory(new PropertyValueFactory<>("cpr"));
        customerBirthdayCol.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerTlfCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        customerEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void forceNumericValues()
    {
        tlfField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    tlfField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        centerAlignTables();
        setupTableColumns();
        customerTable.getItems().setAll(Customer.allCustomers);
        positionBox.setItems(positions);
        forceNumericValues();
    }
}
