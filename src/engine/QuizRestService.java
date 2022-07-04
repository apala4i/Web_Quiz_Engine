package engine;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/quizzes")
public class QuizRestService {

    private final QuizStorage quizStorage;

    @Autowired
    public QuizRestService(QuizStorage quizStorage) {
        this.quizStorage = quizStorage;
    }

    @PostMapping
    public Quiz addQuiz(@RequestBody @Valid Quiz quiz) {
        return quizStorage.addQuiz(quiz);
    }

    @GetMapping("/{id}")
    public Quiz getQuizById(@PathVariable int id) {
        return quizStorage.getQuizByIndex(id);
    }

    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizStorage.getAllQuizzes();
    }

    @PostMapping("/{id}/solve")
    public Response solveQuiz(@PathVariable int id, @RequestBody Answer answer) {
        var writeAnswer = quizStorage.getQuizByIndex(id).getAnswer();

        if (Arrays.equals(writeAnswer, answer.getAnswer())) {
            return Response.correct();
        } else {
            return Response.incorrect();
        }
    }

    @Data
    static class Answer {
        private int [] answer;
    }



}
