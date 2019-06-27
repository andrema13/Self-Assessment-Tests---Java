import interfaces.models.IQuestionMultipleChoice;

public class QuestionMultipleChoice extends Question implements IQuestionMultipleChoice {

    private String[] question_options;

    QuestionMultipleChoice(String title, String question_description,
                           float mark, String score, String[] question_options, String correct_answer) {

        super(title, question_description, mark, score);
        this.question_options = question_options;
        this.correct_answer = correct_answer;
    }

    @Override
    public String[] getOptions() {
        return this.question_options;
    }

    @Override
    public void setOptions(String[] strings) {

        for (int i = 0; i < question_options.length; i++) {
            for (String string : strings) {
                if (question_options[i] == null) {
                    question_options[i] = string;
                }
            }
        }
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
        answer = s;
    }
}
