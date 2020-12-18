package frogger.navigation;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
public class NavigationTest {
    Scene scene;
    @Start
    public void start(Stage stage) {
        scene = new Scene(new Pane(), 100, 100);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void getCorrectNavControllerTest() {
        Assertions.assertEquals(scene, Navigation.getNavController(scene).getScene());
    }
}
