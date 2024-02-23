package com.julianjupiter.sis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.julianjupiter.sis.controller.MainController;
import com.julianjupiter.sis.core.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

/**
 * JavaFX App
 */
public class SisApplication extends Application {
    private static final System.Logger LOGGER = System.getLogger(SisApplication.class.getName());
    private static final String CONFIG_FILE = "application.yml";
    private Config config;
    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        this.loadConfig();
        this.primaryStage = stage;
        this.initMain();
    }

    private void loadConfig() {
        var objectMapper = new ObjectMapper(new YAMLFactory());

        URL url = this.getClass().getClassLoader().getResource(CONFIG_FILE);
        try {
            this.config = objectMapper.readValue(url, Config.class);
        } catch (Exception exception) {
            LOGGER.log(System.Logger.Level.ERROR, () -> "Error... " + exception.getMessage());
            LOGGER.log(System.Logger.Level.ERROR, () -> "Exiting...");
            System.exit(1);
        }
    }

    private void initMain() {
        this.primaryStage.setMaximized(false);
        this.primaryStage.setResizable(false);
        Map<Class<MainController>, Callable<?>> mainControllerFactory = Map.of(
                MainController.class,
                () -> new MainController(this.config, this.primaryStage)
        );
        View<MainController, VBox> view = new View<>(MainController.class, resourceBundle(this.config), mainControllerFactory);
        VBox vBox = view.component();
        var scene = new Scene(vBox);
        this.primaryStage.setScene(scene);
        this.primaryStage.setTitle(this.config.applicationName());
        this.primaryStage.show();
    }

    public static ResourceBundle resourceBundle(Config config) {
        return ResourceBundle.getBundle(config.messages(), config.locale());
    }

    public static void main(String[] args) {
        launch();
    }

}