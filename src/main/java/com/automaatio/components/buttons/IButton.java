package com.automaatio.components.buttons;

import com.automaatio.controller.mainpage.clickActions.ClickActions;
import javafx.scene.Node;

/**
 * The {@code IButton} interface defines a contract for creating UI button elements.
 * Implementing classes are expected to provide a specific implementation of the
 * {@code create} method to generate a {@link Node} (typically a {@link javafx.scene.control.Button})
 * with associated actions and properties.
 *
 * This interface allows for flexible creation of buttons with different behaviors and styles,
 * depending on the implementing class.
 */
public interface IButton {

    /**
     * Creates a UI element (typically a button) associated with a specific object and
     * click actions. The nature of the UI element, its behavior, and appearance are
     * determined by the implementing class.
     *
     * @param object The object associated with the button, which can influence the button's behavior and appearance.
     * @param clickActions The {@code ClickActions} instance defining the actions to be performed on button clicks.
     * @return A {@link Node} representing the created UI element.
     */
    Node create(Object object, ClickActions clickActions);
}
