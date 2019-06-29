import interfaces.controller.ITest;
import interfaces.exceptions.TestException;

public class TestBetterStatistics implements ITestBetterStatistics {

    private Test test;

    TestBetterStatistics(ITest iTest) {
        this.test = (Test) iTest;
    }

    private int numberOfCorrectQuestionsMultipleChoice(){
        int correct_answers = 0;

        for(int i = 0; i < test.numberQuestions(); i++){
            try {
                if(test.getQuestion(i) instanceof QuestionMultipleChoice && test.getQuestion(i).evaluateAnswer()){
                    correct_answers++;
                }
            } catch (TestException e) {
                e.printStackTrace();
            }
        }
        return correct_answers;
    }

    private int totalNumberOfQuestionsMultipleChoice(){

        int total_questions = 0;

        for(int i = 0; i < test.numberQuestions(); i++){
            try {
                if(test.getQuestion(i) instanceof QuestionMultipleChoice){
                    total_questions++;
                }
            } catch (TestException e) {
                e.printStackTrace();
            }
        }
        return total_questions;
    }
    @Override
    public double percentageCorrectMultipleChoiceQuestions() {
        return (numberOfCorrectQuestionsMultipleChoice()/totalNumberOfQuestionsMultipleChoice())* 100;
    }

    @Override
    public double percentageIncorrectMultipleChoiceQuestions() {
        return ((totalNumberOfQuestionsMultipleChoice() - numberOfCorrectQuestionsMultipleChoice())/
                totalNumberOfQuestionsMultipleChoice())* 100;
    }

    @Override
    public double percentageNumberOfNumericQuestions() {

        int numberOfNumericQuestions = 0;

        for (int i = 0; i < test.numberQuestions(); i++){
            try {
                if(test.getQuestion(i) instanceof QuestionNumeric){
                    numberOfNumericQuestions++;
                }
            } catch (TestException e) {
                e.printStackTrace();
            }
        }
        return ((double)numberOfNumericQuestions / test.numberQuestions()) * 100;
    }

    @Override
    public double totalTimeTestInSeconds() {
        return (test.getFinishTime().getTime() - test.getStartTime().getTime())/ 1000;
    }

    @Override
    public double meanTimePerQuestion() {
        return totalTimeTestInSeconds() / test.numberQuestions();
    }
}
