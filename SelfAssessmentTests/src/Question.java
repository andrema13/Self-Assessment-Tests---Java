/*
 * Nome: André Filipe Neto Martins
 * Número: 8180483
 * Turma: T2
 */

import interfaces.exceptions.QuestionException;
import interfaces.models.IQuestion;
import interfaces.models.IQuestionMetadata;

public class Question implements IQuestion {

    protected String correct_answer;
    protected String answer;
    protected String title;
    protected QuestionMetadata question_metadata;
    protected String question_description;
    protected boolean isDone;
    protected float mark;
    private int score;

    /**
     * Constructor for the class question
     * @param title title of the question
     * @param question_description question description
     * @param mark mark of the question
     * @param score score of the question
     */
    Question(String title, String question_description, float mark, int
            score) {
        this.title = title;
        this.question_description = question_description;
        this.mark = mark;
        this.question_metadata = new QuestionMetadata();
        this.score = score;
    }

    /**
     * Gets the title of a question
     * @return the title of a question
     */
    @Override
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets the title of a question
     * @param s  the title of a question
     * @throws QuestionException if title is null
     */
    @Override
    public void setTitle(String s) throws QuestionException {

        if(s == null){
            throw new QuestionException("Title Can't be null");
        }
        this.title = s;
    }

    /**
     * Gets the description of a question
     * @return the description of a question
     */
    @Override
    public String getQuestion_description() {
        return this.question_description;
    }

    /**
     * Sets the description of a question
     * @param s the description of a question
     * @throws QuestionException if the description is null
     */
    @Override
    public void setQuestion_description(String s) throws QuestionException {

        if(s == null){
            throw new QuestionException("Description can't be null");
        }
        this.question_description = s;
    }

    /**
     * Gets an object which implements metadata of a question
     * @return an object which implements metadata of a question
     */
    @Override
    public IQuestionMetadata getQuestion_metadata() {
        return this.question_metadata;
    }

    /**
     * Sets an object which implements metadata of a question
     * @param iQuestionMetadata  an object which implements metadata of a question
     */
    @Override
    public void setQuestion_metadata(IQuestionMetadata iQuestionMetadata) {
        this.question_metadata = (QuestionMetadata) iQuestionMetadata;
    }

    /**
     * Verified if the question is answered
     * @return true if the question is answered false otherwise
     */
    @Override
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets if the question is answered
     * @param b boolean if the question is answered
     */
    @Override
    public void setDone(boolean b) {
        this.isDone = b;
        //If its done , store the finish time
        this.question_metadata.setTimestamp_finish(System.currentTimeMillis());
    }

    /**
     * Sets the answer of a question and sets the question as answered
     * @param s the answer of a question
     */
    @Override
    public void answer(String s) {
        this.answer = s;
        setDone(true);
    }

    /**
     * Evaluates the answer of a question
     * @return true if answer is correct false otherwise
     */
    @Override
    public boolean evaluateAnswer() {
        return isDone && answer.equals(correct_answer);
    }

    /**
     * Gets the mark for the question
     * @return The mark for the question
     */
    @Override
    public float getMark() {
        return this.mark;
    }

    /**
     * Sets the mark for the question
     * @param v The mark for the question
     */
    @Override
    public void setMark(float v) {
        this.mark = v;
    }

    /**
     * Gets the score for the question
     * @return The score for the question
     */
    public int getScore() {
        return score;
    }

    /**
     * Compares two objects for equality
     * @param obj to be compared
     * @return true if they are equals false otherwise
     */
    @Override
    public boolean equals(Object obj) {

        return obj instanceof Question && ((Question) obj).title.equals(title) &&
                ((Question) obj).question_description.equals(question_description);
    }
}
