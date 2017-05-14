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
import java.util.ResourceBundle;

public class AController implements Initializable
{
    ObservableList<String> positions =
            FXCollections.observableArrayList(
                    "Admin",
                    "Sales Assistant",
                    "Auto Mechanic"
            );

    ObservableList<String> sizes =
            FXCollections.observableArrayList(
                    "Small",
                    "Medium",
                    "Large"
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
    private TableView<Employee> staffTable;
    @FXML
    private TableColumn<Employee, String> staffNameCol;
    @FXML
    private TableColumn<Employee, String> staffCPRCol;
    @FXML
    private TableColumn<Employee, String> staffBirthdayCol;
    @FXML
    private TableColumn<Employee, String> staffAddressCol;
    @FXML
    private TableColumn<Employee, Integer> staffTlfCol;
    @FXML
    private TableColumn<Employee, String> staffEmailCol;

    @FXML
    private TableView<Reservation> reservationTable;
    @FXML
    private TableColumn<Reservation, Customer> reservationCustomerCol;
    @FXML
    private TableColumn<Reservation, Motorhome> reservationMotorhomeCol;
    @FXML
    private TableColumn<Reservation, String> reservationDateCol;
    @FXML
    private TableColumn<Reservation, String> reservationPickupCol;
    @FXML
    private TableColumn<Reservation, String> reservationDropoffCol;

    @FXML
    private TableView<Motorhome> motorhomeTable;
    @FXML
    private TableColumn<Motorhome, String> motorhomeIDCol;
    @FXML
    private TableColumn<Motorhome, String> motorhomeModelCol;
    @FXML
    private TableColumn<Motorhome, String> motorhomeBrandCol;
    @FXML
    private TableColumn<Motorhome, String> motorhomeSizeCol;
    @FXML
    private TableColumn<Motorhome, String> motorhomePriceCol;
    @FXML
    private TableColumn<Motorhome, String> motorhomeStatusCol;

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
    @FXML
    private TextField modelField;
    @FXML
    private TextField brandField;
    @FXML
    private ComboBox<String> sizeBox;
    @FXML
    private TextField priceField;
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
                                                        int number = Integer.parseInt(tlfField.getText());

                                                        switch (positionBox.getValue())
                                                        {
                                                            case "Admin":
                                                                Admin admin = new Admin(nameField.getText(), cprField.getText(), birthdayPicker.getValue(), addressField.getText(), number, emailField.getText());
                                                                admin.setUsername(usernameField.getText());
                                                                admin.setPassword(passwordField.getText());
                                                                Employee.allEmployees.add(admin);
                                                                stageHandler.displayInfo("Success", "Admin successfully added to the system", "Press OK to continue");
                                                                break;
                                                            case "Sales Assistant":
                                                                SalesAssistant salesAssistant = new SalesAssistant(nameField.getText(), cprField.getText(), birthdayPicker.getValue(), addressField.getText(), number, emailField.getText());
                                                                salesAssistant.setUsername(usernameField.getText());
                                                                salesAssistant.setPassword(passwordField.getText());
                                                                Employee.allEmployees.add(salesAssistant);
                                                                stageHandler.displayInfo("Success", "Sales Assistant successfully added to the system", "Press OK to continue");
                                                                break;
                                                            case "Auto Mechanic":
                                                                AutoMechanic autoMechanic = new AutoMechanic(nameField.getText(), cprField.getText(), birthdayPicker.getValue(), addressField.getText(), number, emailField.getText());
                                                                autoMechanic.setUsername(usernameField.getText());
                                                                autoMechanic.setPassword(passwordField.getText());
                                                                Employee.allEmployees.add(autoMechanic);
                                                                stageHandler.displayInfo("Success", "Auto Mechanic successfully added to the system", "Press OK to continue");
                                                                break;
                                                            default:
                                                                System.out.println("Error in positionBox reference.");
                                                                break;
                                                        }

                                                        staffTable.getItems().setAll(Employee.allEmployees);
                                                        clearStaffFields();
                                                    }
                                                    else
                                                    {
                                                        stageHandler.displayError("Password not confirmed", "Ensure password confirmation is identical to password", "Please confirm password");
                                                        confirmLabel.setStyle("-fx-text-fill: red");
                                                        confirmField.requestFocus();
                                                        confirmField.selectAll();
                                                    }
                                                }
                                                else
                                                {
                                                    stageHandler.displayError("Error creating staff member", "User already exists", "Please enter a different username");
                                                    usernameField.requestFocus();
                                                }
                                            }
                                            else
                                            {
                                                stageHandler.displayError("Password not confirmed", "Confirm Password field is blank", "Please confirm password");
                                                confirmField.requestFocus();
                                            }
                                        }
                                        else
                                        {
                                            stageHandler.displayError("Password not specified", "Password can't be blank", "Please enter a password");
                                            passwordField.requestFocus();
                                        }
                                    }
                                    else
                                    {
                                        stageHandler.displayError("Username not specified", "Username is missing", "Please enter a username");
                                        usernameField.requestFocus();
                                    }
                                }
                                else
                                {
                                    stageHandler.displayError("Position not specified", "Position is missing", "Please select a position");
                                    positionBox.show();
                                }
                            }
                            else
                            {
                                stageHandler.displayError("Phone number not specified", "Phone number is missing", "Please enter a phone number");
                                tlfField.requestFocus();
                            }
                        }
                        else
                        {
                            stageHandler.displayError("E-mail not specified", "E-mail is missing", "Please enter an e-mail address");
                            emailField.requestFocus();
                        }
                    }
                    else
                    {
                        stageHandler.displayError("Address not specified", "Address is missing", "Please enter an address");
                        addressField.requestFocus();
                    }
                }
                else
                {
                    stageHandler.displayError("Date of birth not specified", "Date of birth is missing", "Please enter a date of birth");
                    birthdayPicker.show();
                }
            }
            else
            {
                stageHandler.displayError("CPR not specified", "CPR is missing", "Please enter a CPR number");
                cprField.requestFocus();
            }
        }
        else
        {
            stageHandler.displayError("Name not specified", "Name is missing", "Please enter a name");
            nameField.requestFocus();
        }
    }

    private void clearStaffFields()
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

    public void onAddMotorhomeBtnPressed(ActionEvent actionEvent)
    {
        if (!modelField.getText().equals(""))
        {
            if (!brandField.getText().equals(""))
            {
                if (!sizeBox.getSelectionModel().isEmpty())
                {
                    if (!priceField.getText().equals(""))
                    {
                        float number = Float.parseFloat(priceField.getText());
                        number = CalculationHandler.clamp(number, 0, 999999);

                        Motorhome motorhome = new Motorhome(modelField.getText(), brandField.getText(), sizeBox.getValue(), false, false, number);
                        Motorhome.allMotorhomes.add(motorhome);

                        stageHandler.displayInfo("Success", "Motorhome successfully added to the system", "Press OK to continue");
                        motorhomeTable.getItems().setAll(Motorhome.allMotorhomes);
                        clearMotorhomeFields();
                    }
                    else
                    {
                        stageHandler.displayError("Price not specified", "Price per day is missing", "Please enter a price");
                        priceField.requestFocus();
                    }
                }
                else
                {
                    stageHandler.displayError("Size not specified", "Size is missing", "Please select a size");
                    sizeBox.show();
                }
            }
            else
            {
                stageHandler.displayError("Brand not specified", "Brand name is missing", "Please enter a Brand name");
                brandField.requestFocus();
            }
        }
        else
        {
            stageHandler.displayError("Model not specified", "Model name is missing", "Please enter a Model name");
            modelField.requestFocus();
        }
    }

    private void clearMotorhomeFields()
    {
        modelField.clear();
        brandField.clear();
        sizeBox.getSelectionModel().clearSelection();
        priceField.clear();
    }

    private void setupTableColumns()
    {
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        customerCPRCol.setCellValueFactory(new PropertyValueFactory<>("cpr"));
        customerBirthdayCol.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerTlfCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        customerEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        staffNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        staffCPRCol.setCellValueFactory(new PropertyValueFactory<>("cpr"));
        staffBirthdayCol.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        staffAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        staffTlfCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        staffEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        reservationCustomerCol.setCellValueFactory(new PropertyValueFactory<>("customer"));
        reservationMotorhomeCol.setCellValueFactory(new PropertyValueFactory<>("motorhome"));
        reservationDateCol.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));
        reservationPickupCol.setCellValueFactory(new PropertyValueFactory<>("pickupDate"));
        reservationDropoffCol.setCellValueFactory(new PropertyValueFactory<>("dropoffDate"));

        motorhomeIDCol.setCellValueFactory(new PropertyValueFactory<>("motorhomeID"));
        motorhomeModelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
        motorhomeBrandCol.setCellValueFactory(new PropertyValueFactory<>("brand"));
        motorhomeSizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
        motorhomePriceCol.setCellValueFactory(new PropertyValueFactory<>("pricePerDay"));
        motorhomeStatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void forceNumericValues()
    {
        tlfField.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (!newValue.matches("\\d*"))
                {
                    tlfField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        priceField.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (!newValue.matches("\\d*"))
                {
                    priceField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        setupTableColumns();
        staffTable.getItems().setAll(Employee.allEmployees);
        customerTable.getItems().setAll(Customer.allCustomers);
        reservationTable.getItems().setAll(Reservation.allReservations);
        motorhomeTable.getItems().setAll(Motorhome.allMotorhomes);
        positionBox.setItems(positions);
        sizeBox.setItems(sizes);
        forceNumericValues();
    }
}
