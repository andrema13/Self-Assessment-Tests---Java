/*
 * Nome: André Filipe Neto Martins
 * Número: 8180483
 * Turma: T2
 */

import interfaces.controller.ITest;
import interfaces.controller.ITestStatistics;
import interfaces.exceptions.TestException;
import interfaces.models.IQuestion;
import interfaces.models.IQuestionMetadata;

public class TestStatistics implements ITestStatistics {

    private Test test;

    TestStatistics(ITest iTest) {
        this.test = (Test) iTest;
    }

    /**
     * Calculates the mean time per answer of a test
     * @return the mean time per answer of a test in seconds
     */
    @Override
    public double meanTimePerAnswer() {

        long totalTime = 0;

        for (int i = 0; i < test.numberQuestions(); i++) {

            try {
                IQuestionMetadata metadata = test.getQuestion(i).getQuestion_metadata();
                totalTime += metadata.getTimestamp_finish() - metadata.getTimestamp_start();
            } catch (TestException e) {
                e.printStackTrace();
            }
        }
        // Time will be returned in seconds
        return (totalTime / test.numberQuestions()) / 1000;
    }

    /**
     * Calculates the standard deviation time per answer of a test
     * @return the standard deviation time per answer of a test in seconds
     */
    @Override
    public double standardDeviationTimePerAnsewer() {

        double meanTime = meanTimePerAnswer();
        double sum = 0.0;

        for (int i = 0; i < test.numberQuestions(); i++) {
            try {
                IQuestionMetadata metadata = test.getQuestion(i).getQuestion_metadata();
                sum += Math.pow(metadata.getTimestamp_finish() -
                        metadata.getTimestamp_start() - meanTime, 2);
            } catch (TestException e) {
                e.printStackTrace();
                return 0.0;
            }
        }
        return Math.sqrt(sum / test.numberQuestions()) / 1000; // convert to seconds
    }

    /**
     * Calculates the correct answer percentage of a test
     * @return the correct answer percentage of a test
     */
    @Override
    public double correctAnswerPecentage() {
        return ((double)correctAnswer() / test.numberQuestions()) * 100;
    }

    /**
     * Calculates the incorrect answer percentage of a test
     * @return the incorrect answer percentage of a test
     */
    @Override
    public double incorrectAnswerPecentage() {
        return ((double)incorrectAnswer() / test.numberQuestions())* 100;
    }

    /**
     * Calculates the number of correct answers of a test
     * @return the number of correct answers of a test
     */
    @Override
    public int correctAnswer() {
        int correct_answers = 0;
        for (int i = 0; i < test.numberQuestions(); i++) {
            try {
                if (test.getQuestion(i).evaluateAnswer()) {
                    correct_answers++;
                }
            } catch (TestException e) {
                e.printStackTrace();
            }
        }
        return correct_answers;
    }

    /**
     * Calculates the number of incorrect answers of a test
     * @return the number of incorrect answers of a test
     */
    @Override
    public int incorrectAnswer() {
        int correct_answers = 0;
        for (int i = 0; i < test.numberQuestions(); i++) {
            try {
                if (!test.getQuestion(i).evaluateAnswer()) {
                    correct_answers++;
                }
            } catch (TestException e) {
                e.printStackTrace();
            }
        }
        return correct_answers;
    }

    /**
     * Fetches the incorrect questions of a test
     * @return the incorrect questions of a test
     */
    @Override
    public IQuestion[] incorrectAnswers() {
        IQuestion[] tempArray = new IQuestion[test.numberQuestions()];
        int temp = 0;

        for (int i = 0; i < test.numberQuestions(); i++) {
            try {
                if (!test.getQuestion(i).evaluateAnswer()) {
                    tempArray[temp] = test.getQuestion(i);
                    temp++;
                }
            } catch (TestException e) {
                e.printStackTrace();
            }
        }
        return tempArray;
    }

    /**
     * Fetches the correct questions of a test
     * @return the correct questions of a test
     */
    @Override
    public IQuestion[] correctAnswers() {
        IQuestion[] tempArray = new IQuestion[test.numberQuestions()];
        int temp = 0;

        for (int i = 0; i < test.numberQuestions(); i++) {
            try {
                if (test.getQuestion(i).evaluateAnswer()) {
                    tempArray[temp] = test.getQuestion(i);
                    temp++;
                }
            } catch (TestException e) {
                e.printStackTrace();
            }
        }
        return tempArray;
    }
}
