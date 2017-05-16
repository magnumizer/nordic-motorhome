package Controller;//Magnus Svendsen DAT16i

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

import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SAController implements Initializable
{
    ObservableList<String> points =
            FXCollections.observableArrayList(
                    "Default",
                    "Custom"
            );

    //region FXML
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
    private ComboBox<Customer> customerBox;
    @FXML
    private ComboBox<Motorhome> motorhomeBox;
    @FXML
    private DatePicker reservationDate;
    @FXML
    private DatePicker pickupDate;
    @FXML
    private DatePicker dropoffDate;
    @FXML
    private CheckBox bikeRackCheck;
    @FXML
    private CheckBox bedLinenCheck;
    @FXML
    private CheckBox childSeatCheck;
    @FXML
    private CheckBox picnicTableCheck;
    @FXML
    private CheckBox chairsCheck;
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
    //endregion

    private StageHandler stageHandler = new StageHandler();


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
                                int number = Integer.parseInt(tlfField.getText());

                                Customer customer = new Customer(nameField.getText(), cprField.getText(), birthdayPicker.getValue(), addressField.getText(), number, emailField.getText());
                                Customer.allCustomers.add(customer);

                                stageHandler.displayInfo("Success", "Customer successfully added to the system", "Press OK to continue");
                                updateCustomerList();
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

    private void updateCustomerList()
    {
        ObservableList<Customer> customers = FXCollections.observableArrayList(Customer.allCustomers);
        customerBox.setItems(customers);
    }

    public void onRegisterBtnPressed(ActionEvent actionEvent)
    {
        if (!customerBox.getSelectionModel().isEmpty())
        {
            if (!motorhomeBox.getSelectionModel().isEmpty())
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
                                    Reservation reservation = new Reservation(customerBox.getValue(), motorhomeBox.getValue(), reservationDate.getValue(), pickupDate.getValue(), dropoffDate.getValue(), dropoffAddressField.getText(), getSeasonValue());
                                    addAccessoriesToReservation(reservation);
                                    Reservation.allReservations.add(reservation);
                                    stageHandler.displayInfo("Success", "Reservation successfully added to the system", "Press OK to continue");
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
                motorhomeBox.show();
            }
        }
        else
        {
            stageHandler.displayError("Customer not specified", "Customer is missing", "Please select a Customer");
            customerBox.show();
        }
    }

    private void clearReservationFields()
    {
        customerBox.getSelectionModel().clearSelection();
        motorhomeBox.getSelectionModel().clearSelection();
        reservationDate.setValue(LocalDate.now());
        pickupDate.setValue(null);
        dropoffDate.setValue(null);
        bikeRackCheck.setSelected(false);
        bedLinenCheck.setSelected(false);
        childSeatCheck.setSelected(false);
        picnicTableCheck.setSelected(false);
        chairsCheck.setSelected(false);
        dropoffBox.getSelectionModel().selectFirst();
        lowSeasonCheck.setSelected(false);
        midSeasonCheck.setSelected(false);
        highSeasonCheck.setSelected(false);
        priceField.setText("0,00");
    }

    private void addAccessoriesToReservation(Reservation reservation)
    {
        if (bikeRackCheck.isSelected())
            reservation.addAccessory(Accessory.allAccessories.get("Bike Rack"));
        if (bedLinenCheck.isSelected())
            reservation.addAccessory(Accessory.allAccessories.get("Bed Linen"));
        if (childSeatCheck.isSelected())
            reservation.addAccessory(Accessory.allAccessories.get("Child Seat"));
        if (picnicTableCheck.isSelected())
            reservation.addAccessory(Accessory.allAccessories.get("Picnic Table"));
        if (chairsCheck.isSelected())
            reservation.addAccessory(Accessory.allAccessories.get("Chair"));
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
        if (pickupDate.getValue() != null && dropoffDate.getValue() != null)
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

    public void onDropoffDateSelected(ActionEvent actionEvent)
    {
        if (pickupDate.getValue() != null && dropoffDate.getValue() != null)
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

    public void recalculatePrice(ActionEvent actionEvent)
    {
        float price = 0;

        if (bikeRackCheck.isSelected())
            price += Accessory.allAccessories.get("Bike Rack").getPrice();
        if (bedLinenCheck.isSelected())
            price += Accessory.allAccessories.get("Bed Linen").getPrice();
        if (childSeatCheck.isSelected())
            price += Accessory.allAccessories.get("Child Seat").getPrice();
        if (picnicTableCheck.isSelected())
            price += Accessory.allAccessories.get("Picnic Table").getPrice();
        if (chairsCheck.isSelected())
            price += Accessory.allAccessories.get("Chair").getPrice();

        if (!motorhomeBox.getSelectionModel().isEmpty() && pickupDate.getValue() != null && dropoffDate.getValue() != null)
        {
            price += CalculationHandler.calculateBasePrice(pickupDate.getValue(), dropoffDate.getValue(), motorhomeBox.getValue().getPricePerDay(), getSeasonValue());

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

    private void updateMotorhomeList()
    {
        ObservableList<Motorhome> motorhomes = FXCollections.observableArrayList(Motorhome.allMotorhomes);
        motorhomeBox.setItems(motorhomes);
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
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        updateCustomerList();
        updateMotorhomeList();
        dropoffBox.setItems(points);
        clearCustomerFields();
        clearReservationFields();
        addListenerHandler();
    }
}
