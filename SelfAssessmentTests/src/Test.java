/*
 * Nome: André Filipe Neto Martins
 * Número: 8180483
 * Turma: T2
 */

import com.google.gson.*;
import interfaces.controller.IScoreStrategy;
import interfaces.controller.ITest;
import interfaces.controller.ITestStatistics;
import interfaces.exceptions.TestException;
import interfaces.models.IQuestion;
import interfaces.models.IQuestionMultipleChoice;
import interfaces.models.IQuestionNumeric;
import interfaces.models.IQuestionYesNo;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Test implements ITest {

    /**
     * Array that stores que questions
     */
    protected Question[] iQuestions;
    /**
     * Variable that declares the score strategy.
     * It was declared "transient" because its a reserved word that don't let pass this to write in the json file
     */
    private transient IScoreStrategy scoreStrategy;
    /**
     * Start time of the test , obtained from current time in the system
     */
    private final Date startTime;
    /**
     * Finish time of the test , obtained from current time in the system
     */
    private Date finishTime;

    /**
     * Constructor that create a score strategy object and a start time for the test
     */
    Test() {
        scoreStrategy = new ScoreStrategy();
        startTime = new Date();
    }

    /**
     * Adds a question to the first free position (null) of the data structure used to store questions
     *
     * @param iQuestion question to be added
     * @return true if the question has been added, otherwise false
     * @throws TestException if the question is null
     */
    @Override
    public boolean addQuestion(IQuestion iQuestion) throws TestException {

        if (iQuestion != null) {
            for (int i = 0; i < iQuestions.length; i++) {
                if (iQuestions[i] == null) {
                    iQuestions[i] = (Question) iQuestion;
                    return true;
                }
            }
        } else {
            throw new TestException("Question can't be null");
        }
        return false;
    }

    /**
     * Lookup a question at a specific position
     *
     * @param i position of the IQuestion to be fetched
     * @return question at the specified position
     * @throws TestException if there is no question at the specified position
     */
    @Override
    public IQuestion getQuestion(int i) throws TestException {
        for (int k = 0; k < iQuestions.length; k++) {
            if (iQuestions[k] != null && k == i) {
                return iQuestions[k];
            }
        }
        throw new TestException("No question at the specified position");
    }

    /**
     * Removes the first occurrence of a question from the data structure used to store questions.
     * This operation puts a null reference in the position previously occupied by the question
     *
     * @param i the position of the question to be removed
     * @return true if the question has been removed, otherwise false
     */
    @Override
    public boolean removeQuestion(int i) {
        return removeQuestion(iQuestions[i]);
    }

    /**
     * Removes the first occurrence of a question from the data structure used to store questions.
     * This operation puts a null reference in the position previously occupied by the question
     *
     * @param iQuestion the question to be removed
     * @return true if the question has been removed, otherwise false
     */
    @Override
    public boolean removeQuestion(IQuestion iQuestion) {
        Question[] tempQuestions = new Question[iQuestions.length - 1];
        int tempIndex = 0;
        for (Question question : iQuestions) {
            if (!question.equals(iQuestion)) {
                try {
                    tempQuestions[tempIndex] = question;
                    tempIndex++;
                } catch (IndexOutOfBoundsException e) {
                    return false;
                }
            }
        }
        iQuestions = tempQuestions;
        return true;
    }

    /**
     * Returns the number of questions in the test
     *
     * @return number of questions in the test
     */
    @Override
    public int numberQuestions() {

        return iQuestions.length;
    }

    /**
     * Verified if all questions in the test are marked as done.
     * Here is take the finish time too when the answer is done.
     *
     * @return true if all questions in the test are marked as done, false otherwise
     */
    @Override
    public boolean isComplete() {

        for (IQuestion iQuestion : iQuestions) {
            if (!iQuestion.isDone()) {
                return false;
            }
        }
        finishTime = new Date();
        return true;
    }

    /**
     * Gets an object that implements the contract ITestStatistics which calculates statistics from user answers
     *
     * @return an object which implements ITestStatistics of this test
     */
    @Override
    public ITestStatistics getTestStatistics() {
        return new TestStatistics(this);
    }

    /**
     * Loads all questions in the test from a text file.
     * Here is initialized the array with the questions with the size of the jsonArray
     * It have a switch case to fill the question by its type in a private methods defined down this class.
     * It have also a try catch to catch if isn't found a file
     * @param s the path to the text file
     * @return true if the file was successfully loaded all questions in the test, false otherwise
     * @throws TestException if there is no question at the specified position
     */
    @Override
    public boolean loadFromJSONFile(String s) throws TestException {

        JsonParser parser = new JsonParser();

        try {
            JsonElement jsonElement = parser.parse(new FileReader(s));
            JsonArray jsonArray = jsonElement.getAsJsonArray();

            // Initialize array with the number of questions
            iQuestions = new Question[jsonArray.size()];

            for (JsonElement element : jsonArray) {


                JsonObject jsonObject = element.getAsJsonObject();
                String type = jsonObject.get("type").getAsString();
                JsonObject question = jsonObject.getAsJsonObject("question");

                if (question == null) {
                    throw new TestException("No question at the specified position");
                }

                switch (type) {
                    case "MultipleChoice":
                        addQuestion(parseMultipleChoice(question));
                        break;
                    case "Numeric":
                        addQuestion(parseQuestionNumeric(question));
                        break;
                    case "YesNo":
                        addQuestion(parseQuestionYesNo(question));
                        break;
                    default:
                        System.out.println("Unknown type");
                        break;
                }
            }
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return false;
        }
    }

    /**
     * Parses the fields of question multiple choice
     * @param question to be parsed
     * @return an object of the type Multiple Choice
     */
    protected IQuestionMultipleChoice parseMultipleChoice(JsonObject question) {
        String title = question.get("title").getAsString();
        int score = question.get("score").getAsInt();
        float mark = question.get("mark").getAsFloat();
        String question_description = question.get("question_description").getAsString();
        String correct_answer = question.get("correct_answer").getAsString();

        JsonArray options = question.getAsJsonArray("options");
        String[] possible_option = new String[options.size()];
        for (int i = 0; i < options.size(); i++) {
            possible_option[i] = options.get(i).getAsString();
        }
        return new QuestionMultipleChoice(title, question_description, mark, score,
                possible_option, correct_answer);
    }

    /**
     * Parses the fields of question numeric
     * @param question to be parsed
     * @return an object of the type numeric
     */
    private IQuestionNumeric parseQuestionNumeric(JsonObject question) {
        String title = question.get("title").getAsString();
        int score = question.get("score").getAsInt();
        float mark = question.get("mark").getAsFloat();
        String question_description = question.get("question_description").getAsString();
        String correct_answer = String.valueOf(question.get("correct_answer").getAsDouble());

        return new QuestionNumeric(title, question_description, mark, score, correct_answer);
    }

    /**
     * Parses the fields of question YesNo
     * @param question to be parsed
     * @return an object of the type YesNo
     */
    private IQuestionYesNo parseQuestionYesNo(JsonObject question) {
        String title = question.get("title").getAsString();
        int score = question.get("score").getAsInt();
        float mark = question.get("mark").getAsFloat();
        String question_description = question.get("question_description").getAsString();
        String correct_answer = question.get("correct_answer").getAsString().toUpperCase();//conversion to UpperCase.

        return new QuestionYesNo(title, question_description, mark, score, correct_answer);
    }

    /**
     * Gets the strategy to calculate the Test score
     * @return The strategy to calculate the Test score
     */
    @Override
    public IScoreStrategy getScoreStrategy() {
        return this.scoreStrategy;
    }

    /**
     *Sets the strategy to calculate the Test score
     * @param iScoreStrategy  the strategy to calculate the Test score
     */
    @Override
    public void setScoreStrategy(IScoreStrategy iScoreStrategy) {
        this.scoreStrategy = iScoreStrategy;
    }

    /**
     * Calculates the Score for the Test based on the defined strategy
     * @return The Score for the Test based on the defined strategy
     */
    @Override
    public String calculateScore() {
        return scoreStrategy.CalculateScore(iQuestions);
    }

    /**
     * Saves all questions in the test to a text file
     * @return true if the file was successfully saved all questions in the test, false otherwise
     * @throws TestException if test isn't complete
     */
    @Override
    public boolean saveTestResults() throws TestException {

        if (!isComplete()) {
            throw new TestException("Test isn't complete");
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        OutputTest outputTest = new OutputTest(this);

        try {
            FileWriter fileWriter = new FileWriter("./src/data/" + System.currentTimeMillis() + ".json");
            gson.toJson(outputTest, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Gets an object that implements the contract ITestBetterStatistics which calculates
     * better statistics from user answers
     * @return an object which implements ITestBetterStatistics of this test
     */
    protected ITestBetterStatistics getBetterStatistics() {
        return new TestBetterStatistics(this);
    }

    /**
     * Gets the total mark in the test
     * @return total mark in the test
     */
    protected int getTotalMark() {

        int totalMark = 0;

        for (IQuestion iQuestion : iQuestions) {
            if (iQuestion.evaluateAnswer()) {
                totalMark += iQuestion.getMark();
            }
        }
        return totalMark;
    }

    /**
     * Prints formatted in the console
     * @return a formatted string
     */
    @Override
    public String toString() {

        ITestStatistics statistics = getTestStatistics();
        ITestBetterStatistics betterStatistics = getBetterStatistics();

        return "Mark: " + getTotalMark() + "\n" +
                "Score: " + calculateScore() + "\n" +
                "Start Time: " + startTime.toString() + "\n" +
                "End Time: " + finishTime.toString() + "\n" +
                "Mean time per answer: " + statistics.meanTimePerAnswer() + "\n" +
                "Standard Deviation Time Per Answer: " + statistics.standardDeviationTimePerAnsewer() + "\n" +
                "Percentage Correct MultipleChoice Questions: " + betterStatistics.percentageCorrectMultipleChoiceQuestions() + " %" + "\n" +
                "Percentage Incorrect MultipleChoice Questions: " + betterStatistics.percentageIncorrectMultipleChoiceQuestions() + " %" + "\n" +
                "Percentage Number Of NumericQuestions " + betterStatistics.percentageNumberOfNumericQuestions() + " %" + "\n" +
                "Total Time Test: " + betterStatistics.totalTimeTestInSeconds() + " seconds" + "\n" +
                "Mean Time Per Question : " + betterStatistics.meanTimePerQuestion() + " seconds";
    }

    /**
     * Gets all the questions of the test
     * @return all questions(array) in the test
     */
    protected Question[] getAllQuestions() {
        return iQuestions;
    }

    /**
     * Gets the start time of the test
     * @return the start time
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Gets the finish time of the test
     * @return finish time
     */
    public Date getFinishTime() {
        return finishTime;
    }
}
