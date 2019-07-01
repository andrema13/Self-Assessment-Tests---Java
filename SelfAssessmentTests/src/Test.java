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

    protected Question[] iQuestions;
    private transient IScoreStrategy scoreStrategy;
    private final Date startTime;
    private Date finishTime;

    Test() {
        scoreStrategy = new ScoreStrategy();
        startTime = new Date();
    }

    @Override
    public boolean addQuestion(IQuestion iQuestion) throws TestException {
        for (int i = 0; i < iQuestions.length; i++) {
            if (iQuestions[i] == null) {
                iQuestions[i] = (Question) iQuestion;
                return true;
            }
        }
        throw new TestException("Array should have space for questions");
    }

    @Override
    public IQuestion getQuestion(int i) throws TestException {
        for (int k = 0; k < iQuestions.length; k++) {
            if (iQuestions[k] != null && k == i) {
                return iQuestions[k];
            }
        }
        throw new TestException("Wrong index");
    }

    @Override
    public boolean removeQuestion(int i) {
        return removeQuestion(iQuestions[i]);
    }

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

    @Override
    public int numberQuestions() {

        return iQuestions.length;
    }

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

    @Override
    public ITestStatistics getTestStatistics() {
        return new TestStatistics(this);
    }

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

    private IQuestionNumeric parseQuestionNumeric(JsonObject question) {
        String title = question.get("title").getAsString();
        int score = question.get("score").getAsInt();
        float mark = question.get("mark").getAsFloat();
        String question_description = question.get("question_description").getAsString();
        String correct_answer = String.valueOf(question.get("correct_answer").getAsDouble());

        return new QuestionNumeric(title, question_description, mark, score, correct_answer);
    }

    private IQuestionYesNo parseQuestionYesNo(JsonObject question) {
        String title = question.get("title").getAsString();
        int score = question.get("score").getAsInt();
        float mark = question.get("mark").getAsFloat();
        String question_description = question.get("question_description").getAsString();
        String correct_answer = question.get("correct_answer").getAsString().toUpperCase();//conversion to UpperCase.

        return new QuestionYesNo(title, question_description, mark, score, correct_answer);
    }

    @Override
    public IScoreStrategy getScoreStrategy() {
        return this.scoreStrategy;
    }

    @Override
    public void setScoreStrategy(IScoreStrategy iScoreStrategy) {
        this.scoreStrategy = iScoreStrategy;
    }

    @Override
    public String calculateScore() {
        return scoreStrategy.CalculateScore(iQuestions);
    }

    @Override
    public boolean saveTestResults() throws TestException {

        if(!isComplete()){
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

    protected ITestBetterStatistics getBetterStatistics() {
        return new TestBetterStatistics(this);
    }

    protected int getTotalMark(){

        int totalMark = 0;

        for (IQuestion iQuestion : iQuestions) {
            if (iQuestion.evaluateAnswer()) {
                totalMark += iQuestion.getMark();
            }
        }
        return totalMark;
    }

    @Override
    public String toString() {

        ITestStatistics statistics = getTestStatistics();
        ITestBetterStatistics betterStatistics = getBetterStatistics();

        return  "Mark: " + getTotalMark() + "\n" +
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

    public Question[] getAllQuestions() {
        return iQuestions;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }
}
