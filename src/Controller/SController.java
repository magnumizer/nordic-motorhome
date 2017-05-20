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
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SController implements Initializable
{
    ObservableList<String> statusOptions =
            FXCollections.observableArrayList(
                    "Available",
                    "Rented",
                    "Out of service"
            );

    //region FXML
    @FXML
    private TabPane mainTabPane;

    @FXML
    private TextField searchField;
    @FXML
    private TextField motorhomeSearchField;

    @FXML
    private TableView<Rental> rentalTable;
    @FXML
    private TableColumn<Rental, String> rentalIDCol;
    @FXML
    private TableColumn<Rental, String> customerCol;
    @FXML
    private TableColumn<Rental, String> motorhomeCol;
    @FXML
    private TableColumn<Rental, String> serviceCol;
    @FXML
    private TableColumn<Rental, String> serviceDateCol;
    @FXML
    private TableColumn<Rental, Float> priceCol;

    @FXML
    private TextField serviceField;
    @FXML
    private TextField priceField;
    @FXML
    private TextArea descriptionField;

    @FXML
    private TableView<Motorhome> motorhomeTable;
    @FXML
    private TableColumn<Motorhome, String> motorhomeIDCol;
    @FXML
    private TableColumn<Motorhome, String> motorhomeModelCol;
    @FXML
    private TableColumn<Motorhome, String> motorhomeBrandCol;
    @FXML
    private TableColumn<Motorhome, String> motorhomeStatusCol;
    @FXML
    private TableColumn<Motorhome, String> motorhomeDateCheckCol;

    @FXML
    private AnchorPane editMotorhomeStatusPane;
    @FXML
    private ComboBox<String> statusBox;
    //endregion

    private StageHandler stageHandler = new StageHandler();

    public void onOptionsBtnPressed(ActionEvent actionEvent)
    {

    }

    public void onLogOutBtnPressed(ActionEvent actionEvent)
    {
        stageHandler.logOut();
    }


    public void onSubmitBtnPressed(ActionEvent actionEvent)
    {
        int selectedIndex = rentalTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0)
        {
            updateRental(selectedIndex);
        }
        else
        {
            // if nothing selected.
            stageHandler.displayError("Selection Error", "Rental selection required", "Please select the Rental you wish to update");
        }
    }

    private void updateRental(int selectedIndex)
    {
        if (!serviceField.getText().equals(""))
        {
            if (!priceField.getText().equals(""))
            {
                if (!descriptionField.getText().equals(""))
                {
                    float price = Float.parseFloat(priceField.getText());
                    Service service = new Service(serviceField.getText(), price, descriptionField.getText(), LocalDate.now());
                    Rental.allRentals.get(selectedIndex).setService(service);
                    stageHandler.displayInfo("Success", "Service successfully submitted to Rental", "Press OK to continue");
                    updateRentalTable(Rental.allRentals);
                    clearFields();
                }
                else
                {
                    stageHandler.displayError("Description error", "Description required", "Please enter the Description for the service");
                    descriptionField.requestFocus();
                }
            }
            else
            {
                stageHandler.displayError("Price error", "Price required", "Please enter the price for the service");
                priceField.requestFocus();
            }
        }
        else
        {
            stageHandler.displayError("Title error", "Service title required", "Please enter the type of service");
            serviceField.requestFocus();
        }
    }

    private void updateRentalTable(ArrayList<Rental> rentalList)
    {
        rentalTable.getItems().setAll(rentalList);
    }

    private void updateMotorhomeTable(ArrayList<Motorhome> motorhomeList)
    {
        motorhomeTable.getItems().setAll(motorhomeList);
    }

    private void clearFields()
    {
        serviceField.clear();
        priceField.clear();
        descriptionField.clear();
    }

    public void onCheckStatusBtnPressed(ActionEvent actionEvent)
    {
        //open reservation edit panel
        if (!motorhomeTable.getSelectionModel().isEmpty())
        {
            mainTabPane.setDisable(true);

            if (!editMotorhomeStatusPane.isVisible())
            {
                int selectedIndex = motorhomeTable.getSelectionModel().getSelectedIndex();
                Motorhome motorhome = Motorhome.allMotorhomes.get(selectedIndex);

                statusBox.getSelectionModel().select(motorhome.getStatus());

                editMotorhomeStatusPane.setDisable(false);
                editMotorhomeStatusPane.setVisible(true);
            }
        }
        else
        {
            stageHandler.displayError("Selection error", "Motorhome not selected", "Please select a Motorhome you want to update");
        }
    }

    public void onStatusSaveBtnPressed(ActionEvent actionEvent)
    {
        int selectedIndex = motorhomeTable.getSelectionModel().getSelectedIndex();
        Motorhome motorhome = Motorhome.allMotorhomes.get(selectedIndex);

        if (statusBox.getValue().equals("Rented"))
        {
            motorhome.setRentedStatus(true);
            motorhome.setServiceStatus(false);
        }
        else if (statusBox.getValue().equals("Out of service"))
        {
            motorhome.setRentedStatus(false);
            motorhome.setServiceStatus(true);
        }
        else
        {
            motorhome.setRentedStatus(false);
            motorhome.setServiceStatus(false);
        }

        motorhome.setDateOfCheck(LocalDate.now());

        stageHandler.displayInfo("Success", "Motorhome details have been changed", "Press OK to continue");
        motorhomeTable.getItems().setAll(Motorhome.allMotorhomes);
        closeEditPanel();
    }

    public void onStatusCancelBtnPressed(ActionEvent actionEvent)
    {
        closeEditPanel();
    }

    private void closeEditPanel()
    {
        mainTabPane.setDisable(false);

        if (editMotorhomeStatusPane.isVisible())
        {
            editMotorhomeStatusPane.setVisible(false);
            editMotorhomeStatusPane.setDisable(true);
        }
    }


    private void setupTableColumns()
    {
        rentalIDCol.setCellValueFactory(new PropertyValueFactory<>("rentalID"));
        customerCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        motorhomeCol.setCellValueFactory(new PropertyValueFactory<>("motorhomeName"));
        serviceCol.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        serviceDateCol.setCellValueFactory(new PropertyValueFactory<>("serviceDate"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("servicePrice"));

        motorhomeIDCol.setCellValueFactory(new PropertyValueFactory<>("motorhomeID"));
        motorhomeModelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
        motorhomeBrandCol.setCellValueFactory(new PropertyValueFactory<>("brand"));
        motorhomeStatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        motorhomeDateCheckCol.setCellValueFactory(new PropertyValueFactory<>("dateOfCheck"));
    }

    private void addListenerHandler()
    {
        searchField.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
            {
                if (newValue.matches(""))
                {
                    updateRentalTable(Rental.allRentals);
                }
                else
                {
                    updateRentalTable(SearchHandler.findRental(searchField.getText()));
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
                    updateMotorhomeTable(Motorhome.allMotorhomes);
                }
                else
                {
                    updateMotorhomeTable(SearchHandler.findMotorhome(motorhomeSearchField.getText()));
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
        updateRentalTable(Rental.allRentals);
        updateMotorhomeTable(Motorhome.allMotorhomes);
        statusBox.setItems(statusOptions);
        setupTableColumns();
        addListenerHandler();
    }
}
