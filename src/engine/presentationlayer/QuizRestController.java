package engine.presentationlayer;

import engine.businesslayer.*;
import engine.businesslayer.Security.User;
import engine.businesslayer.Security.UserDetailsImpl;
import engine.businesslayer.Security.UserDetailsServiceImpl;
import engine.businesslayer.Security.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class QuizRestController {

    private final QuizService quizService;

    private final UserService userService;

    private final QuizCompletionService quizCompletionService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public QuizRestController(QuizService quizService,
                              UserService userService,
                              PasswordEncoder passwordEncoder,
                              QuizCompletionService quizCompletionService) {
        this.quizService = quizService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.quizCompletionService = quizCompletionService;
    }

    @PostMapping("/quizzes")
    public Quiz addQuiz(@RequestBody @Valid Quiz quiz, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Quiz addedQuiz = quizService.addQuiz(quiz);

        var user = userService.findById(userDetails.getUsername()).get();

        user.getQuizIds().add(addedQuiz.getId());
        userService.save(user);

        return addedQuiz;
    }

    @GetMapping("/quizzes/{id}")
    public Quiz getQuizById(@PathVariable long id) {
        return quizService.getQuizByIndex(id);
    }

    @GetMapping("/quizzes/completed")
    public Page<QuizCompletion> getQuizCompletions(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                   @RequestParam(defaultValue = "0") Integer page,
                                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                                   @RequestParam(defaultValue = "completed_at") String sortBy) {
        return quizCompletionService.findByUserId(PageRequest.of(page, pageSize, Sort.by(sortBy).descending()),
                userDetails.getUsername());
    }

    @GetMapping("/quizzes")
    public Page<Quiz> getAllQuizzes(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        return quizService.getAllQuizzes(page, pageSize, sortBy);
    }

    @PostMapping("/quizzes/{id}/solve")
    public Response solveQuiz(@PathVariable long id,
                              @RequestBody Answer answer,
                              @AuthenticationPrincipal UserDetailsImpl userDetails) {
        var rightAnswer = List.copyOf(quizService.getQuizByIndex(id).getAnswer());
        var checkAnswer = answer.getAnswer() != null ? answer.getAnswer() : List.of();

        if (rightAnswer.equals(checkAnswer)) {
            User user = userService.findById(userDetails.getUsername()).get();
            user.addCompletion(id);
            userService.save(user);
            return Response.correct();
        } else {
            return Response.incorrect();
        }
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid User user) {
        if (userService.existsById(user.getEmail())) {
            return new ResponseEntity<>("this email address is already registered", HttpStatus.BAD_REQUEST);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.save(user);
        return new ResponseEntity<>(String.format("User \"%s\" successfully registered",
                user.getEmail()), HttpStatus.OK);
    }


    @DeleteMapping("/quizzes/{id}")
    public void deleteQuiz(@PathVariable long id, @AuthenticationPrincipal UserDetailsImpl user) {

        if (!quizService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (user.isAuthorOfQuiz(id)) {
            quizService.deleteById(id);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @Data
    static class Answer {
        private List<Integer> answer = new ArrayList<>();
    }



}