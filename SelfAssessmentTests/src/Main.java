import interfaces.controller.ITest;
import interfaces.exceptions.TestException;
import views.TestWindow;

public class Main {

    public static void main(String[] args) throws TestException {

        System.out.println("Inicio do Teste");
        ITest demoTest = new Test();
        TestWindow testWindow = new TestWindow();

        try {
            demoTest.loadFromJSONFile("./src/data/teste_A.json");
            testWindow.startTest(demoTest);
            //demoTest.saveTestResults("./src/data/results.txt");

        } catch (TestException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        System.out.println("Teste Efetuado!");
        //System.out.println(demoTest.toString());
    }
}
