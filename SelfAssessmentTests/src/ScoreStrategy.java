import interfaces.controller.IScoreStrategy;
import interfaces.models.IQuestion;

public class ScoreStrategy implements IScoreStrategy {

    @Override
    public String CalculateScore(IQuestion[] iQuestions) {

        int score = 0;

        for (IQuestion iQuestion : iQuestions) {
            Question question = (Question) iQuestion;
            if (iQuestion.evaluateAnswer()) {
                score += question.getScore();
            }
        }
        switch (score) {
            case 4:
                return "Não Satisfaz";
            case 8:
                return "Satisfaz";
            case 12:
                return "Satisfaz Bastante";
            case 16:
                return "Excelente";

            default:
                return "Zero";
        }
    }
}
