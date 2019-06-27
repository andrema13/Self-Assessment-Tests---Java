import interfaces.models.IQuestionMetadata;

public class QuestionMetadata implements IQuestionMetadata {
    private long timestamp_start;
    private long timestamp_finish;

    QuestionMetadata(long timestamp_start) {
        this.timestamp_start = timestamp_start;
    }

    @Override
    public long getTimestamp_start() {
        return this.timestamp_start;
    }

    @Override
    public void setTimestamp_start(long l) {
        this.timestamp_start = l;
    }

    @Override
    public long getTimestamp_finish() {
        return this.timestamp_finish;
    }

    @Override
    public void setTimestamp_finish(long l) {
        this.timestamp_finish = l;
    }
}
