package engine.businesslayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "COMPLETIONS")
public class QuizCompletion {

    @JsonIgnore
    @Id
    @GeneratedValue
    Long id;

    @JsonProperty(value = "id")
    Long quizId;

    LocalDateTime completedAt;

    public QuizCompletion(Long quizId, LocalDateTime completedAt) {
        this.quizId = quizId;
        this.completedAt = completedAt;
    }

    public QuizCompletion(Long quizId) {
        this.quizId = quizId;
        this.completedAt = LocalDateTime.now();
    }
}
