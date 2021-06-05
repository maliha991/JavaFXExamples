package guifxexamples;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


// You DO NOT have to hand this in for the post-lecture submission. This is an optional challenge
//	to work on for practice.

// Challenge: Copy/paste your response from LayoutExercise.java and add appropriate event handlers
//				to make a functional calculator app that computes results based on button presses
//				and displays the result in the textfield area
public class JavaFXCalculator extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        initUI(stage);
    }

    private TextField tfDisplay; // display textfield
    private Button[] btns; // 16 buttons
    private String[] btnLabels = { // Labels of 16 buttons
            "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "x", "C", "0", "=", "/" };
    String concat;
    int result;
    Pattern pattern = Pattern.compile("/|\\+|x|-");

    private void initUI(Stage stage) {

        // TODO: Complete this code to look something like the JavaFXCalculator.png
        // image included in this repo

        // It's okay if it's not exact, as long as the main structure -- a long
        // textfield at the top, followed by a grid of buttons - is there

        // To get it to look closer to the picture though, try playing around with the
        // padding, and use ColumnConstraints:
        // 		http://www-acad.sheridanc.on.ca/~jollymor/prog24178/javafx3b.html

        // Setup the Display TextField
        tfDisplay = new TextField("0");
        tfDisplay.setEditable(false);
        result = Integer.parseInt(tfDisplay.getText());


        // YOUR CODE HERE (Use LayoutComplex.java and the other layout pane resources as reference)

        GridPane root = new GridPane();
        Scene scene = new Scene(root, 400, 480);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(10,0,0,10));
        root.add(tfDisplay, 0,0);
        GridPane.setColumnSpan(tfDisplay, 4);
        tfDisplay.setAlignment(Pos.CENTER_RIGHT);
        GridPane.setHalignment(tfDisplay, HPos.CENTER);


        btns = new Button[btnLabels.length];

        for (int i=0; i<btns.length; i++){
            Button button = new Button(btnLabels[i]);
            button.setPadding(new Insets(5,20,5,20));
            btns[i] = button;
            root.add(btns[i], i%4, (i/4)+1);
        }

        for (int i=0; i<btns.length; i++){

            btns[i].setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {

                    concat = tfDisplay.getText();
                    String value = ((Button) (event.getSource())).getText();
                    Matcher matcher = pattern.matcher(value);
                    char c = concat.charAt(concat.length() - 1);
                    String symbol = Character.toString(c);
                    Matcher matcher1 = pattern.matcher(symbol);

                    if (matcher.find()) {

                        if (!concat.contains(value)) concat = concat + value;
                        else if (matcher1.find()) concat = concat.replace(c, value.charAt(0));
                        tfDisplay.setText(concat);

                    } else if (value.equals("=")) {

                        concat = Integer.toString(result);
                        tfDisplay.setText(concat);

                    } else if (value.equals("C")) {
                        concat = "";
                        tfDisplay.setText("0");

                    } else {
                        if (concat.equals("0")) concat = concat.replace("0", value);
                        else concat = concat + value;
                        calculation();
                        tfDisplay.setText(concat);
                    }
                }

            });
        }


        stage.setTitle("JavaFX Calculator");
        stage.setScene(scene);
        stage.show();

    }

    public void calculation() {

        Matcher matcher = pattern.matcher(concat);

        if (matcher.find()) {
            String symbol = matcher.group(0);
            String [] numbers = concat.split(symbol, 2);

            switch (symbol){
                case "+" :
                    result = Integer.parseInt(numbers[0]) + Integer.parseInt(numbers[1]);
                    break;
                case "-" :
                    result = Integer.parseInt(numbers[0]) - Integer.parseInt(numbers[1]);
                    break;
                case "/" :
                    result = Integer.parseInt(numbers[0]) / Integer.parseInt(numbers[1]);
                    break;
                case "x" :
                    result = Integer.parseInt(numbers[0]) * Integer.parseInt(numbers[1]);
                    break;
            }
        }
    }
}

