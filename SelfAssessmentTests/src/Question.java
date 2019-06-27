import interfaces.exceptions.QuestionException;
import interfaces.models.IQuestion;
import interfaces.models.IQuestionMetadata;
import java.util.Date;

public class Question implements IQuestion {

    protected String answer;
    protected String title;
    protected String question_description;
    protected String score;
    protected float mark;
    protected boolean isDone;
    protected IQuestionMetadata question_metadata;
    protected String correct_answer;
    protected Date date = new Date();

    Question(String title, String question_description, float mark, String
             score) {
        this.title = title;
        this.question_description = question_description;
        this.mark = mark;
        this.question_metadata = new QuestionMetadata(0L);
        this.score = score;
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
        return this.question_metadata;
    }

    @Override
    public void setQuestion_metadata(IQuestionMetadata iQuestionMetadata) {
        this.question_metadata = iQuestionMetadata;
    }

    @Override
    public boolean isDone() {

        if(answer != null){
            long time = date.getTime();
            this.question_metadata.setTimestamp_finish(time);
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

    @Override
    public boolean equals(Object obj) {

        return obj instanceof Question && ((Question) obj).title.equals(title) &&
                ((Question) obj).question_description.equals(question_description);
    }

}
