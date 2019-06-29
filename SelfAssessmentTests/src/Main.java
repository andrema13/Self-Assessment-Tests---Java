import interfaces.controller.ITest;
import interfaces.exceptions.TestException;
import views.TestWindow;

public class Main {

    public static void main(String[] args) throws TestException {

        System.out.println("Inicio do Teste");
        ITest demoTest = new Test();
        TestWindow testWindow = new TestWindow();
        AllTests tests = new AllTests();

        try {
            demoTest.loadFromJSONFile("./src/data/teste_A.json");
            tests.loadAllTests("./src/data/all_tests.json");
            //demoTest.removeQuestion(0);
            testWindow.startTest(demoTest);
            tests.addNewTest(demoTest);
            tests.saveAllTests("./src/data/all_tests.json");
        } catch (TestException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        // Add test to list of tests
        // Save new file with only this test
        System.out.println("Teste Efetuado!");
        System.out.println(demoTest.toString());

    }
}
