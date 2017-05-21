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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private AnchorPane findCustomerPane;
    @FXML
    private TextField findCustomerSearchField;
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, String> customerIDCol;
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
    //endregion

    private StageHandler stageHandler = new StageHandler();

    private Customer selectedCustomer = null;
    private Motorhome selectedMotorhome = null;

    public void onOptionsBtnPressed(ActionEvent actionEvent)
    {

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
                                int number = Integer.parseInt(tlfField.getText());

                                Customer customer = new Customer(nameField.getText(), cprField.getText(), birthdayPicker.getValue(), addressField.getText(), number, emailField.getText());
                                Customer.allCustomers.add(customer);

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
        mainTabPane.setDisable(true);

        if (!findMotorhomePane.isVisible())
        {
            findMotorhomeSearchField.setText("");
            motorhomeTable.getItems().setAll(showAvailableMotorhomes(Motorhome.allMotorhomes));

            findMotorhomePane.setDisable(false);
            findMotorhomePane.setVisible(true);
        }
    }

    public void onFindMotorhomeOKBtnPressed(ActionEvent actionEvent)
    {
        if (!motorhomeTable.getSelectionModel().isEmpty())
        {
            Motorhome motorhome = motorhomeTable.getSelectionModel().getSelectedItem();
            selectedMotorhome = motorhome;
            selectMotorhomeBtn.setText(motorhome.getModel());
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

    private ArrayList<Motorhome> showAvailableMotorhomes(ArrayList<Motorhome> motorhomeList)
    {
        ArrayList<Motorhome> motorhomes = new ArrayList<>();

        for (Motorhome motorhome : motorhomeList)
        {
            if (!motorhome.isRentedStatus() && !motorhome.isServiceStatus())
            {
                motorhomes.add(motorhome);
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
                                    addAccessoriesToReservation(reservation);
                                    Reservation.allReservations.add(reservation);
                                    stageHandler.displayInfo("Success", "Reservation successfully added to the system", "Press OK to continue");
                                    updateTable(overviewRentalTable, Rental.allRentals);
                                    updateTable(checkoutRentalTable, Rental.allRentals);
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
        int bikeRackQuantity = Integer.parseInt(bikeRackBox.getValue());
        int bedLinenQuantity = Integer.parseInt(bedLinenBox.getValue());
        int childSeatQuantity = Integer.parseInt(childSeatBox.getValue());
        int picnicTableQuantity = Integer.parseInt(picnicTableBox.getValue());
        int chairQuantity = Integer.parseInt(chairBox.getValue());

        if (bikeRackQuantity > 0)
            reservation.addAccessory(new Accessory("Bike Rack", Accessory.allAccessories.get("Bike Rack").getPrice(), bikeRackQuantity));
        if (bedLinenQuantity > 0)
            reservation.addAccessory(new Accessory("Bed Linen", Accessory.allAccessories.get("Bed Linen").getPrice(), bedLinenQuantity));
        if (childSeatQuantity > 0)
            reservation.addAccessory(new Accessory("Child Seat", Accessory.allAccessories.get("Child Seat").getPrice(), childSeatQuantity));
        if (picnicTableQuantity > 0)
            reservation.addAccessory(new Accessory("Picnic Table", Accessory.allAccessories.get("Picnic Table").getPrice(), picnicTableQuantity));
        if (chairQuantity > 0)
            reservation.addAccessory(new Accessory("Chair", Accessory.allAccessories.get("Chair").getPrice(), chairQuantity));
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

        price += Accessory.allAccessories.get("Bike Rack").getPrice() * Integer.parseInt(bikeRackBox.getValue());
        price += Accessory.allAccessories.get("Bed Linen").getPrice() * Integer.parseInt(bedLinenBox.getValue());
        price += Accessory.allAccessories.get("Child Seat").getPrice() * Integer.parseInt(childSeatBox.getValue());
        price += Accessory.allAccessories.get("Picnic Table").getPrice() * Integer.parseInt(picnicTableBox.getValue());
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

        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
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
                    updateTable(checkoutRentalTable, Rental.allRentals);
                }
                else
                {
                    updateTable(checkoutRentalTable, SearchHandler.findRental(checkoutSearchField.getText()));
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
                bikeRackBox.setValue(newValue);
                recalculatePrice(null);
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
                bedLinenBox.setValue(newValue);
                recalculatePrice(null);
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
                childSeatBox.setValue(newValue);
                recalculatePrice(null);
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
                picnicTableBox.setValue(newValue);
                recalculatePrice(null);
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
                chairBox.setValue(newValue);
                recalculatePrice(null);
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        dropoffBox.setItems(points);
        bikeRackBox.setItems(quantities);
        bedLinenBox.setItems(quantities);
        childSeatBox.setItems(quantities);
        picnicTableBox.setItems(quantities);
        chairBox.setItems(quantities);
        setupTableColumns();
        updateTable(overviewRentalTable, Rental.allRentals);
        updateTable(checkoutRentalTable, Rental.allRentals);
        clearCustomerFields();
        clearReservationFields();
        addListenerHandler();
    }
}
