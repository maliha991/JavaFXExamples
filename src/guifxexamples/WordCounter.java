package guifxexamples;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WordCounter extends Application {

    private Button button1, button2;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        initUI(stage);
    }


    private void initUI(Stage stage) {

        TextArea text = new TextArea("Type here");
        Label label = new Label();
        button1 = new Button("Count Words");
        button2 = new Button("Count Characters");
        VBox pane = new VBox();

        // YOUR CODE HERE
        // ...

        pane.getChildren().addAll(text, button1, button2, label);
        button1.setOnAction(new Counter(text, label));
        button2.setOnAction(new Counter(text, label));

        Scene scene = new Scene(pane, 300, 300);

        stage.setTitle("Word Counter");
        stage.setScene(scene);
        stage.show();
    }
}


class Counter implements EventHandler<ActionEvent> {

    // YOUR CODE HERE ...

    private TextArea txt;
    private Label label;

    public Counter(TextArea txt, Label lbl){
        this.txt = txt;
        this.label = lbl;
    }

    public void handle(ActionEvent event){

        String text = txt.getText();
        String msg = ((Button) (event.getSource())).getText();
        System.out.println(text);
        switch (msg) {
            case "Count Words" :
                String words[] = text.split(" ");
                label.setText(words.length + " Word(s)");
                return;

            case "Count Characters"	:
                label.setText(text.length() + " Character(s)");
                return;
        }
    }
}
