/*
 * Nome: André Filipe Neto Martins
 * Número: 8180483
 * Turma: T2
 */

import interfaces.controller.ITest;
import interfaces.exceptions.TestException;
import views.TestWindow;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws TestException {

        System.out.println("Choose an option:");
        System.out.println("1. Test A \n \tDescription: It´s a test with a multiple type of questions.");
        System.out.println("2. Test B \n \tDescription: It's a test with just a Multiple Choice Questions.");
        System.out.println("3. Test C \n \tDescription: Tests if a question is removed successfully.");

        Scanner scanner = new Scanner(System.in);

        switch (scanner.next()){
            case "1":
                TestA();
                break;
            case "2":
                TestB();
                break;
            case "3":
                TestC();
            default:
                System.out.println("Wrong Choice!");
                break;
        }
    }

    /**
     * Run a test with a different type of questions.
     * The test is loaded from a json file and it's loaded a json file with all the tests made so far.
     * If test is with all questions answered, the test is added to the file with all tests and it's printed
     * on console "Test Finished!" and all the statistics of this test.
     * If test isn't complete it's just printed "Test Finished!" and it's not added to the file with all tests.
     * @throws TestException if path doesn't exist
     */
    private static void TestA() throws TestException {
        System.out.println("Test Started!");
        ITest demoTest = new Test();
        TestWindow testWindow = new TestWindow();
        AllTests tests = new AllTests();

        try {
            demoTest.loadFromJSONFile("./src/data/test_A.json");
            tests.loadAllTests("./src/data/all_tests.json");
            testWindow.startTest(demoTest);
            if(demoTest.isComplete()){
                tests.addNewTest(demoTest);
                tests.saveAllTests("./src/data/all_tests.json");
                System.out.println(demoTest.toString());
            }
            System.out.println("Test Finished!");
        } catch (TestException e) {
            System.out.println(e.getMessage());
            System.exit(1);
            throw e;
        }
    }

    /**
     * Run a test with just a Multiple Choice questions.
     * The test is loaded from a json file and it's loaded a json file with all the tests made so far.
     * If test is with all questions answered, the test is added to the file with all tests and it's printed
     * on console "Test Finished!" and all the statistics of this test.
     * If test isn't complete it's just printed "Test Finished!" and it's not added to the file with all tests.
     * @throws TestException if path doesn't exist
     */
    private static void TestB() throws TestException {
        System.out.println("Test Started!");
        ITest demoTest = new MultipleChoiceTest();
        TestWindow testWindow = new TestWindow();
        AllTests tests = new AllTests();

        try {
            demoTest.loadFromJSONFile("./src/data/test_B.json");
            tests.loadAllTests("./src/data/all_tests.json");
            testWindow.startTest(demoTest);
            if(demoTest.isComplete()){
                tests.addNewTest(demoTest);
                tests.saveAllTests("./src/data/all_tests.json");
                System.out.println(demoTest.toString());
            }
            System.out.println("Test Finished!");
        } catch (TestException e) {
            System.out.println(e.getMessage());
            System.exit(1);
            throw e;
        }
    }

    /**
     * Run a test with a different type of questions but is tested the removeQuestion method.
     * After removed a question the test is started and follows the normal flow like the TestA() or TestB()
     * @throws TestException if path doesn't exist
     */
    private static void TestC() throws TestException {
        System.out.println("Test Started!");
        ITest demoTest = new MultipleChoiceTest();
        TestWindow testWindow = new TestWindow();
        AllTests tests = new AllTests();

        try {
            demoTest.loadFromJSONFile("./src/data/test_A.json");
            tests.loadAllTests("./src/data/all_tests.json");
            demoTest.removeQuestion(0);
            testWindow.startTest(demoTest);
            if(demoTest.isComplete()){
                tests.addNewTest(demoTest);
                tests.saveAllTests("./src/data/all_tests.json");
                System.out.println(demoTest.toString());
            }
            System.out.println("Test Finished!");
        } catch (TestException e) {
            System.out.println(e.getMessage());
            System.exit(1);
            throw e;
        }
    }

}
