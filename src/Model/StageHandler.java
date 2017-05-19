package Model;//Magnus Svendsen DAT16i

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Optional;

import static Model.Main.primaryStage;

public class StageHandler
{
    public static Employee currentUser = null;

    public void closeProgram()
    {
        //save any data that needs to be saved here


        Main.primaryStage.fireEvent(
                new WindowEvent(
                        Main.primaryStage,
                        WindowEvent.WINDOW_CLOSE_REQUEST
                ));
    }

    public void displayError(String title, String header, String content)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(Main.primaryStage);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void displayInfo(String title, String header, String content)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(Main.primaryStage);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void handleSceneChange()
    {
        Parent root = null;

        if (StageHandler.currentUser instanceof Admin)
        {
            try
            {
                System.out.println("Loading Admin screen...");
                root = FXMLLoader.load(getClass().getResource("/GUI/Admin.fxml"));

            } catch (IOException e)
            {
                e.printStackTrace();
                System.out.println("FXML file not found: Make sure path is correct.");
            }
        }
        else if (StageHandler.currentUser instanceof SalesAssistant)
        {
            try
            {
                System.out.println("Loading Sales Assistant screen...");
                root = FXMLLoader.load(getClass().getResource("/GUI/SalesAssistant.fxml"));

            } catch (IOException e)
            {
                e.printStackTrace();
                System.out.println("FXML file not found: Make sure path is correct.");
            }
        }
        else if (StageHandler.currentUser instanceof AutoMechanic || StageHandler.currentUser instanceof CleaningPersonnel)
        {
            try
            {
                System.out.println("Loading Service screen...");
                root = FXMLLoader.load(getClass().getResource("/GUI/Service.fxml"));

            } catch (IOException e)
            {
                e.printStackTrace();
                System.out.println("FXML file not found: Make sure path is correct.");
            }
        }
        else if (StageHandler.currentUser == null)
        {
            try
            {
                System.out.println("Loading Login screen...");
                root = FXMLLoader.load(getClass().getResource("/GUI/Login.fxml"));

            } catch (IOException e)
            {
                e.printStackTrace();
                System.out.println("FXML file not found: Make sure path is correct.");
            }
        }
        else
        {
            System.out.println("Error changing scene. Make sure you are using a correct reference.");
        }

        if (root != null)
        {
            primaryStage.hide();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/GUI/CSS/theme.css");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    public void logOut()
    {
        StageHandler.currentUser = null;
        handleSceneChange();
    }

    public static EventHandler<WindowEvent> confirmCloseEventHandler = event ->
    {
        Alert closeConfirmation = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Confirm Exit"
        );
        Button exitButton = (Button) closeConfirmation.getDialogPane().lookupButton(
                ButtonType.OK
        );
        exitButton.setText("Exit");
        closeConfirmation.setHeaderText("Exit Nordic Motorhome Rental System?");
        closeConfirmation.initModality(Modality.APPLICATION_MODAL);
        closeConfirmation.initOwner(Main.primaryStage);

        Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
        if (!ButtonType.OK.equals(closeResponse.get())) {
            event.consume();
        }
    };
}
