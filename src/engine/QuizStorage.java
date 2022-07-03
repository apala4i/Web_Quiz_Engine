package engine;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.*;

@Component
public class QuizStorage {

    private final List<Quiz> quizStorage = Collections.synchronizedList(new ArrayList<>());

    public Quiz getQuizByIndex(int index) {
        if (index < 0 || index >= quizStorage.size()) {
            throw new QuizNotFoundException(String.format("Quiz with id = %d not found", index));
        } else {
            return quizStorage.get(index);
        }
    }

    public Quiz addQuiz(Quiz quiz) {
        int nextId = quizStorage.size();
        quizStorage.add(quiz);
        quiz.setId(nextId);
        return quiz;
    }

    public List<Quiz> getAllQuizzes() {
        return quizStorage;
    }


}
