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

import java.net.URL;
import java.sql.Date;
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

                                stageHandler.displayAlert("Success", "Customer successfully added to the system", "Press OK to continue");
                                updateCustomerList();
                                clearCustomerFields();
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
                            if (dropoffBox.getSelectionModel().getSelectedIndex() == 0 || (dropoffBox.getSelectionModel().getSelectedIndex() == 1 && !dropoffAddressField.getText().equals("")))
                            {
                                if (isSeasonSelected())
                                {
                                    Reservation reservation = new Reservation(customerBox.getValue(), motorhomeBox.getValue(), reservationDate.getValue(), pickupDate.getValue(), dropoffDate.getValue(), dropoffAddressField.getText(), getSeasonValue());
                                    Reservation.allReservations.add(reservation);
                                    stageHandler.displayAlert("Success", "Reservation successfully added to the system", "Press OK to continue");
                                    clearReservationFields();
                                }
                                else
                                {
                                    stageHandler.displayAlert("Season not specified", "Current season has not been selected", "Please select the current season");
                                }
                            }
                            else
                            {
                                stageHandler.displayAlert("Drop off address not specified", "Drop off address is missing", "Please enter a Drop off address");
                            }
                        }
                        else
                        {
                            stageHandler.displayAlert("Drop off date not specified", "Drop off date is missing", "Please enter a Drop off date");
                            dropoffDate.show();
                        }
                    }
                    else
                    {
                        stageHandler.displayAlert("Pick up date not specified", "Pick up date is missing", "Please enter a Pick up date");
                        pickupDate.show();
                    }
                }
                else
                {
                    stageHandler.displayAlert("Reservation date not specified", "Reservation date is missing", "Please enter a Reservation date");
                    reservationDate.show();
                }
            }
            else
            {
                stageHandler.displayAlert("Motorhome not specified", "Motorhome is missing", "Please select a Motorhome");
                motorhomeBox.show();
            }
        }
        else
        {
            stageHandler.displayAlert("Customer not specified", "Customer is missing", "Please select a Customer");
            customerBox.show();
        }
    }

    private void clearReservationFields()
    {
        customerBox.getSelectionModel().clearSelection();
        motorhomeBox.getSelectionModel().clearSelection();
        reservationDate.setValue(null);
        pickupDate.setValue(null);
        dropoffDate.setValue(null);
        bikeRackCheck.setSelected(false);
        bedLinenCheck.setSelected(false);
        childSeatCheck.setSelected(false);
        picnicTableCheck.setSelected(false);
        chairsCheck.setSelected(false);
        dropoffBox.getSelectionModel().selectFirst();
        priceField.setText("0.0 kr");
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
        if (lowSeasonCheck.isSelected())
        {
            return 0;
        }
        else if (midSeasonCheck.isSelected())
        {
            return 1;
        }
        else if (highSeasonCheck.isSelected())
        {
            return 2;
        }
        else
        {
            return -1;
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
    }

    public void onMidSeasonSelected(ActionEvent actionEvent)
    {
        if (midSeasonCheck.isSelected())
        {
            lowSeasonCheck.setSelected(false);
            highSeasonCheck.setSelected(false);
        }
    }

    public void onHighSeasonSelected(ActionEvent actionEvent)
    {
        if (highSeasonCheck.isSelected())
        {
            lowSeasonCheck.setSelected(false);
            midSeasonCheck.setSelected(false);
        }
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
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        //add example motorhome for now
        Motorhome motorhome = new Motorhome("example", "ad", "ad", true, true, 4f);
        Motorhome.allMotorhomes.add(motorhome);
        ObservableList<Motorhome> motorhomes = FXCollections.observableArrayList(Motorhome.allMotorhomes);
        motorhomeBox.setItems(motorhomes);

        updateCustomerList();
        dropoffBox.setItems(points);
        clearCustomerFields();
        clearReservationFields();
        addListenerHandler();
    }
}
