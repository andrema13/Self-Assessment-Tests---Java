/*
 * Nome: André Filipe Neto Martins
 * Número: 8180483
 * Turma: T2
 */

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import interfaces.exceptions.TestException;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class MultipleChoiceTest extends Test {

    /**
     * Loads all questions in the test with just multiple choice questions from a text file
     * @param s the path to the text file
     * @return true if the file was successfully loaded all multiple choice questions in the test, false otherwise
     * @throws TestException if there's not match the question type
     */
    @Override
    public boolean loadFromJSONFile(String s) throws TestException {

        JsonParser parser = new JsonParser();

        try {
            JsonElement jsonElement = parser.parse(new FileReader(s));
            JsonArray jsonArray = jsonElement.getAsJsonArray();

            // Initialize array with the number of questions
            iQuestions = new Question[jsonArray.size()];

            for (JsonElement element : jsonArray) {

                JsonObject jsonObject = element.getAsJsonObject();
                String type = jsonObject.get("type").getAsString();
                JsonObject question = jsonObject.getAsJsonObject("question");

                if (type.equals("MultipleChoice")) {
                    addQuestion(parseMultipleChoice(question));
                } else {
                    throw new TestException("Wrong Question Type");
                }
            }
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            return false;
        }
    }

}
