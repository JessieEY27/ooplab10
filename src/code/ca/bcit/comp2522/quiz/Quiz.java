package ca.bcit.comp2522.quiz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;
import java.util.Scanner;

/**
 * Provides a quiz with questions for the user.
 *
 * @author Damon Cao
 * @author Jessie Yuen
 * @version 1.0
 */
public class Quiz
{

    private static final int QUESTION_INDEX;
    private static final int ANSWER_INDEX;
    private static final int DEFAULT_QUESTION_INDEX;
    private static final int DEFAULT_SCORE;
    private static final int DEFAULT_ROUNDS_COMPLETED;
    private static final List<String[]> QUIZ_LIST;
    private static final int MIN_QUIZ_INDEX;
    private static final int MAX_QUIZ_INDEX;

    private int currentQuestionIndex;
    private final List<String[]> wrongAnswerList;
    private int score;
    private int roundsCompleted;

    static
    {
        try
        {
            QUIZ_LIST = new ArrayList<>();
            generateQuizList();
            QUESTION_INDEX = 0;
            ANSWER_INDEX = 1;
            DEFAULT_QUESTION_INDEX = 0;
            DEFAULT_SCORE = 0;
            DEFAULT_ROUNDS_COMPLETED = 0;
            MIN_QUIZ_INDEX = 0;
            MAX_QUIZ_INDEX = QUIZ_LIST.size();
        }
        catch(final FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Constructs a Quiz object
     */
    public Quiz()
    {
        currentQuestionIndex = DEFAULT_QUESTION_INDEX;
        wrongAnswerList = new ArrayList<>();
        score = DEFAULT_SCORE;
        roundsCompleted = DEFAULT_ROUNDS_COMPLETED;
    }

    /**
     * Generates questions for the quiz game
     * @return quiz questions
     */
    public String generateQuestion()
    {
        final RandomGenerator randomQuestionGenerator;
        final int generatedQuestionIndex;

        randomQuestionGenerator = RandomGenerator.getDefault();
        generatedQuestionIndex = randomQuestionGenerator.nextInt(MIN_QUIZ_INDEX, MAX_QUIZ_INDEX);

        currentQuestionIndex = generatedQuestionIndex;

        return QUIZ_LIST.get(generatedQuestionIndex)[QUESTION_INDEX];
    }

    /**
     * Checks whether answer is true or false.
     *
     * @param guess the user's guess
     * @return whether user's guess is true or false
     */
    public boolean checkAnswer(final String guess)
    {
        roundsCompleted++;
        if(guess.trim().equals(QUIZ_LIST.get(currentQuestionIndex)[ANSWER_INDEX]))
        {
            score++;
            return true;
        }
        else
        {
            wrongAnswerList.add(QUIZ_LIST.get(currentQuestionIndex));
            return false;
        }
    }

    /**
     * Fetches the list of incorrect answers by the user.
     *
     * @return summary of wrong answers
     */
    public String getWrongAnswerSummary()
    {
        String stringifiedWrongAnswerList;

        if(wrongAnswerList.isEmpty())
        {
            stringifiedWrongAnswerList = "All answers correct";
        }
        else
        {
            stringifiedWrongAnswerList = "Missed Questions:\n\n";
            for(final String[] questionAnswer : wrongAnswerList)
            {
                stringifiedWrongAnswerList += "Q: " +
                        questionAnswer[QUESTION_INDEX] +
                        "\nA: " +
                        questionAnswer[ANSWER_INDEX] +
                        "\n\n";
            }
        }

        return stringifiedWrongAnswerList;
    }

    /**
     * Gets the user's score.
     *
     * @return user's score
     */
    public int getScore()
    {
        return score;
    }

    /**
     * Gets the number of game rounds completed.
     *
     * @return number of rounds completed
     */
    public int getRoundsCompleted()
    {
        return roundsCompleted;
    }

    /**
     * Resets the quiz game.
     */
    public void resetQuiz()
    {
        wrongAnswerList.clear();
        score = DEFAULT_SCORE;
        roundsCompleted = DEFAULT_ROUNDS_COMPLETED;
    }

    /**
     * Produces a quiz list.
     *
     * @throws FileNotFoundException
     */
    private static void generateQuizList()
            throws FileNotFoundException
    {
        final File quizFile;
        final Scanner quizFileScanner;

        quizFile = new File("ca/bcit/comp2522/quiz/quiz.txt");
        quizFileScanner = new Scanner(Quiz.class.getResourceAsStream("quiz.txt"));

        while(quizFileScanner.hasNextLine())
        {
            QUIZ_LIST.add(quizFileScanner.nextLine()
                    .trim()
                    .split("\\|", 2));
        }

        quizFileScanner.close();
    }
}
