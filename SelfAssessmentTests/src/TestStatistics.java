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

    @Override
    public double correctAnswerPecentage() {
        return ((double)correctAnswer() / test.numberQuestions()) * 100;
    }

    @Override
    public double incorrectAnswerPecentage() {
        return ((double)incorrectAnswer() / test.numberQuestions())* 100;
    }

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
