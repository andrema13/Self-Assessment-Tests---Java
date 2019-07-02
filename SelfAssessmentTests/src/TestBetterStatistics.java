/*
 * Nome: André Filipe Neto Martins
 * Número: 8180483
 * Turma: T2
 */

import interfaces.controller.ITest;
import interfaces.exceptions.TestException;

public class TestBetterStatistics implements ITestBetterStatistics {

    private Test test;

    TestBetterStatistics(ITest iTest) {
        this.test = (Test) iTest;
    }

    /**
     * Gets the number of correct question of the type MultipleChoice
     * @return number of correct question of the type MultipleChoice
     */
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

    /**
     * Gets the total number of questions of MultipleChoice in the test
     * @ total number of questions of MultipleChoice in the test
     */
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

    /**
     * Gets the percentage of correct MultipleChoice questions in the test
     * @return percentage of correct MultipleChoice questions in the test
     */
    @Override
    public double percentageCorrectMultipleChoiceQuestions() {
        return (numberOfCorrectQuestionsMultipleChoice()/totalNumberOfQuestionsMultipleChoice())* 100;
    }

    /**
     * Gets the percentage of incorrect MultipleChoice questions in the test
     * @return percentage of incorrect MultipleChoice questions in the test
     */
    @Override
    public double percentageIncorrectMultipleChoiceQuestions() {
        return ((totalNumberOfQuestionsMultipleChoice() - numberOfCorrectQuestionsMultipleChoice())/
                totalNumberOfQuestionsMultipleChoice())* 100;
    }

    /**
     * Gets the percentage of the number of Numeric questions in the test
     * @return percentage of the number of Numeric questions in the test
     */
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

    /**
     * Gets the total time has spend to make the test in seconds
     * @return total time has spend to make the test in seconds
     */
    @Override
    public double totalTimeTestInSeconds() {
        return (test.getFinishTime().getTime() - test.getStartTime().getTime())/ 1000;
    }

    /**
     * Gets the mean time the user spends to click "next" per question
     * @return mean time the user spends to click "next" per question
     */
    @Override
    public double meanTimePerQuestion() {
        return totalTimeTestInSeconds() / test.numberQuestions();
    }
}
