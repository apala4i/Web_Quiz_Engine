package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizRestService {

    private final QuizStorage quizStorage;

    @Autowired
    public QuizRestService(QuizStorage quizStorage) {
        this.quizStorage = quizStorage;
    }

    @PostMapping
    public Quiz addQuiz(@RequestBody Quiz quiz) {
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
    public Response solveQuiz(@PathVariable int id, @RequestParam int answer) {
        int writeAnswer = quizStorage.getQuizByIndex(id).getAnswer();

        if (answer == writeAnswer) {
            return Response.correct();
        } else {
            return Response.incorrect();
        }
    }



}
