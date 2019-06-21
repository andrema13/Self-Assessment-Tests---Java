import interfaces.exceptions.QuestionException;
import interfaces.models.IQuestionMetadata;
import interfaces.models.IQuestionYesNo;

public class QuestionYesNo extends Question implements IQuestionYesNo {

    private String title;
    private String question_description;
    private String user_answer;
    private String correct_answer;
    private float mark;

    QuestionYesNo(String title, String question_description, float mark) {
        super(title, question_description, mark);
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
        return this.user_answer;
    }

    @Override
    public void setUser_answer(String s) {
        this.user_answer = s;
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
        return null;
    }

    @Override
    public void setQuestion_metadata(IQuestionMetadata iQuestionMetadata) {

    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public void setDone(boolean b) {

    }

    @Override
    public void answer(String s) {

    }

    @Override
    public boolean evaluateAnswer() {

        if(user_answer.equals(correct_answer)){
            return true;
        }
        return false;
    }

    @Override
    public void setMark(float v) {
        this.mark = v;
    }

    @Override
    public float getMark() {
        return this.mark;
    }
}
