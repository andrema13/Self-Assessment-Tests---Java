/*
 * Nome: André Filipe Neto Martins
 * Número: 8180483
 * Turma: T2
 */

import interfaces.models.IQuestionMultipleChoice;

public class QuestionMultipleChoice extends Question implements IQuestionMultipleChoice {

    /**
     * Array with the question options
     */
    private String[] question_options;

    /**
     * Constructor for this class
     * @param title  question title
     * @param question_description question description
     * @param mark mark of the question
     * @param score score of the question
     * @param question_options question options
     * @param correct_answer correct answer of the question
     */
    QuestionMultipleChoice(String title, String question_description,
                           float mark, int score, String[] question_options, String correct_answer) {

        super(title, question_description, mark, score);
        this.question_options = question_options;
        this.correct_answer = correct_answer;
    }

    /**
     * Gets the options of question
     * @return the options of question
     */
    @Override
    public String[] getOptions() {
        return this.question_options;
    }

    /**
     * Sets the options of question
     * @param strings the options of question
     */
    @Override
    public void setOptions(String[] strings) {

        for (int i = 0; i < question_options.length; i++) {
            for (String string : strings) {
                if (question_options[i] == null) {
                    question_options[i] = string;
                }
            }
        }
    }

    /**
     * Gets the correct answer to the question
     * @return the correct answer to the question
     */
    @Override
    public String getCorrect_answer() {
        return this.correct_answer;
    }

    /**
     * Sets the correct answer to the question
     * @param s the correct answer to the question
     */
    @Override
    public void setCorrect_answer(String s) {
        this.correct_answer = s;
    }

    /**
     * Gets the user answer to the question
     * @return the user answer to the question
     */
    @Override
    public String getUser_answer() {
        return answer;
    }

    /**
     * Sets the user answer to the question
     * @param s the user answer to the question
     */
    @Override
    public void setUser_answer(String s) {
        answer = s;
    }
}
