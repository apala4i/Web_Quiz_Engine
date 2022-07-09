package engine.persistencelayer;

import engine.businesslayer.Quiz;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagingQuizRep extends PagingAndSortingRepository<Quiz, Long> {
}
