package se233.chapter4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se233.chapter4.controller.DrawingLoop;
import se233.chapter4.controller.GameLoop;
import se233.chapter4.view.Platform;

//Imports are omitted
//Imports are omitted
public class Launcher extends Application {
    public static void main(String[] args) { launch(args); }
    @Override
    public void start(Stage stage) {

        // Initialize Log4j 3 using the configuration file
        System.setProperty("log4j.configurationFile", "log4j2.yaml");

        Platform platform = new Platform();
        GameLoop gameLoop = new GameLoop(platform);
        DrawingLoop drawingLoop = new DrawingLoop(platform);
        Scene scene = new Scene(platform, platform.WIDTH, platform.HEIGHT);
        scene.setOnKeyPressed(event-> platform.getKeys().add(event.getCode()));
        scene.setOnKeyReleased(event -> platform.getKeys().remove(event.getCode()));
        stage.setTitle("platformer");
        stage.setScene(scene);
        stage.show();
        (new Thread(gameLoop)).start();
        (new Thread(drawingLoop)).start();
    }
}