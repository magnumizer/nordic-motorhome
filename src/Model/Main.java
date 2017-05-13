package Model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application
{
    public static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception
    {
        primaryStage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Login.fxml"));
        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("Nordic Motorhome Rental");
        primaryStage.setScene(scene);
        scene.getStylesheets().add("/GUI/CSS/theme.css");
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setOnCloseRequest(StageHandler.confirmCloseEventHandler);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
