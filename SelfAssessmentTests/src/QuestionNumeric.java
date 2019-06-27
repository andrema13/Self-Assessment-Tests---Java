import interfaces.models.IQuestionNumeric;

public class QuestionNumeric extends Question implements IQuestionNumeric {

    QuestionNumeric(String title, String question_description,
                    float mark, String score, String correct_answer) {
        super(title, question_description, mark, score);
        this.correct_answer = correct_answer;
        // This is done because on NumericQuestionPanel is reading and trying to convert a null value
        // since it happens before the user can answer. Since there's no option to fix the issue, it's necessary
        // to initialize the answer with zero and assume it like not answered
        this.answer = "0";
    }

    @Override
    public double getCorrect_anwser() {
        return Double.parseDouble(this.correct_answer);
    }

    @Override
    public void setCorrect_anwser(double v) {
        this.correct_answer = String.valueOf(v);
    }

    @Override
    public double getUser_answer() {
        return Double.parseDouble(answer);
    }

    @Override
    public void setUser_answer(double v) {
        answer = String.valueOf(v);
    }

    @Override
    public boolean isDone() {

        // Zero means not answered due to panel issue
        if (Double.parseDouble(answer) > 0.0) {
            this.question_metadata.setTimestamp_finish(System.currentTimeMillis());
            return true;
        }
        return false;
    }
}
