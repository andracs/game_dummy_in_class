import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.settings.GameSettings;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.Map;

import static javafx.application.Application.launch;

/**
 * FXGL example in class
 *
 * Based on https://github.com/AlmasB/FXGL/wiki/Basic-Game-Example
 *
 */

public class Game extends GameApplication {

    private Entity player1;
    private Entity player2;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(200);
        settings.setHeight(200);
        settings.setTitle("EMIL");
        settings.setVersion("2.0");
    }

    @Override
    protected void initGame() {
        player1 = Entities.builder()
                .at(10, 10)
                .viewFromNode(new Rectangle(5, 5, Color.ORANGERED))
                .buildAndAttach(getGameWorld());
        player2 = Entities.builder()
                .at(100, 100)
                .viewFromNode(new Rectangle(5, 5, Color.GREEN))
                .buildAndAttach(getGameWorld());
    }

    @Override
    protected void initInput() {
        Input input = getInput();

        input.addAction(new UserAction("Move Right") {
            @Override
            protected void onAction() {
                player1.translateX(1);
                getGameState().increment("pixelsMoved", +5);
            }
        }, KeyCode.G);

        input.addAction(new UserAction("Move Left") {
            @Override
            protected void onAction() {
                player1.translateX(-5); // move left 5 pixels
            }
        }, KeyCode.A);

        input.addAction(new UserAction("Move Up") {
            @Override
            protected void onAction() {
                player1.translateY(-5); // move up 5 pixels
            }
        }, KeyCode.W);

        input.addAction(new UserAction("Move Down") {
            @Override
            protected void onAction() {
                player1.translateY(5); // move down 5 pixels
            }
        }, KeyCode.S);
    }

    @Override
    protected void initUI() {
        Text textPixels = new Text();
        textPixels.fontProperty().setValue(Font.font("Times"));
        textPixels.setUnderline(true);
        textPixels.setTranslateX(20); // x = 50
        textPixels.setTranslateY(20); // y = 100

        getGameScene().addUINode(textPixels); // add to the scene graph
        textPixels.textProperty().bind(getGameState().intProperty("pixelsMoved").asString());

    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("pixelsMoved", 0);
    }





    public static void main(String[] args) {
        launch(args);
    }
}
