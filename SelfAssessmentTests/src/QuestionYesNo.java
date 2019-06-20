import interfaces.exceptions.QuestionException;
import interfaces.models.IQuestionMetadata;
import interfaces.models.IQuestionYesNo;

public class QuestionYesNo extends Question implements IQuestionYesNo {
    @Override
    public String getCorrect_answer() {
        return null;
    }

    @Override
    public void setCorrect_answer(String s) {

    }

    @Override
    public String getUser_answer() {
        return null;
    }

    @Override
    public void setUser_answer(String s) {

    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void setTitle(String s) throws QuestionException {

    }

    @Override
    public String getQuestion_description() {
        return null;
    }

    @Override
    public void setQuestion_description(String s) throws QuestionException {

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
