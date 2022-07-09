package engine.persistencelayer;

import engine.businesslayer.QuizCompletion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PagingQuizCompletionRep extends PagingAndSortingRepository<QuizCompletion, Long> {

    @Query(value = "SELECT * FROM COMPLETIONS u WHERE u.user_id = :idval",
            nativeQuery = true)
    public Page<QuizCompletion> findByUserId(Pageable pageable, @Param("idval") String user_id);

}
