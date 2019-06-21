public class Main {

    public static void main(String[] args) {

        Question question1 =  new Question("Questao 1 ", "Qual é o seu nome?",5);
        Test test1 = new Test();
        test1.addQuestion(question1);
    }
}
