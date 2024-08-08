import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Homework_01 extends Application {
    //Create borderpane, pane, and textfield
    BorderPane borderPane = new BorderPane();
    Pane pane = new Pane();
    TextField text = new TextField();

    //int order will store current order of H tree
    int order = 1;
    //set default height and width for window
    int HEIGHT = 800;
    int WIDTH = 800;

    @Override
    public void start(Stage primaryStage) throws Exception {
        setUI();
        Scene scene = new Scene(borderPane, WIDTH, HEIGHT);
        primaryStage.setTitle("H-Tree Fractal"); // Set the stage title
        primaryStage.setScene(scene);
        primaryStage.show();

        pane.requestFocus();
    }


    //this method controls the user interface components
    private void setUI() {
        HBox hBox = new HBox();
        //Prompt user for H-Tree Order
        hBox.getChildren().addAll(new Label(" Enter order(int) for H-Tree: "), text);
        hBox.setAlignment(Pos.CENTER);
        text.setPrefColumnCount(10);
        text.setAlignment(Pos.CENTER);

        //action is set when enter key is pressed
        text.setOnAction(e -> {
            //set order value based on text input
            order = Integer.parseInt(text.getText());
            //paint/repaint H-Tree according to order value
            paint();
        });
        borderPane.setBottom(hBox);
        borderPane.setCenter(pane);

        //use listeners to keep track of height and width changes
        pane.widthProperty().addListener((ov, oldVal, newVal) -> paint());
        pane.heightProperty().addListener((ov, oldVal, newVal) -> paint());
    }

    //paints H-Tree
    private void paint() {
        //clears existing shapes from 'pane'
        pane.getChildren().clear();
        int w = (int) pane.getWidth();
        int h = (int) pane.getHeight();

        //if input is 0 then leave it blank
        if (w == 0 || h == 0) {
            return;
        }

        //if order is greater than 0 draw h tree according to order
        if (order > 0) {
            //recursive method to draw the h tree
            draw(order, w / 2, h / 2, Math.min(w, h) / 2);
        }
    }

    //Draw a single H shape to begin the tree
    private void drawH(double x, double y, double size) {
        //calculate coordinate for the four points of h tree
        double x1 = x - size / 2;
        double x2 = x + size / 2;
        double y1 = y - size / 2;
        double y2 = y + size / 2;

        //line1, line2, and line3 are lines that form the H shape
        //horizontal line
        Line line1 = new Line(x1, y, x2, y);
        //vertical line (left side)
        Line line2 = new Line(x1, y1, x1, y2);
        //vertical line (right side)
        Line line3 = new Line(x2, y1, x2, y2);

        //add all lines to the pane
        pane.getChildren().addAll(line1, line2, line3);
    }

    //recursively draw H-Trees
    private void draw(int order, double x, double y, double size) {
        //only one base case for this method
        if (order == 0) {
            return;
        }

        //draw H based on specified x, y, and size
        drawH(x, y, size);

        //calculate coordinate for the four points of h tree
        double x1 = x - size / 2;
        double x2 = x + size / 2;
        double y1 = y - size / 2;
        double y2 = y + size / 2;

        //bottom left
        draw(order - 1, x1, y1, size / 2);

        //top left
        draw(order - 1, x1, y2, size / 2);

        //bottom right
        draw(order - 1, x2, y1, size / 2);

        //top right
        draw(order - 1, x2, y2, size / 2);
    }

    public static void main(String[] args) {
        launch(args);
    }
}