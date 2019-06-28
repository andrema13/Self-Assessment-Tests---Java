import interfaces.models.IQuestion;
import java.util.Date;

public class OutputTest {

    private IQuestion[] iQuestions;
    private Date startTime;
    private Date finishTime;
    private double meanTime;

    OutputTest(Test test){
        iQuestions = test.getAllQuestions();
        startTime = test.getStartTime();
        finishTime = test.getFinishTime();
        meanTime = test.getTestStatistics().meanTimePerAnswer();
    }

}
