package engine.businesslayer;

import engine.persistencelayer.PagingQuizRep;
import engine.persistencelayer.QuizCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Collections;
import java.util.*;

@Service
public class QuizService {

    private final QuizCrudRepository repository;

    private final PagingQuizRep pagingQuizRep;

    @Autowired
    public QuizService(QuizCrudRepository repository, PagingQuizRep pagingQuizRep) {
        this.repository = repository;
        this.pagingQuizRep = pagingQuizRep;
    }

    public Quiz getQuizByIndex(Long index) {
        var quiz = repository.findById(index);
        if (quiz.isEmpty()) {
            throw new QuizNotFoundException(String.format("Quiz with id = %d not found", index));
        } else {
            return quiz.get();
        }
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Quiz addQuiz(Quiz quiz) {
        return repository.save(quiz);
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public Page<Quiz> getAllQuizzes(Integer pageNo, Integer pageSize, String sortBy) {
        var pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return pagingQuizRep.findAll(pageable);
    }

    public List<Quiz> getAllQuizzes() {
        Iterable<Quiz> quizzesIterable = repository.findAll();
        List<Quiz> quizzesList = new ArrayList<Quiz>();
        quizzesIterable.forEach(quizzesList::add);

        return quizzesList;

    }


}