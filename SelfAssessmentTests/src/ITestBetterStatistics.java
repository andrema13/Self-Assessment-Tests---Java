public interface ITestBetterStatistics {

    /**
     * Calculates the percentage of MultipleChoice questions was answered correctly
     * @return percentage of MultipleChoice questions was answered correctly
     */
    double percentageCorrectMultipleChoiceQuestions();

    /**
     * Calculates the percentage of MultipleChoice questions was answered incorrectly
     * @return percentage of MultipleChoice questions was answered incorrectly
     */
    double percentageIncorrectMultipleChoiceQuestions();

    /**
     * Calculates the percentage of the number of Numeric questions
     * @return percentage of number of Numeric questions
     */
    double percentageNumberOfNumericQuestions();

    /**
     * Calculates the total time from the begin to the end of a test
     * @return seconds have been spend  to made the test
     */
    double totalTimeTestInSeconds();

    /**
     * Calculates the mean time per question
     * @return mean time per question
     */
    double meanTimePerQuestion();
}
