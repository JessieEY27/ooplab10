import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class QuizApp extends Application {

    private static final int SCENE_WIDTH = 800;
    private static final int SCENE_HEIGHT = 450;
    private static final int DEFAULT_SCORE = 0;

    @Override
    public void start(final Stage primaryStage)
    {
        final Quiz quiz;
        quiz = new Quiz();

        final VBox myVBox;
        myVBox = new VBox(10);
        myVBox.getStyleClass().add("vbox");

        final Button startButton;
        startButton = new Button("Start Quiz!");
        startButton.getStyleClass().add("button");

        final Label startLabel;
        startLabel = new Label("Press 'Start Quiz' to begin!");
        startLabel.getStyleClass().add("label");

        final Label questionField;
        questionField = new Label(quiz.generateQuestion());

        final TextField answerField;
        answerField = new TextField();
        answerField.setPromptText("Answer: ");
        answerField.getStyleClass().add("text-field");

        final Button submitButton;
        submitButton = new Button("Submit!");
        submitButton.getStyleClass().add("button");

        final int score;
        final Label scoreLabel;
        score = DEFAULT_SCORE;
        scoreLabel = new Label("Score: " + score);
        scoreLabel.getStyleClass().add("label");


        myVBox.getChildren().addAll(
                startButton,
                startLabel,
                questionField,
                answerField,
                submitButton,
                scoreLabel
        );

        final Scene scene;
        scene = new Scene(myVBox,
                        SCENE_WIDTH,
                        SCENE_HEIGHT);

        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Simple Quiz App");
        primaryStage.show();
    }

    public static void main(final String[] args) {
        Application.launch(args);
    }
}
