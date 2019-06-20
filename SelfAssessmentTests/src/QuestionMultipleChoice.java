import interfaces.models.IQuestionMultipleChoice;

public class QuestionMultipleChoice extends Question implements IQuestionMultipleChoice{
    @Override
    public String[] getOptions() {
        return new String[0];
    }

    @Override
    public void setOptions(String[] strings) {

    }

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
}
