/*
* Nome: André Filipe Neto Martins
* Número: 8180483
* Turma: T2
 */

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
    /**
     * Private array of type Test[] to store tests
     */
    private Test[] tests;

    /**
     * Load all tests made so far from a specified path
     * @param filePath path with the file
     * @throws TestException if the file isn't found
     */
    @Override
    public void loadAllTests(String filePath) throws TestException {

        try {
            tests = new Gson().fromJson(new FileReader(filePath), Test[].class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new TestException("File not Found");
        }
    }

    /**
     * Add new test to the file with the tests
     * Initially is created a temporary array with a additionally position of tests.length.
     * After that is copied the tests from tests to tempArray with a length of tests array.
     * So is missing one position to fill , and its for the new test.
     * The tempArray is passed to the tests array.
     * Is sorted the tests by the number of correct answers.
     * If is no tests yet , its created a array with one position and the new tests is filled in position 0.
     * @param test Test to be added to the file
     */
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

    /**
     * Writes all tests made by now to the json file
     * @param filePath Path to save the tests
     * @throws TestException If isn't possible to write in the specific file
     */
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

    /**
     * Inner class to sort test by most correct answers in the file.
     */
    class SortByCorrectAnswers implements Comparator<Test> {

        /**
         * Override method to compare two tests and set the test with most correct answers
         * @param o1 test 1 to be compared
         * @param o2 test 2 to be compared
         * @return >0 means the o1 has most correct answers, <0 means o2 has the most correct answers
         */
        @Override
        public int compare(Test o1, Test o2) {
            return o1.getTestStatistics().correctAnswer() - o2.getTestStatistics().correctAnswer();
        }
    }
}
