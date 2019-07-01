import interfaces.models.IQuestionMetadata;

public class QuestionMetadata implements IQuestionMetadata {
    private long timestamp_start;
    private long timestamp_finish;

    /**
     * Constructor for this class
     */
    QuestionMetadata() {
        this.timestamp_start = 0L;
    }

    /**
     * Gets a timestamp marking the start of the IQuestion answer
     * @return a timestamp marking the start of the IQuestion answer
     */
    @Override
    public long getTimestamp_start() {
        return this.timestamp_start;
    }

    /**
     * Sets a timestamp marking the start of the IQuestion answer
     * @param l a timestamp marking the start of the IQuestion answer
     */
    @Override
    public void setTimestamp_start(long l) {
        this.timestamp_start = l;
    }

    /**
     * Gets a timestamp marking the finish of the IQuestion answer
     * @return a timestamp marking the finish of the IQuestion answer
     */
    @Override
    public long getTimestamp_finish() {
        return this.timestamp_finish;
    }

    /**
     * Sets a timestamp marking the finish of the IQuestion answer
     * @param l  a timestamp marking the finish of the IQuestion answer
     */
    @Override
    public void setTimestamp_finish(long l) {
        this.timestamp_finish = l;
    }
}
