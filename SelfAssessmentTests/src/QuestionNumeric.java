import interfaces.models.IQuestionNumeric;

public class QuestionNumeric extends Question implements IQuestionNumeric{

    private double correct_answer;
    private double user_answer;

    QuestionNumeric(String title, String question_description, float mark) {
        super(title, question_description, mark);
    }

    @Override
    public double getCorrect_anwser() {
        return this.correct_answer;
    }

    @Override
    public void setCorrect_anwser(double v) {
        this.correct_answer = v;
    }

    @Override
    public double getUser_answer() {
        return this.user_answer;
    }

    @Override
    public void setUser_answer(double v) {
        this.user_answer = v;
    }
}
