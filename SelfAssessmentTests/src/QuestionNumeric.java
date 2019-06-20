import interfaces.models.IQuestionNumeric;

public class QuestionNumeric extends Question implements IQuestionNumeric{

    @Override
    public double getCorrect_anwser() {
        return 0;
    }

    @Override
    public void setCorrect_anwser(double v) {

    }

    @Override
    public double getUser_answer() {
        return 0;
    }

    @Override
    public void setUser_answer(double v) {

    }
}
