package mathia;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

public class GridDisplay extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        final AtomicReference<World> model = new AtomicReference<>();
        model.set(World.constructJava(Collections.emptyList()));

        final GridPane grid = new GridPane();
        grid.setHgap(0);
        grid.setVgap(0);
        grid.setMinHeight(50 * 5);
        grid.setMinWidth(50 * 5);
//        grid.setPadding(new Insets(0, 5, 0, 5));
//        Rectangle rect = new Rectangle(5, 5, Paint.valueOf("black"));
//        grid.add(rect, 50, 50);
        initGrid(grid);

        Button btnTick = new Button();
        btnTick.setText("tick");
        btnTick.setOnAction(event -> {
            initGrid(grid);
            World next = model.updateAndGet(World::tick);
            draw(next, grid);
        });

        Button btnMore = new Button();
        btnMore.setText("More");
        btnMore.setOnAction(event -> {
            initGrid(grid);
            World next = model.updateAndGet((world) -> world.randomParticles(10));
            draw(next, grid);
        });

        VBox root = new VBox();
        HBox buttons = new HBox();
        buttons.getChildren().add(btnTick);
        buttons.getChildren().add(btnMore);
        root.getChildren().add(grid);
        root.getChildren().add(buttons);

        Scene scene = new Scene(root, 500, 500);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void draw(World next, GridPane grid) {
//        Platform.runLater;
        for (Powder powder : next.getParticles()) {
            Rectangle rect = new Rectangle(5, 5, Paint.valueOf("black"));
            grid.add(rect, ((int) powder.pos().getX()), ((int) powder.pos().getY()));
        }
    }

    private void initGrid(GridPane grid) {
        for (int x = 0; x <= 50; x++) {
            for (int y = 0; y <= 50; y++) {
                Rectangle curRect = new Rectangle(5, 5, Paint.valueOf("grey"));
                grid.add(curRect, x, y);
            }
        }
    }
}