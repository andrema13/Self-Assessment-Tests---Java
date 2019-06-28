import interfaces.models.IQuestionYesNo;

public class QuestionYesNo extends Question implements IQuestionYesNo {

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
