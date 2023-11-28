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


@ExtendWith(ApplicationExtension.class)
public abstract class AbstractTestFX extends ApplicationTest {

    @BeforeEach
    public void setUpClass() throws Exception {
        ApplicationTest.launch(GraphicalUI.class);
    }

    @Override
    public void start(Stage stage) throws Exception{
        stage.show();
    }

    @AfterEach
    public void afterEachTest() throws TimeoutException {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Override
    public void init() throws Exception {
        Platform.setImplicitExit(false);
        FxToolkit.registerPrimaryStage();
    }
    
    @Override
    public void destroy() {
        FxToolkit.cleanupStages();
    }
}
