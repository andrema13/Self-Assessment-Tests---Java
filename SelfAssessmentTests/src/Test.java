import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

public class Test implements ITest {

    private IQuestion[] iQuestions;
    private IScoreStrategy scoreStrategy;

    Test(){
        scoreStrategy = new ScoreStrategy();
    }

    @Override
    public boolean addQuestion(IQuestion iQuestion) throws TestException {
        for (int i = 0; i < iQuestions.length; i++) {
            if (iQuestions[i] == null) {
                iQuestions[i] = iQuestion;
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
        IQuestion[] tempQuestions = new IQuestion[iQuestions.length - 1];
        int tempIndex = 0;
        for (IQuestion question : iQuestions) {
            if (question.equals(iQuestion)) {
                try {
                    tempQuestions[tempIndex] = question;
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
                iQuestion.setDone(false);
                return false;
            } else {
                iQuestion.setDone(true);
            }
        }
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
            iQuestions = new IQuestion[jsonArray.size()];

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

    private IQuestionMultipleChoice parseMultipleChoice(JsonObject question) {
        String title = question.get("title").getAsString();
        String score = question.get("score").getAsString();
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
        String score = question.get("score").getAsString();
        float mark = question.get("mark").getAsFloat();
        String question_description = question.get("question_description").getAsString();
        String correct_answer = String.valueOf(question.get("correct_answer").getAsDouble());

        return new QuestionNumeric(title, question_description, mark, score, correct_answer);
    }

    private IQuestionYesNo parseQuestionYesNo(JsonObject question) {
        String title = question.get("title").getAsString();
        String score = question.get("score").getAsString();
        float mark = question.get("mark").getAsFloat();
        String question_description = question.get("question_description").getAsString();
        String correct_answer = question.get("correct_answer").getAsString().toUpperCase();//conversao para Maius.

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
    public boolean saveTestResults(String s) throws TestException {
        return false;
    }
}
