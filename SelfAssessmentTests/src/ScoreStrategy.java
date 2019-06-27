import interfaces.controller.IScoreStrategy;
import interfaces.models.IQuestion;

public class ScoreStrategy implements IScoreStrategy {

    //private String score;

    @Override
    public String CalculateScore(IQuestion[] iQuestions) {

        /*for(int i = 0; i < iQuestions.length; i++){

            if(iQuestions[i].isDone() && iQuestions[i].evaluateAnswer()){
                iQuestions[i].
            }
            switch (score){
                case "4":
                    System.out.println("Não Satisfaz");
                    break;
                case "8":
                    System.out.println("Satisfaz");
                    break;
                case "12":
                    System.out.println("Satisfaz Bastante");
                    break;
                case "16":
                    System.out.println("Excelente");
                    break;

                    default:
                        System.out.println("Zero");
                        break;
            }
        }*/
        return "abc";
    }
}
