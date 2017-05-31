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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class SAController implements Initializable
{
    ObservableList<String> points =
            FXCollections.observableArrayList(
                    "Default",
                    "Custom"
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
    private TabPane mainTabPane;

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
    private Button selectCustomerBtn;
    @FXML
    private Button selectMotorhomeBtn;
    @FXML
    private DatePicker reservationDate;
    @FXML
    private DatePicker pickupDate;
    @FXML
    private DatePicker dropoffDate;
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
    private ComboBox<String> dropoffBox;
    @FXML
    private TextField dropoffAddressField;
    @FXML
    private CheckBox lowSeasonCheck;
    @FXML
    private CheckBox midSeasonCheck;
    @FXML
    private CheckBox highSeasonCheck;
    @FXML
    private TextField priceField;

    @FXML
    private TextField fineField;
    @FXML
    private CheckBox cashCheck;
    @FXML
    private CheckBox creditCheck;

    @FXML
    private AnchorPane findCustomerPane;
    @FXML
    private TextField findCustomerSearchField;
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
    private AnchorPane findMotorhomePane;
    @FXML
    private TextField findMotorhomeSearchField;
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
    private TextField overviewSearchField;

    @FXML
    private TableView<Rental> overviewRentalTable;
    @FXML
    private TableColumn<Rental, String> overviewMotorhomeCol;
    @FXML
    private TableColumn<Rental, String> overviewCustomerCol;
    @FXML
    private TableColumn<Rental, String> overviewDateCol;
    @FXML
    private TableColumn<Rental, String> overviewPriceCol;
    @FXML
    private TableColumn<Rental, String> overviewStatusCol;

    @FXML
    private TextField checkoutSearchField;

    @FXML
    private TableView<Rental> checkoutRentalTable;
    @FXML
    private TableColumn<Rental, String> checkoutCustomerCol;
    @FXML
    private TableColumn<Rental, String> checkoutMotorhomeCol;
    @FXML
    private TableColumn<Rental, Integer> checkoutDaysCol;
    @FXML
    private TableColumn<Rental, String> checkoutPriceCol;
    @FXML
    private TableColumn<Rental, String> checkoutServicePriceCol;
    @FXML
    private TableColumn<Rental, String> checkoutTotalCol;

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
    private ComboBox<String> bikeRackBoxEdit;
    @FXML
    private ComboBox<String> bedLinenBoxEdit;
    @FXML
    private ComboBox<String> childSeatBoxEdit;
    @FXML
    private ComboBox<String> picnicTableBoxEdit;
    @FXML
    private ComboBox<String> chairBoxEdit;

    @FXML
    private TextField reservationSearchField;

    @FXML
    private TableView<Reservation> reservationTable;
    @FXML
    private TableColumn<Reservation, String> reservationIDCol;
    @FXML
    private TableColumn<Motorhome, String> reservationMotorhomeCol;
    @FXML
    private TableColumn<Customer, String> reservationCustomerCol;
    @FXML
    private TableColumn<Reservation, String> reservationPickupDateCol;
    @FXML
    private TableColumn<Reservation, String> reservationPriceCol;

    @FXML
    private TextField reservationScheduleSearchField;

    @FXML
    private TableView<Reservation> reservationScheduleTable;
    @FXML
    private TableColumn<Reservation, String> reservationScheduleIDCol;
    @FXML
    private TableColumn<Motorhome, String> reservationScheduleMotorhomeCol;
    @FXML
    private TableColumn<Customer, String> reservationScheduleCustomerCol;
    @FXML
    private TableColumn<Reservation, String> reservationSchedulePickupDateCol;
    @FXML
    private TableColumn<Reservation, String> reservationSchedulePriceCol;
    //endregion

    private DBWrapper database = new DBWrapper();
    private StageHandler stageHandler = new StageHandler();

    private Customer selectedCustomer = null;
    private Motorhome selectedMotorhome = null;


    public void onCheckoutBtnPressed(ActionEvent actionEvent)
    {
        if (!checkoutRentalTable.getSelectionModel().isEmpty())
        {
            Rental rental = checkoutRentalTable.getSelectionModel().getSelectedItem();
            rental.setPaidByCustomer(true);
            database.updateRental(rental);
            stageHandler.displayInfo("Success", "Rental has been paid by " + rental.getCustomerName(), "Press OK to continue");
            updateTable(checkoutRentalTable, unpaidRentals());
            updateTable(overviewRentalTable, Rental.allRentals);
        }
        else
        {
            stageHandler.displayError("Selection error", "Rental is missing", "Please select a Rental");
        }
    }

    public void onLogOutBtnPressed(ActionEvent actionEvent)
    {
        stageHandler.logOut();
    }

    public void onSaveBtnPressed(ActionEvent actionEvent)
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
                                Customer customer = new Customer(nameField.getText(), cprField.getText(), birthdayPicker.getValue(), addressField.getText(), tlfField.getText(), emailField.getText());
                                Customer.allCustomers.add(customer);
                                database.updateCustomer(customer);

                                stageHandler.displayInfo("Success", "Customer successfully added to the system", "Press OK to continue");
                                clearCustomerFields();
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

    private void clearCustomerFields()
    {
        nameField.clear();
        cprField.clear();
        birthdayPicker.setValue(null);
        addressField.clear();
        tlfField.clear();
        emailField.clear();
    }

    public void onSelectCustomerBtnPressed(ActionEvent actionEvent)
    {
        mainTabPane.setDisable(true);

        if (!findCustomerPane.isVisible())
        {
            findCustomerSearchField.setText("");
            customerTable.getItems().setAll(Customer.allCustomers);

            findCustomerPane.setDisable(false);
            findCustomerPane.setVisible(true);
        }
    }

    public void onFindCustomerOKBtnPressed(ActionEvent actionEvent)
    {
        if (!customerTable.getSelectionModel().isEmpty())
        {
            Customer customer = customerTable.getSelectionModel().getSelectedItem();
            selectedCustomer = customer;
            selectCustomerBtn.setText(customer.getName());
            closeFindPanel();
        }
        else
        {
            stageHandler.displayError("Selection error", "Customer not selected", "Please select a Customer from the list");
        }
    }

    public void onFindCustomerCancelBtnPressed(ActionEvent actionEvent)
    {
        closeFindPanel();
    }

    public void onSelectMotorhomeBtnPressed(ActionEvent actionEvent)
    {
        if(pickupDate.getValue() != null)
        {
            if(dropoffDate.getValue() != null)
            {
                mainTabPane.setDisable(true);

                if (!findMotorhomePane.isVisible())
                {
                    findMotorhomeSearchField.setText("");
                    motorhomeTable.getItems().setAll(showAvailableMotorhomes(Motorhome.allMotorhomes));

                    findMotorhomePane.setDisable(false);
                    findMotorhomePane.setVisible(true);
                }
            }
            else
            {
                stageHandler.displayError("Drop off date not specified", "Drop off date is missing", "Please enter a drop off date");
                dropoffDate.show();
            }
        }
        else
        {
            stageHandler.displayError("Pick up date not specified", "Pick up date is missing", "Please enter a pick up date");
            pickupDate.show();
        }
    }

    public void onFindMotorhomeOKBtnPressed(ActionEvent actionEvent)
    {
        if (!motorhomeTable.getSelectionModel().isEmpty())
        {
            Motorhome motorhome = motorhomeTable.getSelectionModel().getSelectedItem();
            selectedMotorhome = motorhome;
            selectMotorhomeBtn.setText(motorhome.getBrand() + " " + motorhome.getModel());
            closeFindPanel();
        }
        else
        {
            stageHandler.displayError("Selection error", "Motorhome not selected", "Please select a Motorhome from the list");
        }
    }

    public void onFindMotorhomeCancelBtnPressed(ActionEvent actionEvent)
    {
        closeFindPanel();
    }

    private void closeFindPanel()
    {
        mainTabPane.setDisable(false);

        if (findCustomerPane.isVisible())
        {
            findCustomerPane.setVisible(false);
            findCustomerPane.setDisable(true);
        }
        else if (findMotorhomePane.isVisible())
        {
            findMotorhomePane.setVisible(false);
            findMotorhomePane.setDisable(true);
        }
    }

    //Sarb Alisia-Nadia
    private ArrayList<Motorhome> showAvailableMotorhomes(ArrayList<Motorhome> motorhomeList)
    {
        ArrayList<Motorhome> motorhomes = new ArrayList<>();

        for (Motorhome motorhome : motorhomeList) {
            for (Reservation reservation : Reservation.allReservations) {

                if (motorhome.equals(reservation.getMotorhome())) {

                    if ((reservation.getPickupDate().isEqual(pickupDate.getValue())
                            && reservation.getDropoffDate().isEqual(dropoffDate.getValue())
                            || (reservation.getPickupDate().isEqual(pickupDate.getValue())
                            && reservation.getDropoffDate().isBefore(dropoffDate.getValue()))
                            || (reservation.getPickupDate().isAfter(pickupDate.getValue())
                            && reservation.getDropoffDate().isEqual(dropoffDate.getValue())))
                            || (reservation.getPickupDate().isBefore(pickupDate.getValue())
                            && reservation.getDropoffDate().isEqual(pickupDate.getValue()))
                            || (reservation.getPickupDate().isEqual(dropoffDate.getValue())
                            && reservation.getDropoffDate().isAfter(dropoffDate.getValue()))) {

                    } else {

                        if (motorhome.getStatus().equals("Available") && motorhome.getCleanStatus().equals("Clean")) {


                            motorhomes.add(motorhome);

                        }
                    }
                }
            }
        }

        return motorhomes;

    }

    public void onRegisterBtnPressed(ActionEvent actionEvent)
    {
        if (selectedCustomer != null)
        {
            if (selectedMotorhome != null)
            {
                if (reservationDate.getValue() != null)
                {
                    if (pickupDate.getValue() != null)
                    {
                        if (dropoffDate.getValue() != null)
                        {
                            if (dropoffBox.getSelectionModel().getSelectedIndex() == 0 || !dropoffAddressField.getText().equals(""))
                            {
                                if (isSeasonSelected())
                                {
                                    Reservation reservation = new Reservation(selectedCustomer, selectedMotorhome, reservationDate.getValue(), pickupDate.getValue(), dropoffDate.getValue(), dropoffAddressField.getText(), getSeasonValue());
                                    reservation.setReservationID(reservation.generateID());
                                    addAccessoriesToReservation(reservation);

                                    Reservation.allReservations.add(reservation);
                                    database.updateReservation(reservation);

                                    Rental rental = new Rental(reservation);
                                    rental.setRentalID(rental.generateID());
                                    Rental.allRentals.add(rental);
                                    database.updateRental(rental);

                                    stageHandler.displayInfo("Success", "Reservation successfully added to the system", "Press OK to continue");
                                    updateTable(overviewRentalTable, Rental.allRentals);
                                    updateTable(checkoutRentalTable, unpaidRentals());
                                    updateTable(reservationTable, Reservation.allReservations);
                                    updateTable(reservationScheduleTable, futureReservations());
                                    clearReservationFields();
                                }
                                else
                                {
                                    stageHandler.displayError("Season not specified", "Current season has not been selected", "Please select the current season");
                                }
                            }
                            else
                            {
                                stageHandler.displayError("Drop off address not specified", "Drop off address is missing", "Please enter a Drop off address");
                                dropoffAddressField.requestFocus();
                            }
                        }
                        else
                        {
                            stageHandler.displayError("Drop off date not specified", "Drop off date is missing", "Please enter a Drop off date");
                            dropoffDate.show();
                        }
                    }
                    else
                    {
                        stageHandler.displayError("Pick up date not specified", "Pick up date is missing", "Please enter a Pick up date");
                        pickupDate.show();
                    }
                }
                else
                {
                    stageHandler.displayError("Reservation date not specified", "Reservation date is missing", "Please enter a Reservation date");
                    reservationDate.show();
                }
            }
            else
            {
                stageHandler.displayError("Motorhome not specified", "Motorhome is missing", "Please select a Motorhome");
                onSelectMotorhomeBtnPressed(null);
            }
        }
        else
        {
            stageHandler.displayError("Customer not specified", "Customer is missing", "Please select a Customer");
            onSelectCustomerBtnPressed(null);
        }
    }

    private void clearReservationFields()
    {
        selectedCustomer = null;
        selectCustomerBtn.setText("Select a customer");
        customerTable.getSelectionModel().clearSelection();
        selectedMotorhome = null;
        selectMotorhomeBtn.setText("Select a motorhome");
        motorhomeTable.getSelectionModel().clearSelection();
        reservationDate.setValue(LocalDate.now());
        pickupDate.setValue(null);
        dropoffDate.setValue(null);
        bikeRackBox.setValue("0");
        bedLinenBox.setValue("0");
        childSeatBox.setValue("0");
        picnicTableBox.setValue("0");
        chairBox.setValue("0");
        dropoffBox.getSelectionModel().selectFirst();
        lowSeasonCheck.setSelected(false);
        midSeasonCheck.setSelected(false);
        highSeasonCheck.setSelected(false);
        priceField.setText("0,00");
    }

    private void addAccessoriesToReservation(Reservation reservation)
    {
        HashMap<Accessory, Integer> accessories = new HashMap<>();
        int bikeRackQuantity = Integer.parseInt(bikeRackBox.getValue());
        int bedLinenQuantity = Integer.parseInt(bedLinenBox.getValue());
        int childSeatQuantity = Integer.parseInt(childSeatBox.getValue());
        int picnicTableQuantity = Integer.parseInt(picnicTableBox.getValue());
        int chairQuantity = Integer.parseInt(chairBox.getValue());

        if (bikeRackQuantity >= 0)
        {
            Accessory bikeRack = Accessory.allAccessories.get("Bike Rack");
            int quantity = bikeRack.getQuantity() - bikeRackQuantity;
            bikeRack.setQuantity(quantity);
            accessories.put(bikeRack, bikeRackQuantity);
            database.updateAccessory(bikeRack);
        }
        if (bedLinenQuantity >= 0)
        {
            Accessory bedLinen = Accessory.allAccessories.get("Bed Linen");
            int quantity = bedLinen.getQuantity() - bedLinenQuantity;
            bedLinen.setQuantity(quantity);
            accessories.put(bedLinen, bedLinenQuantity);
            database.updateAccessory(bedLinen);
        }
        if (childSeatQuantity >= 0)
        {
            Accessory childSeat = Accessory.allAccessories.get("Child Seat");
            int quantity = childSeat.getQuantity() - childSeatQuantity;
            childSeat.setQuantity(quantity);
            accessories.put(childSeat, childSeatQuantity);
            database.updateAccessory(childSeat);
        }
        if (picnicTableQuantity >= 0)
        {
            Accessory picnicTable = Accessory.allAccessories.get("Picnic Table");
            int quantity = picnicTable.getQuantity() - picnicTableQuantity;
            picnicTable.setQuantity(quantity);
            accessories.put(picnicTable, picnicTableQuantity);
            database.updateAccessory(picnicTable);
        }
        if (chairQuantity >= 0)
        {
            Accessory chair = Accessory.allAccessories.get("Chair");
            int quantity = chair.getQuantity() - chairQuantity;
            chair.setQuantity(quantity);
            accessories.put(chair, chairQuantity);
            database.updateAccessory(chair);
        }

        reservation.setAccessories(accessories);
    }

    private void handleDropoffAddressField(boolean custom)
    {
        if (custom)
        {
            dropoffAddressField.setDisable(false);
            dropoffAddressField.requestFocus();
            dropoffAddressField.clear();
        }
        else
        {
            dropoffAddressField.setDisable(true);
            dropoffAddressField.setText("Nordic Motorhome Office");
        }
    }

    private int getSeasonValue()
    {
        if (midSeasonCheck.isSelected())
        {
            return 1;
        }
        else if (highSeasonCheck.isSelected())
        {
            return 2;
        }
        else
        {
            return 0;
        }
    }

    private boolean isSeasonSelected()
    {
        if (!lowSeasonCheck.isSelected() && !midSeasonCheck.isSelected() && !highSeasonCheck.isSelected())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public void onLowSeasonSelected(ActionEvent actionEvent)
    {
        if (lowSeasonCheck.isSelected())
        {
            midSeasonCheck.setSelected(false);
            highSeasonCheck.setSelected(false);
        }
        recalculatePrice(actionEvent);
    }

    public void onMidSeasonSelected(ActionEvent actionEvent)
    {
        if (midSeasonCheck.isSelected())
        {
            lowSeasonCheck.setSelected(false);
            highSeasonCheck.setSelected(false);
        }
        recalculatePrice(actionEvent);
    }

    public void onHighSeasonSelected(ActionEvent actionEvent)
    {
        if (highSeasonCheck.isSelected())
        {
            lowSeasonCheck.setSelected(false);
            midSeasonCheck.setSelected(false);
        }
        recalculatePrice(actionEvent);
    }

    public void onPickupDateSelected(ActionEvent actionEvent)
    {
        if (pickupDate.getValue() != null)
        {
            if (pickupDate.getValue().isAfter(reservationDate.getValue()))
            {
                if (dropoffDate.getValue() != null)
                {
                    if (!pickupDate.getValue().isBefore(dropoffDate.getValue()))
                    {
                        pickupDate.setValue(null);
                        stageHandler.displayError("Invalid date", "Pick up date must be BEFORE drop off date", "Please select a valid date");
                        Platform.runLater(() -> {
                            pickupDate.show();
                        });
                    }
                    else
                    {
                        recalculatePrice(actionEvent);
                    }
                }
            }
            else
            {
                stageHandler.displayError("Invalid date", "Pick up date must be AFTER today", "Please select a valid date");
                pickupDate.setValue(null);
                Platform.runLater(() -> {
                    pickupDate.show();
                });
            }
        }
    }

    public void onDropoffDateSelected(ActionEvent actionEvent)
    {
        if (dropoffDate.getValue() != null)
        {
            if (dropoffDate.getValue().isAfter(reservationDate.getValue()))
            {
                if (pickupDate.getValue() != null)
                {
                    if (!dropoffDate.getValue().isAfter(pickupDate.getValue()))
                    {
                        dropoffDate.setValue(null);
                        stageHandler.displayError("Invalid date", "Drop off date must be AFTER pick up date", "Please select a valid date");
                        Platform.runLater(() -> {
                            dropoffDate.show();
                        });
                    }
                    else
                    {
                        recalculatePrice(actionEvent);
                    }
                }
            }
            else
            {
                stageHandler.displayError("Invalid date", "Drop off date must be AFTER today", "Please select a valid date");
                dropoffDate.setValue(null);
                Platform.runLater(() -> {
                    dropoffDate.show();
                });            }
        }
    }

    public void recalculatePrice(ActionEvent actionEvent)
    {
        float price = 0;

        if (!bikeRackBox.getValue().equals(""))
            price += Accessory.allAccessories.get("Bike Rack").getPrice() * Integer.parseInt(bikeRackBox.getValue());
        if (!bedLinenBox.getValue().equals(""))
            price += Accessory.allAccessories.get("Bed Linen").getPrice() * Integer.parseInt(bedLinenBox.getValue());
        if (!childSeatBox.getValue().equals(""))
            price += Accessory.allAccessories.get("Child Seat").getPrice() * Integer.parseInt(childSeatBox.getValue());
        if (!picnicTableBox.getValue().equals(""))
            price += Accessory.allAccessories.get("Picnic Table").getPrice() * Integer.parseInt(picnicTableBox.getValue());
        if (!chairBox.getValue().equals(""))
            price += Accessory.allAccessories.get("Chair").getPrice() * Integer.parseInt(chairBox.getValue());

        if (selectedMotorhome != null && pickupDate.getValue() != null && dropoffDate.getValue() != null)
        {
            price += CalculationHandler.calculateBasePrice(pickupDate.getValue(), dropoffDate.getValue(), selectedMotorhome.getPricePerDay(), getSeasonValue());

            if (dropoffBox.getSelectionModel().getSelectedIndex() == 1) //if custom drop off point
            {
                price += CalculationHandler.calculateDropoffPrice(dropoffAddressField.getText());
            }
        }

        String priceText = new DecimalFormat("#0.00").format(price);
        updatePrice(priceText);
    }

    private void updatePrice(String price)
    {
        priceField.setText(price);
    }

    private ArrayList<Rental> unpaidRentals()
    {
        ArrayList<Rental> rentals = new ArrayList<>();

        for (Rental rental : Rental.allRentals)
        {
            if (!rental.isPaidByCustomer())
            {
                rentals.add(rental);
            }
        }

        return rentals;
    }

    private ArrayList<Reservation> futureReservations()
    {
        ArrayList<Reservation> reservations = new ArrayList<>();

        for (Reservation reservation : Reservation.allReservations)
        {
            if (!reservation.getPickupDate().isBefore(LocalDate.now()))
            {
                reservations.add(reservation);
            }
        }

        return reservations;
    }

    public void onReservationTableClicked(MouseEvent mouseEvent)
    {
        if (!reservationScheduleTable.getSelectionModel().isEmpty())
        {
            calculateFine(reservationScheduleTable.getSelectionModel().getSelectedItem());
        }
    }

    public void onEditReservationBtnPressed(ActionEvent actionEvent)
    {
        if (!reservationTable.getSelectionModel().isEmpty())
        {
            mainTabPane.setDisable(true);

            if (!editReservationPane.isVisible())
            {
                int selectedIndex = reservationTable.getSelectionModel().getSelectedIndex();
                Reservation reservation = Reservation.allReservations.get(selectedIndex);

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

                bikeRackBoxEdit.setValue(String.valueOf(accessories.get(Accessory.allAccessories.get("Bike Rack"))));
                bedLinenBoxEdit.setValue(String.valueOf(accessories.get(Accessory.allAccessories.get("Bed Linen"))));
                childSeatBoxEdit.setValue(String.valueOf(accessories.get(Accessory.allAccessories.get("Child Seat"))));
                picnicTableBoxEdit.setValue(String.valueOf(accessories.get(Accessory.allAccessories.get("Picnic Table"))));
                chairBoxEdit.setValue(String.valueOf(accessories.get(Accessory.allAccessories.get("Chair"))));

                editReservationPane.setDisable(false);
                editReservationPane.setVisible(true);
            }
        }
        else
        {
            stageHandler.displayError("Selection error", "Reservation not selected", "Please select a Reservation from the list");
        }
    }

    public void onEditSaveBtnPressed(ActionEvent actionEvent)
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
                updateTable(reservationTable, Reservation.allReservations);
                updateTable(reservationScheduleTable, futureReservations());
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

    public void onEditCancelBtnPressed(ActionEvent actionEvent)
    {
        closeEditPanel();
    }

    private void closeEditPanel()
    {
        mainTabPane.setDisable(false);

        if (editReservationPane.isVisible())
        {
            editReservationPane.setVisible(false);
            editReservationPane.setDisable(true);
        }
    }

    private void clearFineFields()
    {
        fineField.clear();
        cashCheck.setSelected(false);
        creditCheck.setSelected(false);
    }

    private void calculateFine(Reservation reservation)
    {
        String fineText = String.valueOf(CalculationHandler.calculateCancellationFee(reservation));
        fineField.setText(fineText);
    }

    public void onCashSelected(ActionEvent actionEvent)
    {
        if (cashCheck.isSelected())
        {
            creditCheck.setSelected(false);
        }
    }

    public void onCreditSelected(ActionEvent actionEvent)
    {
        if (creditCheck.isSelected())
        {
            cashCheck.setSelected(false);
        }
    }

    public void onConfirmCancellationBtnPressed(ActionEvent actionEvent)
    {
        if (!reservationScheduleTable.getSelectionModel().isEmpty())
        {
            if (cashCheck.isSelected() || creditCheck.isSelected())
            {
                Reservation reservation = reservationScheduleTable.getSelectionModel().getSelectedItem();
                Reservation.allReservations.remove(reservation);
                database.deleteReservation(reservation);

                for (Rental rental : Rental.allRentals)
                {
                    if (rental.getReservation() == reservation)
                    {
                        Rental.allRentals.remove(rental);
                        database.deleteRental(rental);
                        break;
                    }
                }

                stageHandler.displayInfo("Success", "Reservation successfully cancelled - Payment receieved", "Press OK to continue");
                updateTable(reservationTable, Reservation.allReservations);
                updateTable(reservationScheduleTable, futureReservations());
                clearFineFields();
                updateTable(overviewRentalTable, Rental.allRentals);
                updateTable(checkoutRentalTable, unpaidRentals());
            }
            else
            {
                stageHandler.displayError("Payment type error", "Payment method not selected", "Please select the payment method the customer has chosen");
            }
        }
        else
        {
            stageHandler.displayError("Selection error", "Reservation not selected", "Please select a Reservation from the list");
        }
    }

    private void updateTable(TableView table, ArrayList list)
    {
        table.getItems().setAll(list);
    }

    private void setupTableColumns()
    {
        overviewMotorhomeCol.setCellValueFactory(new PropertyValueFactory<>("motorhomeName"));
        overviewCustomerCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        overviewDateCol.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));
        overviewPriceCol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        overviewStatusCol.setCellValueFactory(new PropertyValueFactory<>("paidByCustomer"));

        checkoutCustomerCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        checkoutMotorhomeCol.setCellValueFactory(new PropertyValueFactory<>("motorhomeName"));
        checkoutDaysCol.setCellValueFactory(new PropertyValueFactory<>("daysOfRental"));
        checkoutPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        checkoutServicePriceCol.setCellValueFactory(new PropertyValueFactory<>("servicePrice"));
        checkoutTotalCol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        customerCPRCol.setCellValueFactory(new PropertyValueFactory<>("cpr"));
        customerBirthdayCol.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerTlfCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        customerEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        motorhomeIDCol.setCellValueFactory(new PropertyValueFactory<>("motorhomeID"));
        motorhomeModelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
        motorhomeBrandCol.setCellValueFactory(new PropertyValueFactory<>("brand"));
        motorhomeSizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
        motorhomePriceCol.setCellValueFactory(new PropertyValueFactory<>("pricePerDay"));
        motorhomeStatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        reservationIDCol.setCellValueFactory(new PropertyValueFactory<>("reservationID"));
        reservationMotorhomeCol.setCellValueFactory(new PropertyValueFactory<>("motorhome"));
        reservationCustomerCol.setCellValueFactory(new PropertyValueFactory<>("customer"));
        reservationPickupDateCol.setCellValueFactory(new PropertyValueFactory<>("pickupDate"));
        reservationPriceCol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        reservationScheduleIDCol.setCellValueFactory(new PropertyValueFactory<>("reservationID"));
        reservationScheduleMotorhomeCol.setCellValueFactory(new PropertyValueFactory<>("motorhome"));
        reservationScheduleCustomerCol.setCellValueFactory(new PropertyValueFactory<>("customer"));
        reservationSchedulePickupDateCol.setCellValueFactory(new PropertyValueFactory<>("pickupDate"));
        reservationSchedulePriceCol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
    }

    private void addListenerHandler()
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

        dropoffBox.valueProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (!newValue.matches(dropoffBox.getItems().get(0)))
                {
                    handleDropoffAddressField(true);
                }
                else
                {
                    handleDropoffAddressField(false);
                }

                recalculatePrice(null);
            }
        });

        overviewSearchField.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (newValue.matches(""))
                {
                    updateTable(overviewRentalTable, Rental.allRentals);
                }
                else
                {
                    updateTable(overviewRentalTable, SearchHandler.findRental(overviewSearchField.getText()));
                }

            }
        });

        checkoutSearchField.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (newValue.matches(""))
                {
                    updateTable(checkoutRentalTable, unpaidRentals());
                }
                else
                {
                    updateTable(checkoutRentalTable, SearchHandler.findUnpaidRental(checkoutSearchField.getText(), unpaidRentals()));
                }

            }
        });

        findCustomerSearchField.textProperty().addListener(new ChangeListener<String>()
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
                    updateTable(customerTable, SearchHandler.findCustomer(findCustomerSearchField.getText()));
                }

            }
        });

        findMotorhomeSearchField.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (newValue.matches(""))
                {
                    updateTable(motorhomeTable, showAvailableMotorhomes(Motorhome.allMotorhomes));
                }
                else
                {
                    ArrayList<Motorhome> motorhomes = SearchHandler.findMotorhome(findMotorhomeSearchField.getText());
                    updateTable(motorhomeTable, showAvailableMotorhomes(motorhomes));
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
                    int limitVal = Accessory.allAccessories.get("Bike Rack").getQuantity();

                    if (inputVal > limitVal)
                    {
                        bikeRackBox.getEditor().setText(limitVal + "");
                        bikeRackBox.setValue(limitVal + "");
                    }
                    else
                    {
                        bikeRackBox.setValue(newValue);
                    }
                    recalculatePrice(null);
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
                    int limitVal = Accessory.allAccessories.get("Bed Linen").getQuantity();

                    if (inputVal > limitVal)
                    {
                        bedLinenBox.getEditor().setText(limitVal + "");
                        bedLinenBox.setValue(limitVal + "");
                    }
                    else
                    {
                        bedLinenBox.setValue(newValue);
                    }
                    recalculatePrice(null);
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
                    int limitVal = Accessory.allAccessories.get("Child Seat").getQuantity();

                    if (inputVal > limitVal)
                    {
                        childSeatBox.getEditor().setText(limitVal + "");
                        childSeatBox.setValue(limitVal + "");
                    }
                    else
                    {
                        childSeatBox.setValue(newValue);
                    }
                    recalculatePrice(null);
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
                    int limitVal = Accessory.allAccessories.get("Picnic Table").getQuantity();

                    if (inputVal > limitVal)
                    {
                        picnicTableBox.getEditor().setText(limitVal + "");
                        picnicTableBox.setValue(limitVal + "");
                    }
                    else
                    {
                        picnicTableBox.setValue(newValue);
                    }
                    recalculatePrice(null);
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
                    int limitVal = Accessory.allAccessories.get("Chair").getQuantity();

                    if (inputVal > limitVal)
                    {
                        chairBox.getEditor().setText(limitVal + "");
                        chairBox.setValue(limitVal + "");
                    }
                    else
                    {
                        chairBox.setValue(newValue);
                    }
                    recalculatePrice(null);
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

        reservationScheduleSearchField.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (newValue.matches(""))
                {
                    updateTable(reservationScheduleTable, futureReservations());
                }
                else
                {
                    updateTable(reservationScheduleTable, SearchHandler.findFutureReservation(reservationScheduleSearchField.getText(), futureReservations()));
                }
            }
        });

        bikeRackBoxEdit.getEditor().textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (!newValue.matches("\\d*"))
                {
                    bikeRackBoxEdit.getEditor().setText(newValue.replaceAll("[^\\d]", ""));
                }
                else
                {
                    int inputVal = Integer.parseInt(newValue);
                    int limitVal = 99;

                    if (inputVal > limitVal)
                    {
                        bikeRackBoxEdit.getEditor().setText(limitVal + "");
                        bikeRackBoxEdit.setValue(limitVal + "");
                    }
                    else
                    {
                        bikeRackBoxEdit.setValue(newValue);
                    }
                }
            }
        });

        bedLinenBoxEdit.getEditor().textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (!newValue.matches("\\d*"))
                {
                    bedLinenBoxEdit.getEditor().setText(newValue.replaceAll("[^\\d]", ""));
                }
                else
                {
                    int inputVal = Integer.parseInt(newValue);
                    int limitVal = 99;

                    if (inputVal > limitVal)
                    {
                        bedLinenBoxEdit.getEditor().setText(limitVal + "");
                        bedLinenBoxEdit.setValue(limitVal + "");
                    }
                    else
                    {
                        bedLinenBoxEdit.setValue(newValue);
                    }
                }
            }
        });

        childSeatBoxEdit.getEditor().textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (!newValue.matches("\\d*"))
                {
                    childSeatBoxEdit.getEditor().setText(newValue.replaceAll("[^\\d]", ""));
                }
                else
                {
                    int inputVal = Integer.parseInt(newValue);
                    int limitVal = 99;

                    if (inputVal > limitVal)
                    {
                        childSeatBoxEdit.getEditor().setText(limitVal + "");
                        childSeatBoxEdit.setValue(limitVal + "");
                    }
                    else
                    {
                        childSeatBoxEdit.setValue(newValue);
                    }
                }
            }
        });

        picnicTableBoxEdit.getEditor().textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (!newValue.matches("\\d*"))
                {
                    picnicTableBoxEdit.getEditor().setText(newValue.replaceAll("[^\\d]", ""));
                }
                else
                {
                    int inputVal = Integer.parseInt(newValue);
                    int limitVal = 99;

                    if (inputVal > limitVal)
                    {
                        picnicTableBoxEdit.getEditor().setText(limitVal + "");
                        picnicTableBoxEdit.setValue(limitVal + "");
                    }
                    else
                    {
                        picnicTableBoxEdit.setValue(newValue);
                    }
                }
            }
        });

        chairBoxEdit.getEditor().textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (!newValue.matches("\\d*"))
                {
                    chairBoxEdit.getEditor().setText(newValue.replaceAll("[^\\d]", ""));
                }
                else
                {
                    int inputVal = Integer.parseInt(newValue);
                    int limitVal = 99;

                    if (inputVal > limitVal)
                    {
                        chairBoxEdit.getEditor().setText(limitVal + "");
                        chairBoxEdit.setValue(limitVal + "");
                    }
                    else
                    {
                        chairBoxEdit.setValue(newValue);
                    }
                }
            }
        });
    }

    private void setupComboboxes()
    {
        dropoffBox.setItems(points);
        bikeRackBox.setItems(quantities);
        bedLinenBox.setItems(quantities);
        childSeatBox.setItems(quantities);
        picnicTableBox.setItems(quantities);
        chairBox.setItems(quantities);
        bikeRackBoxEdit.setItems(quantities);
        bedLinenBoxEdit.setItems(quantities);
        childSeatBoxEdit.setItems(quantities);
        picnicTableBoxEdit.setItems(quantities);
        chairBoxEdit.setItems(quantities);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        setupComboboxes();
        setupTableColumns();
        updateTable(overviewRentalTable, Rental.allRentals);
        updateTable(checkoutRentalTable, unpaidRentals());
        updateTable(reservationTable, Reservation.allReservations);
        updateTable(reservationScheduleTable, futureReservations());
        clearCustomerFields();
        clearReservationFields();
        addListenerHandler();
    }
}
