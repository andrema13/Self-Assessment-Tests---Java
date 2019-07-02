/*
 * Nome: André Filipe Neto Martins
 * Número: 8180483
 * Turma: T2
 */

import interfaces.models.IQuestionNumeric;

public class QuestionNumeric extends Question implements IQuestionNumeric {

    /**
     * Constructor for this class
     * @param title title of question numeric
     * @param question_description question description
     * @param mark mark of the question numeric
     * @param score score of the question numeric
     * @param correct_answer correct answer of the question numeric
     */
    QuestionNumeric(String title, String question_description,
                    float mark, int score, String correct_answer) {
        super(title, question_description, mark, score);
        this.correct_answer = correct_answer;
        // This is done because on NumericQuestionPanel is reading and trying to convert a null value
        // since it happens before the user can answer. Since there's no option to fix the issue, it's necessary
        // to initialize the answer with zero and assume it like not answered
        this.answer = "0";
    }

    /**
     * Gets the correct answer to the question
     * @return the correct answer to the question
     */
    @Override
    public double getCorrect_anwser() {
        return Double.parseDouble(this.correct_answer);
    }

    /**
     * Sets the correct answer to the question
     * @param v the correct answer to the question
     */
    @Override
    public void setCorrect_anwser(double v) {
        this.correct_answer = String.valueOf(v);
    }

    /**
     * Gets the user answer to the question
     * @return the user answer to the question
     */
    @Override
    public double getUser_answer() {
        return Double.parseDouble(answer);
    }

    /**
     * Sets the user answer to the question
     * @param v the user answer to the question
     */
    @Override
    public void setUser_answer(double v) {
        answer(String.valueOf(v));
    }

}
