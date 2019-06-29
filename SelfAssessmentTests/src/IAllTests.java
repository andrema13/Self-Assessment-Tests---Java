import interfaces.controller.ITest;
import interfaces.exceptions.TestException;

public interface IAllTests {

    void loadAllTests(String filePath) throws TestException;

    void saveAllTests(String filePath) throws TestException;

    void addNewTest(ITest test);
}
