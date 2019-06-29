import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import interfaces.controller.ITest;
import interfaces.exceptions.TestException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class AllTests implements IAllTests {

    private Test[] tests;

    @Override
    public void loadAllTests(String filePath) throws TestException {

        try {
            tests = new Gson().fromJson(new FileReader(filePath), Test[].class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new TestException("File not Found");
        }
    }

    @Override
    public void saveAllTests(String filePath) throws TestException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            gson.toJson(tests, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new TestException("Can't write to file");
        }
    }

    @Override
    public void addNewTest(ITest test) {

        if (tests != null) {

            Test[] tempArray = new Test[tests.length + 1];
            System.arraycopy(tests, 0, tempArray, 0, tests.length);
            tempArray[tempArray.length - 1] = (Test) test;
            tests = tempArray;
            Arrays.sort(tests, new SortByCorrectAnswers());

        } else {
            tests = new Test[1];
            tests[0] = (Test) test;
        }

    }

    class SortByCorrectAnswers implements Comparator<Test> {

        @Override
        public int compare(Test o1, Test o2) {
            return o1.getTestStatistics().correctAnswer() - o2.getTestStatistics().correctAnswer();
        }

    }
}
