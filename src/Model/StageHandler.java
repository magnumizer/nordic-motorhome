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

    public void handleSceneChange(String position)
    {
        Parent root = null;

        switch (position)
        {
            case "Admin":
                try
                {
                    root = FXMLLoader.load(getClass().getResource("/GUI/Admin.fxml"));
                    System.out.println("Loading admin screen...");

                } catch (IOException e)
                {
                    e.printStackTrace();
                    System.out.println("FXML file not found: Make sure path is correct.");
                }
                break;
            case "Sales Assistant":
                try
                {
                    root = FXMLLoader.load(getClass().getResource("/GUI/SalesAssistant.fxml"));
                    System.out.println("Loading Sales Assistant screen...");

                } catch (IOException e)
                {
                    e.printStackTrace();
                    System.out.println("FXML file not found: Make sure path is correct.");
                }
                break;
            case "Auto Mechanic":
                try
                {
                    root = FXMLLoader.load(getClass().getResource("/GUI/AutoMechanic.fxml"));
                    System.out.println("Loading Auto Mechanic screen...");

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

        primaryStage.hide();
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void logOut()
    {
        Parent root = null;

        try
        {
            root = FXMLLoader.load(getClass().getResource("/GUI/Login.fxml"));
            System.out.println("Loading Login screen...");

        } catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("FXML file not found: Make sure path is correct.");
        }

        primaryStage.hide();
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static EventHandler<WindowEvent> confirmCloseEventHandler = event ->
    {
        Alert closeConfirmation = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Exit Nordic Motorhome Rental System?"
        );
        Button exitButton = (Button) closeConfirmation.getDialogPane().lookupButton(
                ButtonType.OK
        );
        exitButton.setText("Exit");
        closeConfirmation.setHeaderText("Confirm Exit");
        closeConfirmation.initModality(Modality.APPLICATION_MODAL);
        closeConfirmation.initOwner(Main.primaryStage);

        Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
        if (!ButtonType.OK.equals(closeResponse.get())) {
            event.consume();
        }
    };
}
