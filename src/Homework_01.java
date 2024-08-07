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
    //required components for user interface: borderpane(layout), pane(display H), textfield(enter order)
    BorderPane borderPane = new BorderPane();
    Pane pane = new Pane();
    TextField text = new TextField();

    //variable order will store current order of H tree
    int order = 0;
    int WIDTH = 500, HEIGHT = 550;

    @Override
    public void start(Stage primaryStage) throws Exception {
        setUI();
        Scene scene = new Scene(borderPane, WIDTH, HEIGHT);
        primaryStage.setTitle("H Tree");
        primaryStage.setScene(scene);
        primaryStage.show();

        pane.requestFocus();
    }


    //this method controls the user interface components
    private void setUI() {
        HBox hBox = new HBox();
        //label and text field will prompt user to enter order of H tree
        hBox.getChildren().addAll(new Label(" Enter an order: "), text);
        hBox.setAlignment(Pos.CENTER);
        text.setPrefColumnCount(10);
        text.setAlignment(Pos.CENTER);

        //action is set when enter key is pressed
        text.setOnAction(e -> {
            //parses the value from text field
            order = Integer.parseInt(text.getText());
            //method will repeat process on new order to continue h tree
            paint();
        });
        borderPane.setBottom(hBox);
        borderPane.setCenter(pane);

        //part of self eval q3 --> observe the width and height changes in the pane which will trigger paint if changed
        pane.widthProperty().addListener((ov, oldVal, newVal) -> paint());
        pane.heightProperty().addListener((ov, oldVal, newVal) -> paint());
    }

    //self eval q3 --> this method will repeat the process and continue to add h to build h tree
    private void paint() {
        //clears existing shapes from 'pane'
        pane.getChildren().clear();
        int w = (int) pane.getWidth();
        int h = (int) pane.getHeight();

        //if input is 0 then leave it blank
        if (w == 0 || h == 0) {
            return;
        }

        //if its greater than 0 draw out the h tree
        if (order > 0) {
            //recursive method to draw the h tree
            draw(order, w / 2, h / 2, Math.min(w, h) / 2);
        }
    }

    //single H shape: self eval q1 --> this method will draw a single H shape to begin the tree
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

        //add all tje lines to the pane
        pane.getChildren().addAll(line1, line2, line3);
    }

    //recursive method: self eval q2 --> draw/display h shapes
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