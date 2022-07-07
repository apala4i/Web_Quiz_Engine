package engine.presentationlayer;

import engine.businesslayer.Quiz;
import engine.businesslayer.QuizService;
import engine.businesslayer.Response;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/quizzes")
public class QuizRestController {

    private final QuizService quizService;

    @Autowired
    public QuizRestController(QuizService quizStorage) {
        this.quizService = quizStorage;
    }

    @PostMapping
    public Quiz addQuiz(@RequestBody @Valid Quiz quiz) {
        return quizService.addQuiz(quiz);
    }

    @GetMapping("/{id}")
    public Quiz getQuizById(@PathVariable long id) {
        return quizService.getQuizByIndex(id);
    }

    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @PostMapping("/{id}/solve")
    public Response solveQuiz(@PathVariable long id, @RequestBody Answer answer) {
        var rightAnswer = List.copyOf(quizService.getQuizByIndex(id).getAnswer());
        var checkAnswer = answer.getAnswer() != null ? answer.getAnswer() : List.of();

        if (rightAnswer.equals(checkAnswer)) {
            return Response.correct();
        } else {
            return Response.incorrect();
        }
    }

    @Data
    static class Answer {
        private List<Integer> answer = new ArrayList<>();
    }



}
