package testFX;

import com.automaatio.view.GraphicalUI;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.concurrent.TimeoutException;

/**
 * The {@code AbstractTestFX} class serves as a base class for TestFX-based UI tests.
 * It provides common setup and teardown routines for tests that interact with
 * JavaFX components.
 *
 * This abstract class is designed to be extended by specific test classes that require
 * a JavaFX environment. It handles the launching and hiding of the main application stage
 * and provides utility methods for releasing keyboard and mouse actions.
 */
@ExtendWith(ApplicationExtension.class)
public abstract class AbstractTestFX extends ApplicationTest {

    /**
     * Sets up the JavaFX testing environment by launching the main application class.
     * This method is called before each test execution.
     *
     * @throws Exception if there is an error during setup.
     */
    @BeforeEach
    public void setUpClass() throws Exception {
        ApplicationTest.launch(GraphicalUI.class);
    }

    /**
     * Initializes the primary stage for the test. This method is called before
     * each test execution to set up the primary stage.
     *
     * @param stage The primary stage for this application.
     * @throws Exception if there is an error during stage setup.
     */
    @Override
    public void start(Stage stage) throws Exception{
        stage.show();
    }

    /**
     * Cleans up after each test. This method hides the primary stage and releases
     * any keyboard and mouse actions that may have been pressed or clicked during the test.
     *
     * @throws TimeoutException if there is a timeout during cleanup.
     */
    @AfterEach
    public void afterEachTest() throws TimeoutException {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }
}
