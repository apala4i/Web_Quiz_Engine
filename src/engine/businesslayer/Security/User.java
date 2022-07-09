package engine.businesslayer.Security;

import java.util.ArrayList;
import java.util.HashSet;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import engine.businesslayer.QuizCompletion;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Pattern(regexp = ".+@.+\\..+")
    private String email;

    @Length(min=5)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Long> quizIds = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<QuizCompletion> completions = new ArrayList<>();

    @Transient
    @JsonIgnore // not used now
    private List<String> role = new ArrayList<>();

    public void addCompletion(Long quizId) {
        completions.add(new QuizCompletion(quizId));
    }

}
