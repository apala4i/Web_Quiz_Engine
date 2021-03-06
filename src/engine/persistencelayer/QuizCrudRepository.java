package engine.persistencelayer;

import engine.businesslayer.Security.User;
import org.springframework.data.repository.CrudRepository;
import engine.businesslayer.Quiz;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizCrudRepository extends CrudRepository<Quiz, Long> {

}