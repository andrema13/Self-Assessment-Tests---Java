import interfaces.controller.ITest;
import interfaces.exceptions.TestException;

public interface IAllTests {

    /**
     * Load all tests made so far from a specified path
     * @param filePath path with the file
     * @throws TestException if the file isn't found
     */
    void loadAllTests(String filePath) throws TestException;

    /**
     * Add new test to the file with the tests
     * @param test Test to be added to the file
     **/
    void addNewTest(ITest test);

    /**
     * Writes all tests made by now to the json file
     * @param filePath Path to save the tests
     * @throws TestException If isn't possible to write in the specific file
     */
    void saveAllTests(String filePath) throws TestException;


}
