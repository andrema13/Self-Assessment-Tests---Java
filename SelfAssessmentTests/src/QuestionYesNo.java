/*
 * Nome: André Filipe Neto Martins
 * Número: 8180483
 * Turma: T2
 */

import interfaces.models.IQuestionYesNo;

public class QuestionYesNo extends Question implements IQuestionYesNo {

    /**
     * Constructor for this class
     * @param title title of the YesNo question
     * @param question_description question description of YesNo question
     * @param mark mark of the question YesNo
     * @param score score of the question YesNo
     * @param correct_answer correct answer of the question YesNo
     */
    QuestionYesNo(String title, String question_description, float mark, int score, String correct_answer) {
        super(title, question_description, mark, score);
        this.correct_answer = correct_answer;
    }

    @Override
    public String getCorrect_answer() {
        return this.correct_answer;
    }

    @Override
    public void setCorrect_answer(String s) {
        this.correct_answer = s;
    }

    @Override
    public String getUser_answer() {
        return answer;
    }

    @Override
    public void setUser_answer(String s) {
        answer(s);
    }

}
