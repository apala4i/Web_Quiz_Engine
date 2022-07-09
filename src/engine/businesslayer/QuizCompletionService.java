package engine.businesslayer;

import engine.persistencelayer.PagingQuizCompletionRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public class QuizCompletionService {
    private final PagingQuizCompletionRep repository;

    @Autowired
    public QuizCompletionService(PagingQuizCompletionRep repository) {
        this.repository = repository;
    }


    public Page<QuizCompletion> findByUserId(Pageable pageable, String user_id) {
        return repository.findByUserId(pageable, user_id);
    }

    public Page<QuizCompletion> findById(Integer pageNo, Integer pageSize, String sortBy) {
        var pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return repository.findAll(pageable);
    }

}
