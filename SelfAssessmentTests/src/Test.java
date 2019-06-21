import interfaces.controller.IScoreStrategy;
import interfaces.controller.ITest;
import interfaces.controller.ITestStatistics;
import interfaces.exceptions.TestException;
import interfaces.models.IQuestion;

public class Test implements ITest{

    private IQuestion[] iQuestions;
    private static final int MAX_TAM = 100;

    Test(){
        iQuestions = new IQuestion[MAX_TAM];
    }

    @Override
    public boolean addQuestion(IQuestion iQuestion) throws TestException {

        for(int i = 0 ; i < iQuestions.length ; i++){
            if(iQuestions[i] == null){
                iQuestions[i] = iQuestion;
                return true;
            }
        }
        return false;
    }

    @Override
    public IQuestion getQuestion(int i) throws TestException {

        for(int k = 0; k < iQuestions.length ; k++){

            if(iQuestions[k] != null && (int)iQuestions[k].getMark() == i){
                return iQuestions[k];
            }
        }
        return null;
    }

    @Override
    public boolean removeQuestion(int i) {

        for(int k = 0; k < iQuestions.length ; k++){
            if(iQuestions[k] != null && (int)iQuestions[k].getMark() == i){
                iQuestions[k] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeQuestion(IQuestion iQuestion) {

        for(int i = 0; i < iQuestions.length ; i++){
            if(iQuestions[i] == iQuestion){
                iQuestions[i] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public int numberQuestions() {

        int numberOfQuestions = 0;

        for(int i = 0; i < iQuestions.length ; i++){
            if(iQuestions[i] != null){
                numberOfQuestions++;
                return numberOfQuestions;
            }
        }
        return numberOfQuestions;
    }

    @Override
    public boolean isComplete() {

        for(int i = 0; i < iQuestions.length ; i++){
            if(iQuestions[i] == null){
                return false;
            }
        }
        return true;
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
