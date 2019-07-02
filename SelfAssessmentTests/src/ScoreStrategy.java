/*
 * Nome: André Filipe Neto Martins
 * Número: 8180483
 * Turma: T2
 */

import interfaces.controller.IScoreStrategy;
import interfaces.models.IQuestion;

public class ScoreStrategy implements IScoreStrategy {

    /**
     * Calculates the Score for the Test .
     * It was declared this possibles scores to the test:
     * 4 is "Not Enough"
     * 8 is "Satisfy"
     * 12 is "Really Satisfy"
     * 16 is "Excellent"
     * @param iQuestions  the questions used to calculate score
     * @return the score for the test
     */
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
                return "Not Enough";
            case 8:
                return "Satisfy";
            case 12:
                return "Really Satisfy";
            case 16:
                return "Excellent";

            default:
                return "Zero";
        }
    }
}
