import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.awt.*;

public class QuizApp extends Application {

    @Override
    public void start(final Stage primaryStage)
    {

        VBox myVBox;
        myVBox = new VBox(10);
        myVBox.getStyleClass().add("vbox");

        Button startButton;
        startButton = new Button("Start Quiz!");
        startButton.getStyleClass().add("button");

        Label startLabel;
        startLabel = new Label("Press 'Start Quiz' to begin!");
        startLabel.getStyleClass().add("label");

        TextField answerField;
        answerField = new TextField();
        answerField.setPromptText("Answer: ");
        answerField.getStyleClass().add("text-field");

        Button submitButton;
        submitButton = new Button("Submit!");
        submitButton.getStyleClass().add("button");

        Label scoreLabel;
        scoreLabel = new Label("Score: ");
        scoreLabel.getStyleClass().add("label");


        myVBox.getChildren().addAll(
                startButton,
                startLabel,
                answerField,
                submitButton,
                scoreLabel
        );

        Scene scene = new Scene(myVBox, 200, 150);

        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Simple Quiz App");
        primaryStage.show();
    }

    public static void main(final String[] args) {
        Application.launch(args);
    }
}
