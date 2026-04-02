package ca.bcit.comp2522.quiz;

import javafx.application.Application;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Creates UI for a simple quiz app.
 *
 * @author Damon Cao
 * @author Jessie Yuen
 * @version 1.0
 */
public class QuizApp extends Application
{

    private static final int SCENE_WIDTH = 800;
    private static final int SCENE_HEIGHT = 450;
    private static final int MAX_ROUNDS = 10;
    private static final Quiz QUIZ = new Quiz();

    /**
     * Starts the JavaFX application and initializes the stage.
     *
     * @param primaryStage the JavaFX's primary stage
     */
    @Override
    public void start(final Stage primaryStage)
    {
        final VBox primaryVBox;
        primaryVBox = createVBox();

        final Scene scene;
        scene = new Scene(primaryVBox,
                SCENE_WIDTH,
                SCENE_HEIGHT);

        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Simple Quiz App");
        primaryStage.show();
    }

    private static VBox createVBox()
    {
        final VBox myVBox;
        final Button startButton;
        final Label questionField;
        final TextField answerField;
        final Label scoreLabel;
        final Button submitButton;
        final TextArea wrongAnswerSummary;

        myVBox = new VBox();
        startButton = new Button("Start Quiz!");
        questionField = new Label("Press 'Start Quiz' to begin!");
        answerField = new TextField();
        scoreLabel = new Label("Score: " + QUIZ.getScore());
        submitButton = new Button("Submit!");
        wrongAnswerSummary = new TextArea();

        myVBox.getStyleClass().add("vbox");

        startButton.getStyleClass().add("button");
        startButton.setOnAction(event -> {
            submitButton.setDisable(false);
            submitButton.setDefaultButton(true);
            answerField.setDisable(false);
            startButton.setDisable(true);
            questionField.setText(QUIZ.generateQuestion());
            myVBox.getChildren().remove(wrongAnswerSummary);
            scoreLabel.setText("Score: " + QUIZ.getScore());
        });

        answerField.setPromptText("Answer: ");
        answerField.setDisable(true);
        answerField.getStyleClass().add("text-field");

        scoreLabel.getStyleClass().add("label");

        submitButton.getStyleClass().add("button");
        submitButton.setDisable(true);
        submitButton.setOnAction(event -> {
            if(QUIZ.checkAnswer(answerField.getText()))
            {
                scoreLabel.setText("Score: " + QUIZ.getScore());
            }
            if(QUIZ.getRoundsCompleted() == MAX_ROUNDS)
            {
                questionField.setText("Quiz Complete! Final score is " + QUIZ.getScore() + "/10");
                myVBox.getChildren().add(wrongAnswerSummary);
                wrongAnswerSummary.setText(QUIZ.getWrongAnswerSummary());
                submitButton.setDisable(true);
                submitButton.setDefaultButton(false);
                answerField.setDisable(true);
                startButton.setDisable(false);
                QUIZ.resetQuiz();
            }
            else
            {
                questionField.setText(QUIZ.generateQuestion());
            }
            answerField.clear();
        });

        myVBox.getChildren().addAll(
                questionField,
                answerField,
                submitButton,
                startButton,
                scoreLabel
        );

        return myVBox;
    }

    /**
     * Drives the program.
     *
     * @param args unused
     */
    public static void main(final String[] args) {
        Application.launch(args);
    }
}
