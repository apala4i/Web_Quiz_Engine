package engine;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
public class QuizRestService {

    private Quiz quiz = new Quiz("name question",
            "What is my name?", new String[]{"David", "Peter", "Semen", "Alex"});

    private int correctAnswerIndex = 2;

    @GetMapping("/quiz")
    public Quiz getQuiz() {
        return quiz;
    }

    @PostMapping("/quiz")
    public Response solveQuiz(@RequestParam int answer) {
        if (answer == correctAnswerIndex) {
            return Response.correct();
        } else {
            return Response.incorrect();
        }
    }

}
