import interfaces.exceptions.QuestionException;
import interfaces.models.IQuestionMetadata;
import interfaces.models.IQuestionYesNo;

public class QuestionYesNo extends Question implements IQuestionYesNo {

    QuestionYesNo(String title, String question_description, float mark, String score, String correct_answer) {
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
        this.answer = s;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setTitle(String s) throws QuestionException {
        this.title = s;
    }

    @Override
    public String getQuestion_description() {
        return this.question_description;
    }

    @Override
    public void setQuestion_description(String s) throws QuestionException {
        this.question_description = s;
    }
    @Override
    public IQuestionMetadata getQuestion_metadata() {
        return question_metadata;
    }

    @Override
    public void setQuestion_metadata(IQuestionMetadata iQuestionMetadata) {
        this.question_metadata = iQuestionMetadata;
    }

    @Override
    public boolean isDone() {

        if(answer != null){
            this.question_metadata.setTimestamp_finish(System.currentTimeMillis());
            return true;
        }
        return false;
    }

    @Override
    public void setDone(boolean b) {
        this.isDone = b;
    }

    @Override
    public void answer(String s) {
        this.answer = s;
    }

    @Override
    public boolean evaluateAnswer() {
        return isDone && answer.equals(correct_answer);
    }

    @Override
    public float getMark() {
        return this.mark;
    }

    @Override
    public void setMark(float v) {
        this.mark = v;
    }
}
