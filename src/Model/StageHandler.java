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

    public void displayAlert(String title, String header, String content)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(Main.primaryStage);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void handleSceneChange(String sceneTitle)
    {
        Parent root = null;

        switch (sceneTitle)
        {
            case "Admin":
                try
                {
                    System.out.println("Loading Admin screen...");
                    root = FXMLLoader.load(getClass().getResource("/GUI/Admin.fxml"));

                } catch (IOException e)
                {
                    e.printStackTrace();
                    System.out.println("FXML file not found: Make sure path is correct.");
                }
                break;
            case "Sales Assistant":
                try
                {
                    System.out.println("Loading Sales Assistant screen...");
                    root = FXMLLoader.load(getClass().getResource("/GUI/SalesAssistant.fxml"));

                } catch (IOException e)
                {
                    e.printStackTrace();
                    System.out.println("FXML file not found: Make sure path is correct.");
                }
                break;
            case "Auto Mechanic":
                try
                {
                    System.out.println("Loading Auto Mechanic screen...");
                    root = FXMLLoader.load(getClass().getResource("/GUI/AutoMechanic.fxml"));

                } catch (IOException e)
                {
                    e.printStackTrace();
                    System.out.println("FXML file not found: Make sure path is correct.");
                }
                break;
            case "Login":
                try
                {
                    System.out.println("Loading Login screen...");
                    root = FXMLLoader.load(getClass().getResource("/GUI/Login.fxml"));

                } catch (IOException e)
                {
                    e.printStackTrace();
                    System.out.println("FXML file not found: Make sure path is correct.");
                }
                break;
            default:
                System.out.println("Error changing scene. Make sure you are using a correct reference.");
                break;
        }

        if (root != null)
        {
            primaryStage.hide();
            Scene scene = new Scene(root, 600, 400);
            scene.getStylesheets().add("/CSS/theme.css");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    public void logOut()
    {
        StageHandler.currentUser = null;
        handleSceneChange("Login");
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
