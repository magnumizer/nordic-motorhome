package Controller;//Magnus Svendsen DAT16i

import Model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SController implements Initializable
{
    //region FXML
    @FXML
    private TextField searchField;

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
    private TableColumn<Rental, Float> priceCol;

    @FXML
    private TextField serviceField;
    @FXML
    private TextField priceField;
    @FXML
    private TextArea descriptionField;
    //endregion

    private StageHandler stageHandler = new StageHandler();

    public void onLogOutBtnPressed(ActionEvent actionEvent)
    {
        stageHandler.logOut();
    }

    public void onSearchEnter(ActionEvent actionEvent)
    {
        if (!searchField.getText().equals(""))
        {
            updateTable(SearchHandler.searchRentals(searchField.getText()));
        }
        else
        {
            updateTable(Rental.allRentals);
        }
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
                    Service service = new Service(serviceField.getText(), price,  descriptionField.getText());
                    Rental.allRentals.get(selectedIndex).setService(service);
                    stageHandler.displayInfo("Success", "Service successfully submitted to Rental", "Press OK to continue");
                    updateTable(Rental.allRentals);
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

    private void updateTable(ArrayList<Rental> rentalList)
    {
        rentalTable.getItems().setAll(rentalList);
    }

    private void clearFields()
    {
        serviceField.clear();
        priceField.clear();
        descriptionField.clear();
    }

    private void setupTableColumns()
    {
        rentalIDCol.setCellValueFactory(new PropertyValueFactory<>("rentalID"));
        customerCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        motorhomeCol.setCellValueFactory(new PropertyValueFactory<>("motorhomeName"));
        serviceCol.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("servicePrice"));
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
                    updateTable(Rental.allRentals);
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
        updateTable(Rental.allRentals);
        setupTableColumns();
        addListenerHandler();
    }
}
