package com.automaatio.components.buttons;

import com.automaatio.controller.mainpage.clickActions.ClickActions;
import javafx.scene.Node;

/**
 * The IButton interface declares common methods shared by buttons.
 *
 * @author Elmo Erla, Mikko Hänninen, Nikita Nossenko, Matleena Kankaanpää
 * @version 1.0
 */
public interface IButton {

    /**
     * Creates a button associated with an object (the target being clicked)
     * and an action to be executed.
     *
     * @param object        The object clicked by the user
     * @param clickActions  The action to be executed when the button is clicked
     * @return              A
     */
    Node create(Object object, ClickActions clickActions);
}
