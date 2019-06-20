import interfaces.controller.IScoreStrategy;
import interfaces.controller.ITest;
import interfaces.controller.ITestStatistics;
import interfaces.exceptions.TestException;
import interfaces.models.IQuestion;

public class Test implements ITest{

    @Override
    public boolean addQuestion(IQuestion iQuestion) throws TestException {
        return false;
    }

    @Override
    public IQuestion getQuestion(int i) throws TestException {
        return null;
    }

    @Override
    public boolean removeQuestion(int i) {
        return false;
    }

    @Override
    public boolean removeQuestion(IQuestion iQuestion) {
        return false;
    }

    @Override
    public int numberQuestions() {
        return 0;
    }

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public ITestStatistics getTestStatistics() {
        return null;
    }

    @Override
    public boolean loadFromJSONFile(String s) throws TestException {
        return false;
    }

    @Override
    public void setScoreStrategy(IScoreStrategy iScoreStrategy) {

    }

    @Override
    public IScoreStrategy getScoreStrategy() {
        return null;
    }

    @Override
    public String calculateScore() {
        return null;
    }

    @Override
    public boolean saveTestResults(String s) throws TestException {
        return false;
    }
}
