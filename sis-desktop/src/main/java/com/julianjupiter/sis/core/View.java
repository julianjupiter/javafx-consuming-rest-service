package com.julianjupiter.sis.core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

public final class View<T extends Controller, U extends Parent> {
    private static final String FXML_EXTENSION = ".fxml";
    private final Class<? extends Controller> controllerClass;
    private final ResourceBundle resourceBundle;
    private final Map<Class<T>, Callable<?>> controllerFactory;
    private FXMLLoader loader;
    private U u;

    public View(Class<? extends Controller> controllerClass, ResourceBundle resourceBundle) {
        this(controllerClass, resourceBundle, Map.of());
    }

    public View(Class<? extends Controller> controllerClass, ResourceBundle resourceBundle, Map<Class<T>, Callable<?>> controllerFactory) {
        this.controllerClass = controllerClass;
        this.resourceBundle = resourceBundle;
        this.controllerFactory = controllerFactory;
        this.load();
    }

    private void load() {
        this.loader = new FXMLLoader(this.fxmlUrl(), this.resourceBundle);
        this.controllerFactory();
        try {
            this.u = this.loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private URL fxmlUrl() {
        return this.controllerClass.getResource(this.controllerClass.getSimpleName() + FXML_EXTENSION);
    }

    public U component() {
        return u;
    }

    public T controller() {
        return this.loader.getController();
    }

    private void controllerFactory() {
        this.loader.setControllerFactory(param -> {
            Callable<?> callable = controllerFactory.get(param);
            if (callable == null) {
                try {
                    return param.getConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                         InvocationTargetException exception) {
                    throw new IllegalStateException(exception);
                }
            } else {
                try {
                    return callable.call();
                } catch (Exception exception) {
                    throw new IllegalStateException(exception);
                }
            }
        });
    }
}