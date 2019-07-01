import java.util.Date;

public class OutputTest {

    private Question[] iQuestions;
    private Date startTime;
    private Date finishTime;
    private double totalTimeTest;
    private double meanTime;
    private String score;
    private int mark;
    private double standartDeviationTimePerAnswer;
    private int correctAnswers;
    private double correctAnswerPercentage;
    private int incorrectAnswers;
    private double incorrectAnswerPercentage;
    private double percentageCorrectMultipleChoiceQuestions;
    private double percentageIncorrectMultipleChoiceQuestions;
    private double percentageNumberOfNumericQuestions;
    private double meanTimePerQuestion;

    /**
     * Constructor to save all the fields properly in the file
     * @param test Test to output his statistics in the file
     */
    OutputTest(Test test) {

        iQuestions = test.getAllQuestions();
        score = test.calculateScore();
        mark = test.getTotalMark();
        startTime = test.getStartTime();
        finishTime = test.getFinishTime();
        totalTimeTest = test.getBetterStatistics().totalTimeTestInSeconds();
        meanTime = test.getTestStatistics().meanTimePerAnswer();
        standartDeviationTimePerAnswer = test.getTestStatistics().standardDeviationTimePerAnsewer();
        correctAnswers = test.getTestStatistics().correctAnswer();
        correctAnswerPercentage = test.getTestStatistics().correctAnswerPecentage();
        incorrectAnswers = test.getTestStatistics().incorrectAnswer();
        incorrectAnswerPercentage = test.getTestStatistics().incorrectAnswerPecentage();
        percentageCorrectMultipleChoiceQuestions =  test.getBetterStatistics().percentageCorrectMultipleChoiceQuestions();
        percentageIncorrectMultipleChoiceQuestions = test.getBetterStatistics().percentageIncorrectMultipleChoiceQuestions();
        percentageNumberOfNumericQuestions = test.getBetterStatistics().percentageNumberOfNumericQuestions();
        meanTimePerQuestion = test.getBetterStatistics().meanTimePerQuestion();

    }
}
