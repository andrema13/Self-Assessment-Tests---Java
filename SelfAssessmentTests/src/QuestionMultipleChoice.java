import interfaces.models.IQuestionMultipleChoice;

public class QuestionMultipleChoice extends Question implements IQuestionMultipleChoice{

    private String correct_answer;
    private String user_answer;
    private static final int MAX_OPTIONS = 4;
    private String[] question_options = new String[MAX_OPTIONS];

    QuestionMultipleChoice(String title, String question_description, float mark) {
        super(title, question_description, mark);
    }

    @Override
    public String[] getOptions() {
        return this.question_options;
    }

    @Override
    public void setOptions(String[] strings) {

        for(int i = 0; i < question_options.length; i++){
            for(int k = 0; k < strings.length; k++){
                if(question_options[i] == null){
                    question_options[i] =  strings[k];
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
        return this.user_answer;
    }

    @Override
    public void setUser_answer(String s) {
        this.user_answer = s;
    }
}
