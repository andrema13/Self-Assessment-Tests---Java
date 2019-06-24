import interfaces.exceptions.TestException;

public class Main {

    public static void main(String[] args) {


        /*Question question1 =  new QuestionMultipleChoice
                ("Questao 1 ", "Qual é o teu nome?",5);*/
        Test test1 = new Test();
        try {
            test1.loadFromJSONFile("./src/data/teste_A.json");

        }
        catch (TestException e) {
            System.out.println(e.getMessage());
        }



    }
}
