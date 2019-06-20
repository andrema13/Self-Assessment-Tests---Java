import interfaces.exceptions.QuestionException;
import interfaces.models.IQuestion;
import interfaces.models.IQuestionMetadata;

public class Question implements IQuestion {

    private String title;
    private String question_description;

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
        return false;
    }

    @Override
    public void setMark(float v) {

    }

    @Override
    public float getMark() {
        return 0;
    }
}
