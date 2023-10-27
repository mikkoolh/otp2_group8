package com.automaatio.controller.mainpage.clickActions;

import com.automaatio.model.database.Device;
import com.automaatio.utils.CacheSingleton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class RoutinesClick implements ClickActions {
    private CacheSingleton cache = CacheSingleton.getInstance();

    @Override
    public void onExpandClick(Object object) {
        cache.setDevice((Device) object);
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/routine.fxml"));
            Parent newView = loader.load();
            cache.getMainPane().getChildren().clear();
            cache.getMainPane().getChildren().add(newView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDeleteClick(Object object, VBox mainVBox, VBox boxToDelete) {

    }
}
