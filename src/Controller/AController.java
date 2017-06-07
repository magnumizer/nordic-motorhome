package Controller;//Magnus Svendsen DAT16i

import DB.DBWrapper;
import Handler.CalculationHandler;
import Handler.SearchHandler;
import Handler.StageHandler;
import Model.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AController implements Initializable
{
    ObservableList<String> positions =
            FXCollections.observableArrayList(
                    "Admin",
                    "Auto Mechanic",
                    "Book Keeper",
                    "Cleaning Staff",
                    "Sales Assistant"
            );

    ObservableList<String> sizes =
            FXCollections.observableArrayList(
                    "Small",
                    "Medium",
                    "Large"
            );

    ObservableList<String> quantities =
            FXCollections.observableArrayList(
                    "0",
                    "1",
                    "2",
                    "3",
                    "4",
                    "5",
                    "6",
                    "7",
                    "8",
                    "9",
                    "10"
            );

    //region FXML
    @FXML
    private TextField staffSearchField;
    @FXML
    private TextField customerSearchField;
    @FXML
    private TextField reservationSearchField;
    @FXML
    private TextField motorhomeSearchField;
    @FXML
    private TextField accessorySearchField;

    @FXML
    private TabPane mainTabPane;
    @FXML
    private TabPane overviewTabs;

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
    private TableColumn<Employee, String> staffPositionCol;

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
    private TableView<Reservation> reservationTable;
    @FXML
    private TableColumn<Reservation, String> reservationIDCol;
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
    private TableColumn<Reservation, Float> reservationPriceCol;

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
    private TableView<Accessory> accessoryTable;
    @FXML
    private TableColumn<Accessory, String> accessoryTypeCol;
    @FXML
    private TableColumn<Accessory, Float> accessoryPriceCol;
    @FXML
    private TableColumn<Accessory, Integer> accessoryQuantityCol;

    //staff
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

    //motorhome
    @FXML
    private TextField modelField;
    @FXML
    private TextField brandField;
    @FXML
    private ComboBox<String> sizeBox;
    @FXML
    private TextField priceField;

    //edit
    @FXML
    private AnchorPane editCustomerPane;
    @FXML
    private TextField editCustomerNameField;
    @FXML
    private TextField editCustomerCPRField;
    @FXML
    private DatePicker editCustomerBirthdayPicker;
    @FXML
    private TextField editCustomerAddressField;
    @FXML
    private TextField editCustomerTlfField;
    @FXML
    private TextField editCustomerEmailField;

    @FXML
    private AnchorPane editStaffPane;
    @FXML
    private TextField editStaffNameField;
    @FXML
    private TextField editStaffCPRField;
    @FXML
    private DatePicker editStaffBirthdayPicker;
    @FXML
    private TextField editStaffAddressField;
    @FXML
    private TextField editStaffTlfField;
    @FXML
    private TextField editStaffEmailField;
    @FXML
    private TextField editStaffUserField;
    @FXML
    private TextField editStaffPasswordField;
    @FXML
    private TextField editStaffConfirmField;

    @FXML
    private AnchorPane editReservationPane;
    @FXML
    private ComboBox<Motorhome> editReservationMotorhomeBox;
    @FXML
    private ComboBox<Customer> editReservationCustomerBox;
    @FXML
    private DatePicker editReservationPickup;
    @FXML
    private DatePicker editReservationDropoff;
    @FXML
    private TextField editReservationAddress;
    @FXML
    private ComboBox<String> bikeRackBox;
    @FXML
    private ComboBox<String> bedLinenBox;
    @FXML
    private ComboBox<String> childSeatBox;
    @FXML
    private ComboBox<String> picnicTableBox;
    @FXML
    private ComboBox<String> chairBox;

    @FXML
    private AnchorPane editMotorhomePane;
    @FXML
    private TextField editMotorhomeModel;
    @FXML
    private TextField editMotorhomeBrand;
    @FXML
    private ComboBox<String> editMotorhomeSizeBox;
    @FXML
    private TextField editMotorhomePrice;
    @FXML
    private ComboBox<String> editMotorhomeStatusBox;

    @FXML
    private AnchorPane editAccessoryPane;
    @FXML
    private TextField editAccessoryType;
    @FXML
    private TextField editAccessoryPrice;
    @FXML
    private TextField editAccessoryAmount;
    //endregion

    private DBWrapper database = new DBWrapper();
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
                                                        switch (positionBox.getValue())
                                                        {
                                                            case "Admin":
                                                                Admin admin = new Admin(nameField.getText(), cprField.getText(), birthdayPicker.getValue(), addressField.getText(), tlfField.getText(), emailField.getText(), usernameField.getText(), passwordField.getText());
                                                                Employee.allEmployees.add(admin);
                                                                database.updateEmployee(admin);
                                                                stageHandler.displayInfo("Success", "Admin successfully added to the system", "Press OK to continue");
                                                                break;
                                                            case "Auto Mechanic":
                                                                AutoMechanic autoMechanic = new AutoMechanic(nameField.getText(), cprField.getText(), birthdayPicker.getValue(), addressField.getText(), tlfField.getText(), emailField.getText(), usernameField.getText(), passwordField.getText());
                                                                Employee.allEmployees.add(autoMechanic);
                                                                database.updateEmployee(autoMechanic);
                                                                stageHandler.displayInfo("Success", "Auto Mechanic successfully added to the system", "Press OK to continue");
                                                                break;
                                                            case "Book Keeper":
                                                                BookKeeper bookKeeper = new BookKeeper(nameField.getText(), cprField.getText(), birthdayPicker.getValue(), addressField.getText(), tlfField.getText(), emailField.getText(), usernameField.getText(), passwordField.getText());
                                                                Employee.allEmployees.add(bookKeeper);
                                                                database.updateEmployee(bookKeeper);
                                                                stageHandler.displayInfo("Success", "Book Keeper successfully added to the system", "Press OK to continue");
                                                                break;
                                                            case "Cleaning Staff":
                                                                CleaningStaff cleaningStaff = new CleaningStaff(nameField.getText(), cprField.getText(), birthdayPicker.getValue(), addressField.getText(), tlfField.getText(), emailField.getText(), usernameField.getText(), passwordField.getText());
                                                                Employee.allEmployees.add(cleaningStaff);
                                                                database.updateEmployee(cleaningStaff);
                                                                stageHandler.displayInfo("Success", "Cleaning Staff successfully added to the system", "Press OK to continue");
                                                                break;
                                                            case "Sales Assistant":
                                                                SalesAssistant salesAssistant = new SalesAssistant(nameField.getText(), cprField.getText(), birthdayPicker.getValue(), addressField.getText(), tlfField.getText(), emailField.getText(), usernameField.getText(), passwordField.getText());
                                                                Employee.allEmployees.add(salesAssistant);
                                                                database.updateEmployee(salesAssistant);
                                                                stageHandler.displayInfo("Success", "Sales Assistant successfully added to the system", "Press OK to continue");
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
        confirmLabel.setStyle("-fx-text-fill: black");
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

                        Motorhome motorhome = new Motorhome(modelField.getText(), brandField.getText(), sizeBox.getValue(), number);
                        Motorhome.allMotorhomes.add(motorhome);
                        database.updateMotorhome(motorhome);

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

    public void onEditBtnPressed(ActionEvent actionEvent)
    {
        int selectedTabIndex = overviewTabs.getSelectionModel().getSelectedIndex();

        switch (selectedTabIndex)
        {
            case 0:
                //open staff edit panel
                if (!staffTable.getSelectionModel().isEmpty())
                {
                    mainTabPane.setDisable(true);

                    if (!editStaffPane.isVisible())
                    {
                        Employee employee = staffTable.getSelectionModel().getSelectedItem();

                        editStaffNameField.setText(employee.getName());
                        editStaffCPRField.setText(employee.getCpr());
                        editStaffBirthdayPicker.setValue(employee.getDateOfBirth());
                        editStaffAddressField.setText(employee.getAddress());
                        editStaffTlfField.setText(employee.getPhoneNumber() + "");
                        editStaffEmailField.setText(employee.getEmail());
                        editStaffUserField.setText(employee.getUsername());
                        editStaffPasswordField.setText(employee.getPassword());
                        editStaffConfirmField.setText(employee.getPassword());

                        editStaffPane.setDisable(false);
                        editStaffPane.setVisible(true);
                    }
                }
                else
                {
                    stageHandler.displayError("Selection error", "Employee not selected", "Please select an Employee you want to edit");
                }
                break;
            case 1:
                //open customer edit panel
                if (!customerTable.getSelectionModel().isEmpty())
                {
                    mainTabPane.setDisable(true);

                    if (!editCustomerPane.isVisible())
                    {
                        Customer customer = customerTable.getSelectionModel().getSelectedItem();

                        editCustomerNameField.setText(customer.getName());
                        editCustomerCPRField.setText(customer.getCpr());
                        editCustomerBirthdayPicker.setValue(customer.getDateOfBirth());
                        editCustomerAddressField.setText(customer.getAddress());
                        editCustomerTlfField.setText(customer.getPhoneNumber() + "");
                        editCustomerEmailField.setText(customer.getEmail());

                        editCustomerPane.setDisable(false);
                        editCustomerPane.setVisible(true);
                    }
                }
                else
                {
                    stageHandler.displayError("Selection error", "Customer not selected", "Please select a Customer you want to edit");
                }
                break;
            case 2:
                //open reservation edit panel
                if (!reservationTable.getSelectionModel().isEmpty())
                {
                    mainTabPane.setDisable(true);

                    if (!editReservationPane.isVisible())
                    {
                        Reservation reservation = reservationTable.getSelectionModel().getSelectedItem();

                        ObservableList<Motorhome> motorhomes = FXCollections.observableArrayList(Motorhome.allMotorhomes);
                        editReservationMotorhomeBox.setItems(motorhomes);

                        ObservableList<Customer> customers = FXCollections.observableArrayList(Customer.allCustomers);
                        editReservationCustomerBox.setItems(customers);

                        int motorhomeIndex = 0;

                        for (int i = 0; i < Motorhome.allMotorhomes.size(); i++)
                        {
                            if (reservation.getMotorhome() == Motorhome.allMotorhomes.get(i))
                            {
                                motorhomeIndex = i;
                            }
                        }

                        int customerIndex = 0;

                        for (int i = 0; i < Customer.allCustomers.size(); i++)
                        {
                            if (reservation.getCustomer() == Customer.allCustomers.get(i))
                            {
                                customerIndex = i;
                            }
                        }

                        editReservationMotorhomeBox.getSelectionModel().select(motorhomeIndex);
                        editReservationCustomerBox.getSelectionModel().select(customerIndex);
                        editReservationPickup.setValue(reservation.getPickupDate());
                        editReservationDropoff.setValue(reservation.getDropoffDate());
                        editReservationAddress.setText(reservation.getDropoffAddress());

                        HashMap<Accessory, Integer> accessories = reservation.getAccessories();

                        bikeRackBox.setValue(String.valueOf(accessories.get(Accessory.allAccessories.get("Bike Rack"))));
                        bedLinenBox.setValue(String.valueOf(accessories.get(Accessory.allAccessories.get("Bed Linen"))));
                        childSeatBox.setValue(String.valueOf(accessories.get(Accessory.allAccessories.get("Child Seat"))));
                        picnicTableBox.setValue(String.valueOf(accessories.get(Accessory.allAccessories.get("Picnic Table"))));
                        chairBox.setValue(String.valueOf(accessories.get(Accessory.allAccessories.get("Chair"))));

                        editReservationPane.setDisable(false);
                        editReservationPane.setVisible(true);
                    }
                }
                else
                {
                    stageHandler.displayError("Selection error", "Reservation not selected", "Please select a Reservation you want to edit");
                }
                break;
            case 3:
                //open motorhome edit panel
                if (!motorhomeTable.getSelectionModel().isEmpty())
                {
                    mainTabPane.setDisable(true);

                    if (!editMotorhomePane.isVisible())
                    {
                        Motorhome motorhome = motorhomeTable.getSelectionModel().getSelectedItem();

                        editMotorhomeModel.setText(motorhome.getModel());
                        editMotorhomeBrand.setText(motorhome.getBrand());
                        editMotorhomeSizeBox.setItems(sizes);
                        editMotorhomeSizeBox.getSelectionModel().select(motorhome.getSize());
                        editMotorhomePrice.setText((motorhome.getPricePerDay() / 10) + "");

                        ObservableList<String> statusOptions =
                                FXCollections.observableArrayList(
                                        "Available",
                                        "Rented",
                                        "Out of service"
                                );
                        editMotorhomeStatusBox.setItems(statusOptions);
                        editMotorhomeStatusBox.getSelectionModel().select(motorhome.getStatus());

                        editMotorhomePane.setDisable(false);
                        editMotorhomePane.setVisible(true);
                    }
                }
                else
                {
                    stageHandler.displayError("Selection error", "Motorhome not selected", "Please select a Motorhome you want to edit");
                }
                break;
            case 4:
                //open accessory edit panel
                if (!accessoryTable.getSelectionModel().isEmpty())
                {
                    mainTabPane.setDisable(true);

                    if (!editAccessoryPane.isVisible())
                    {
                        Accessory accessory = accessoryTable.getSelectionModel().getSelectedItem();

                        editAccessoryType.setText(accessory.getType());
                        editAccessoryPrice.setText((accessory.getPrice() / 10) + "");
                        editAccessoryAmount.setText(accessory.getQuantity() + "");

                        editAccessoryPane.setDisable(false);
                        editAccessoryPane.setVisible(true);
                    }
                }
                else
                {
                    stageHandler.displayError("Selection error", "Accessory not selected", "Please select an Accessory you want to edit");
                }
                break;
            default:
                System.out.println("Weird error. Tab not found. Fix please.");
                break;
        }
    }

    public void onEditSaveBtnPressed(ActionEvent actionEvent)
    {
        int selectedIndex = overviewTabs.getSelectionModel().getSelectedIndex();

        switch (selectedIndex)
        {
            case 0:
                editStaff();
                break;
            case 1:
                editCustomer();
                break;
            case 2:
                editReservation();
                break;
            case 3:
                editMotorhome();
                break;
            case 4:
                editAccessory();
                break;
            default:
                System.out.println("Weird error. Tab not found. Fix please.");
                break;
        }
    }

    private void editStaff()
    {
        if (!editStaffNameField.getText().equals(""))
        {
            if (!editStaffCPRField.getText().equals(""))
            {
                if (editStaffBirthdayPicker.getValue() != null)
                {
                    if (!editStaffAddressField.getText().equals(""))
                    {
                        if (!editStaffTlfField.getText().equals(""))
                        {
                            if (!editStaffEmailField.getText().equals(""))
                            {
                                if (!editStaffUserField.getText().equals(""))
                                {
                                    if (!editStaffPasswordField.getText().equals(""))
                                    {
                                        if (editStaffPasswordField.getText().equals(editStaffConfirmField.getText()))
                                        {
                                            Employee employee = staffTable.getSelectionModel().getSelectedItem();
                                            employee.setName(editStaffNameField.getText());
                                            employee.setCpr(editStaffCPRField.getText());
                                            employee.setDateOfBirth(editStaffBirthdayPicker.getValue());
                                            employee.setAddress(editStaffAddressField.getText());
                                            employee.setPhoneNumber(editStaffTlfField.getText());
                                            employee.setEmail(editStaffEmailField.getText());
                                            employee.setUsername(editStaffUserField.getText());
                                            employee.setPassword(editStaffPasswordField.getText());

                                            database.updateEmployee(employee);
                                            stageHandler.displayInfo("Success", "Staff details have been changed", "Press OK to continue");
                                            staffTable.getItems().setAll(Employee.allEmployees);
                                            closeEditPanel();
                                        }
                                        else
                                        {
                                            stageHandler.displayError("Password not confirmed", "Ensure password confirmation is identical to password", "Please confirm password");
                                            editStaffConfirmField.requestFocus();
                                        }
                                    }
                                    else
                                    {
                                        stageHandler.displayError("Password error", "Password is missing", "Please enter a password");
                                        editStaffPasswordField.requestFocus();
                                    }
                                }
                                else
                                {
                                    stageHandler.displayError("Username not specified", "Username is missing", "Please enter a username");
                                    editStaffUserField.requestFocus();
                                }
                            }
                            else
                            {
                                stageHandler.displayError("E-mail not specified", "E-mail is missing", "Please enter an e-mail address");
                                editStaffEmailField.requestFocus();
                            }
                        }
                        else
                        {
                            stageHandler.displayError("Phone number not specified", "Phone number is missing", "Please enter a phone number");
                            editStaffTlfField.requestFocus();
                        }
                    }
                    else
                    {
                        stageHandler.displayError("Address not specified", "Address is missing", "Please enter an address");
                        editStaffAddressField.requestFocus();
                    }
                }
                else
                {
                    stageHandler.displayError("Date of birth not specified", "Date of birth is missing", "Please enter a date of birth");
                    editStaffBirthdayPicker.show();
                }
            }
            else
            {
                stageHandler.displayError("CPR not specified", "CPR is missing", "Please enter a CPR number");
                editStaffCPRField.requestFocus();
            }
        }
        else
        {
            stageHandler.displayError("Name not specified", "Name is missing", "Please enter a name");
            editStaffNameField.requestFocus();
        }
    }

    private void editCustomer()
    {
        if (!editCustomerNameField.getText().equals(""))
        {
            if (!editCustomerCPRField.getText().equals(""))
            {
                if (editCustomerBirthdayPicker.getValue() != null)
                {
                    if (!editCustomerAddressField.getText().equals(""))
                    {
                        if (!editCustomerTlfField.getText().equals(""))
                        {
                            if (!editCustomerEmailField.getText().equals(""))
                            {
                                Customer customer = customerTable.getSelectionModel().getSelectedItem();
                                customer.setName(editCustomerNameField.getText());
                                customer.setCpr(editCustomerCPRField.getText());
                                customer.setDateOfBirth(editCustomerBirthdayPicker.getValue());
                                customer.setAddress(editCustomerAddressField.getText());
                                customer.setPhoneNumber(editCustomerTlfField.getText());
                                customer.setEmail(editCustomerEmailField.getText());

                                database.updateCustomer(customer);
                                stageHandler.displayInfo("Success", "Customer details have been changed", "Press OK to continue");
                                customerTable.getItems().setAll(Customer.allCustomers);
                                closeEditPanel();
                            }
                            else
                            {
                                stageHandler.displayError("E-mail not specified", "E-mail is missing", "Please enter an e-mail address");
                                editCustomerEmailField.requestFocus();
                            }
                        }
                        else
                        {
                            stageHandler.displayError("Phone number not specified", "Phone number is missing", "Please enter a phone number");
                            editCustomerTlfField.requestFocus();
                        }
                    }
                    else
                    {
                        stageHandler.displayError("Address not specified", "Address is missing", "Please enter an address");
                        editCustomerAddressField.requestFocus();
                    }
                }
                else
                {
                    stageHandler.displayError("Date of birth not specified", "Date of birth is missing", "Please enter a date of birth");
                    editCustomerBirthdayPicker.show();
                }
            }
            else
            {
                stageHandler.displayError("CPR not specified", "CPR is missing", "Please enter a CPR number");
                editCustomerCPRField.requestFocus();
            }
        }
        else
        {
            stageHandler.displayError("Name not specified", "Name is missing", "Please enter a name");
            editCustomerNameField.requestFocus();
        }
    }

    private void editReservation()
    {
        if (editReservationPickup.getValue() != null)
        {
            if (editReservationDropoff.getValue() != null)
            {
                Reservation reservation = reservationTable.getSelectionModel().getSelectedItem();
                reservation.setMotorhome(editReservationMotorhomeBox.getValue());
                reservation.setCustomer(editReservationCustomerBox.getValue());
                reservation.setPickupDate(editReservationPickup.getValue());
                reservation.setDropoffDate(editReservationDropoff.getValue());
                reservation.setDropoffAddress(editReservationAddress.getText());

                addAccessoriesToReservation(reservation);

                database.updateReservation(reservation);
                stageHandler.displayInfo("Success", "Reservation details have been changed", "Press OK to continue");
                reservationTable.getItems().setAll(Reservation.allReservations);
                accessoryTable.getItems().setAll(Accessory.allAccessories.values());
                closeEditPanel();
            }
            else
            {
                stageHandler.displayError("Drop off date not specified", "Drop off date is missing", "Please enter a drop off date");
                editReservationDropoff.show();
            }
        }
        else
        {
            stageHandler.displayError("Pick up date not specified", "Pick up date is missing", "Please enter a pick up date");
            editReservationPickup.show();
        }
    }

    private void addAccessoriesToReservation(Reservation reservation)
    {
        HashMap<Accessory, Integer> currentAccessories = reservation.getAccessories();
        HashMap<Accessory, Integer> accessories = new HashMap<>();
        int bikeRackQuantity = Integer.parseInt(bikeRackBox.getValue());
        int bedLinenQuantity = Integer.parseInt(bedLinenBox.getValue());
        int childSeatQuantity = Integer.parseInt(childSeatBox.getValue());
        int picnicTableQuantity = Integer.parseInt(picnicTableBox.getValue());
        int chairQuantity = Integer.parseInt(chairBox.getValue());

        if (bikeRackQuantity >= 0)
        {
            Accessory bikeRack = Accessory.allAccessories.get("Bike Rack");
            int quantity = bikeRack.getQuantity() - bikeRackQuantity + currentAccessories.get(bikeRack);
            bikeRack.setQuantity(quantity);
            accessories.put(bikeRack, bikeRackQuantity);
            database.updateAccessory(bikeRack);
        }
        if (bedLinenQuantity >= 0)
        {
            Accessory bedLinen = Accessory.allAccessories.get("Bed Linen");
            int quantity = bedLinen.getQuantity() - bedLinenQuantity + currentAccessories.get(bedLinen);
            bedLinen.setQuantity(quantity);
            accessories.put(bedLinen, bedLinenQuantity);
            database.updateAccessory(bedLinen);
        }
        if (childSeatQuantity >= 0)
        {
            Accessory childSeat = Accessory.allAccessories.get("Child Seat");
            int quantity = childSeat.getQuantity() - childSeatQuantity + currentAccessories.get(childSeat);
            childSeat.setQuantity(quantity);
            accessories.put(childSeat, childSeatQuantity);
            database.updateAccessory(childSeat);
        }
        if (picnicTableQuantity >= 0)
        {
            Accessory picnicTable = Accessory.allAccessories.get("Picnic Table");
            int quantity = picnicTable.getQuantity() - picnicTableQuantity + currentAccessories.get(picnicTable);
            picnicTable.setQuantity(quantity);
            accessories.put(picnicTable, picnicTableQuantity);
            database.updateAccessory(picnicTable);
        }
        if (chairQuantity >= 0)
        {
            Accessory chair = Accessory.allAccessories.get("Chair");
            int quantity = chair.getQuantity() - chairQuantity + currentAccessories.get(chair);
            chair.setQuantity(quantity);
            accessories.put(chair, chairQuantity);
            database.updateAccessory(chair);
        }

        reservation.setAccessories(accessories);
    }

    private void editMotorhome()
    {
        if (!editMotorhomeModel.getText().equals(""))
        {
            if (!editMotorhomeBrand.getText().equals(""))
            {
                if (!editMotorhomePrice.getText().equals(""))
                {
                    float number = Float.parseFloat(editMotorhomePrice.getText());

                    Motorhome motorhome = motorhomeTable.getSelectionModel().getSelectedItem();
                    motorhome.setModel(editMotorhomeModel.getText());
                    motorhome.setBrand(editMotorhomeBrand.getText());
                    motorhome.setSize(editMotorhomeSizeBox.getValue());
                    motorhome.setPricePerDay(number);
                    motorhome.setStatus(editMotorhomeStatusBox.getValue());

                    database.updateMotorhome(motorhome);
                    stageHandler.displayInfo("Success", "Motorhome details have been changed", "Press OK to continue");
                    motorhomeTable.getItems().setAll(Motorhome.allMotorhomes);
                    closeEditPanel();
                }
                else
                {
                    stageHandler.displayError("Price not specified", "Price is missing", "Please enter a price per day in kroner");
                    editMotorhomePrice.requestFocus();
                }
            }
            else
            {
                stageHandler.displayError("Brand not specified", "Brand is missing", "Please enter a Brand");
                editMotorhomeBrand.requestFocus();
            }
        }
        else
        {
            stageHandler.displayError("Model not specified", "Model is missing", "Please enter a Model");
            editMotorhomeModel.requestFocus();
        }
    }

    private void editAccessory()
    {
        if (!editAccessoryPrice.getText().equals(""))
        {
            if (!editAccessoryAmount.getText().equals(""))
            {
                String selectedAccessory = accessoryTable.getSelectionModel().getSelectedItem().getType();
                float price = Float.parseFloat(editAccessoryPrice.getText());
                int amount = Integer.parseInt(editAccessoryAmount.getText());

                Accessory accessory = Accessory.allAccessories.get(selectedAccessory);
                accessory.setPrice(price);
                accessory.setQuantity(amount);

                database.updateAccessory(accessory);
                stageHandler.displayInfo("Success", "Accessory details have been changed", "Press OK to continue");
                accessoryTable.getItems().setAll(Accessory.allAccessories.values());
                closeEditPanel();
            }
            else
            {
                stageHandler.displayError("Item amount not specified", "Item amount is missing", "Please enter an Item amount");
                editAccessoryAmount.requestFocus();
            }
        }
        else
        {
            stageHandler.displayError("Price not specified", "Price is missing", "Please enter a Price");
            editAccessoryPrice.requestFocus();
        }
    }

    public void onEditCancelBtnPressed(ActionEvent actionEvent)
    {
        closeEditPanel();
    }

    private void closeEditPanel()
    {
        mainTabPane.setDisable(false);

        if (editCustomerPane.isVisible())
        {
            editCustomerPane.setVisible(false);
            editCustomerPane.setDisable(true);
        }
        else if (editStaffPane.isVisible())
        {
            editStaffPane.setVisible(false);
            editStaffPane.setDisable(true);
        }
        else if (editReservationPane.isVisible())
        {
            editReservationPane.setVisible(false);
            editReservationPane.setDisable(true);
        }
        else if (editMotorhomePane.isVisible())
        {
            editMotorhomePane.setVisible(false);
            editMotorhomePane.setDisable(true);
        }
        else if (editAccessoryPane.isVisible())
        {
            editAccessoryPane.setVisible(false);
            editAccessoryPane.setDisable(true);
        }
    }

    private void setupTableColumns()
    {
        staffNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        staffCPRCol.setCellValueFactory(new PropertyValueFactory<>("cpr"));
        staffBirthdayCol.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        staffAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        staffTlfCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        staffEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        staffPositionCol.setCellValueFactory(new PropertyValueFactory<>("position"));

        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        customerCPRCol.setCellValueFactory(new PropertyValueFactory<>("cpr"));
        customerBirthdayCol.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerTlfCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        customerEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        reservationIDCol.setCellValueFactory(new PropertyValueFactory<>("reservationID"));
        reservationCustomerCol.setCellValueFactory(new PropertyValueFactory<>("customer"));
        reservationMotorhomeCol.setCellValueFactory(new PropertyValueFactory<>("motorhome"));
        reservationDateCol.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));
        reservationPickupCol.setCellValueFactory(new PropertyValueFactory<>("pickupDate"));
        reservationDropoffCol.setCellValueFactory(new PropertyValueFactory<>("dropoffDate"));
        reservationPriceCol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        motorhomeIDCol.setCellValueFactory(new PropertyValueFactory<>("motorhomeID"));
        motorhomeModelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
        motorhomeBrandCol.setCellValueFactory(new PropertyValueFactory<>("brand"));
        motorhomeSizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
        motorhomePriceCol.setCellValueFactory(new PropertyValueFactory<>("pricePerDay"));
        motorhomeStatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        accessoryTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        accessoryPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        accessoryQuantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    private void updateTable(TableView table, ArrayList list)
    {
        table.getItems().setAll(list);
    }

    private void resetTables()
    {
        staffTable.getItems().setAll(Employee.allEmployees);
        customerTable.getItems().setAll(Customer.allCustomers);
        reservationTable.getItems().setAll(Reservation.allReservations);

        motorhomeTable.getItems().setAll(Motorhome.allMotorhomes);
        accessoryTable.getItems().setAll(Accessory.allAccessories.values());
    }

    public void onPickupDateSelected(ActionEvent actionEvent)
    {
        if (editReservationPickup.getValue() != null && editReservationDropoff.getValue() != null)
        {
            if (!editReservationPickup.getValue().isBefore(editReservationDropoff.getValue()))
            {
                editReservationPickup.setValue(null);
                stageHandler.displayError("Invalid date", "Pick up date must be BEFORE drop off date", "Please select a valid date");
                Platform.runLater(() -> {
                    editReservationPickup.show();
                });
            }
        }
    }

    public void onDropoffDateSelected(ActionEvent actionEvent)
    {
        if (editReservationPickup.getValue() != null && editReservationDropoff.getValue() != null)
        {
            if (!editReservationDropoff.getValue().isAfter(editReservationPickup.getValue()))
            {
                editReservationDropoff.setValue(null);
                stageHandler.displayError("Invalid date", "Drop off date must be AFTER pick up date", "Please select a valid date");
                Platform.runLater(() -> {
                    editReservationDropoff.show();
                });
            }
        }
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

        confirmField.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (newValue.matches(passwordField.getText()))
                {
                    confirmLabel.setStyle("-fx-text-fill: green");
                }
                else
                {
                    confirmLabel.setStyle("-fx-text-fill: black");
                }
            }
        });

        editCustomerTlfField.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (!newValue.matches("\\d*"))
                {
                    editCustomerTlfField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        editStaffTlfField.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (!newValue.matches("\\d*"))
                {
                    editStaffTlfField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        editMotorhomePrice.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (!newValue.matches("\\d*"))
                {
                    editMotorhomePrice.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        editAccessoryPrice.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (!newValue.matches("\\d*"))
                {
                    editAccessoryPrice.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        editAccessoryAmount.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (!newValue.matches("\\d*"))
                {
                    editAccessoryAmount.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });


        bikeRackBox.getEditor().textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (!newValue.matches("\\d*"))
                {
                    bikeRackBox.getEditor().setText(newValue.replaceAll("[^\\d]", ""));
                }
                else
                {
                    int inputVal = Integer.parseInt(newValue);
                    int limitVal = 99;

                    if (inputVal > limitVal)
                    {
                        bikeRackBox.getEditor().setText(limitVal + "");
                        bikeRackBox.setValue(limitVal + "");
                    }
                    else
                    {
                        bikeRackBox.setValue(newValue);
                    }
                }
            }
        });

        bedLinenBox.getEditor().textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (!newValue.matches("\\d*"))
                {
                    bedLinenBox.getEditor().setText(newValue.replaceAll("[^\\d]", ""));
                }
                else
                {
                    int inputVal = Integer.parseInt(newValue);
                    int limitVal = 99;

                    if (inputVal > limitVal)
                    {
                        bedLinenBox.getEditor().setText(limitVal + "");
                        bedLinenBox.setValue(limitVal + "");
                    }
                    else
                    {
                        bedLinenBox.setValue(newValue);
                    }
                }
            }
        });

        childSeatBox.getEditor().textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (!newValue.matches("\\d*"))
                {
                    childSeatBox.getEditor().setText(newValue.replaceAll("[^\\d]", ""));
                }
                else
                {
                    int inputVal = Integer.parseInt(newValue);
                    int limitVal = 99;

                    if (inputVal > limitVal)
                    {
                        childSeatBox.getEditor().setText(limitVal + "");
                        childSeatBox.setValue(limitVal + "");
                    }
                    else
                    {
                        childSeatBox.setValue(newValue);
                    }
                }
            }
        });

        picnicTableBox.getEditor().textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (!newValue.matches("\\d*"))
                {
                    picnicTableBox.getEditor().setText(newValue.replaceAll("[^\\d]", ""));
                }
                else
                {
                    int inputVal = Integer.parseInt(newValue);
                    int limitVal = 99;

                    if (inputVal > limitVal)
                    {
                        picnicTableBox.getEditor().setText(limitVal + "");
                        picnicTableBox.setValue(limitVal + "");
                    }
                    else
                    {
                        picnicTableBox.setValue(newValue);
                    }
                }
            }
        });

        chairBox.getEditor().textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (!newValue.matches("\\d*"))
                {
                    chairBox.getEditor().setText(newValue.replaceAll("[^\\d]", ""));
                }
                else
                {
                    int inputVal = Integer.parseInt(newValue);
                    int limitVal = 99;

                    if (inputVal > limitVal)
                    {
                        chairBox.getEditor().setText(limitVal + "");
                        chairBox.setValue(limitVal + "");
                    }
                    else
                    {
                        chairBox.setValue(newValue);
                    }
                }
            }
        });
    }

    private void addSearchListeners()
    {
        staffSearchField.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (newValue.matches(""))
                {
                    updateTable(staffTable, Employee.allEmployees);
                }
                else
                {
                    updateTable(staffTable, SearchHandler.findEmployee(staffSearchField.getText()));
                }
            }
        });

        customerSearchField.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (newValue.matches(""))
                {
                    updateTable(customerTable, Customer.allCustomers);
                }
                else
                {
                    updateTable(customerTable, SearchHandler.findCustomer(customerSearchField.getText()));
                }
            }
        });

        reservationSearchField.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (newValue.matches(""))
                {
                    updateTable(reservationTable, Reservation.allReservations);
                }
                else
                {
                    updateTable(reservationTable, SearchHandler.findReservation(reservationSearchField.getText()));
                }
            }
        });

        motorhomeSearchField.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (newValue.matches(""))
                {
                    updateTable(motorhomeTable, Motorhome.allMotorhomes);
                }
                else
                {
                    updateTable(motorhomeTable, SearchHandler.findMotorhome(motorhomeSearchField.getText()));
                }
            }
        });

        accessorySearchField.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (newValue.matches(""))
                {
                    ArrayList<Accessory> accessories = new ArrayList<>();
                    accessories.addAll(Accessory.allAccessories.values());
                    updateTable(accessoryTable, accessories);
                }
                else
                {
                    updateTable(accessoryTable, SearchHandler.findAccessory(accessorySearchField.getText()));
                }
            }
        });
    }


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        setupTableColumns();
        resetTables();

        positionBox.setItems(positions);
        sizeBox.setItems(sizes);
        bikeRackBox.setItems(quantities);
        bedLinenBox.setItems(quantities);
        childSeatBox.setItems(quantities);
        picnicTableBox.setItems(quantities);
        chairBox.setItems(quantities);
        forceNumericValues();
        addSearchListeners();
    }
}
