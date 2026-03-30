import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;
import java.util.Scanner;

public class Quiz {

    private static final int QUESTION_INDEX;
    private static final int ANSWER_INDEX;
    private static final int DEFAULT_QUESTION_INDEX;
    private static final List<String[]> QUIZ_LIST;
    private static final int MIN_QUIZ_INDEX;
    private static final int MAX_QUIZ_INDEX;

    private int currentQuestionIndex;
    private final List<String[]> wrongAnswerList;

    static
    {
        try
        {
            QUIZ_LIST = new ArrayList<>();
            generateQuizList();
            QUESTION_INDEX = 0;
            ANSWER_INDEX = 1;
            DEFAULT_QUESTION_INDEX = 0;
            MIN_QUIZ_INDEX = 0;
            MAX_QUIZ_INDEX = QUIZ_LIST.size();
        }
        catch (final FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

    public Quiz()
    {
        currentQuestionIndex = DEFAULT_QUESTION_INDEX;
        wrongAnswerList = new ArrayList<>();

    }

    public String generateQuestion()
    {
        final RandomGenerator randomQuestionGenerator;
        final int generatedQuestionIndex;

        randomQuestionGenerator = RandomGenerator.getDefault();
        generatedQuestionIndex = randomQuestionGenerator.nextInt(MIN_QUIZ_INDEX, MAX_QUIZ_INDEX);

        currentQuestionIndex = generatedQuestionIndex;

        return QUIZ_LIST.get(generatedQuestionIndex)[QUESTION_INDEX];
    }

    public boolean checkAnswer(final String guess)
    {
        if (guess.trim().equals(QUIZ_LIST.get(currentQuestionIndex)[ANSWER_INDEX]))
        {
            return true;
        }
        else
        {
            wrongAnswerList.add(QUIZ_LIST.get(currentQuestionIndex));
            return false;
        }
    }

    public String getWrongAnswerList()
    {
        String stringifiedWrongAnswerList;
        stringifiedWrongAnswerList = "";

        for (final String[] questionAnswer : wrongAnswerList)
        {
            stringifiedWrongAnswerList += "Question: " +
                    questionAnswer[QUESTION_INDEX] +
                    " Answer: " +
                    questionAnswer[ANSWER_INDEX] +
                    "\n";
        }

        return stringifiedWrongAnswerList;
    }

    public void clearWrongAnswerList()
    {
        wrongAnswerList.clear();
    }

    private static void generateQuizList()
            throws FileNotFoundException
    {
        final File quizFile;
        final Scanner quizFileScanner;

        quizFile = new File("quiz.txt");
        quizFileScanner = new Scanner(quizFile);

        while (quizFileScanner.hasNextLine())
        {
            QUIZ_LIST.add(quizFileScanner.nextLine()
                    .trim()
                    .split("\\|", 2));
        }

        quizFileScanner.close();
    }
}
