package engine.businesslayer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import lombok.Getter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Quiz {


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue
    private long id;

    @NotNull(message = "title field is required")
    private String title;

    @NotNull(message = "text field is required")
    private String text;

    @NotNull
    @Size(min = 2, message = "min options size = 2")
    @ElementCollection
    private List<String> options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ElementCollection
    private List<Integer> answer = new ArrayList<>();

    public Quiz(){}

    public Quiz(String title, String text, List<String> options) {
        this.title = title;
        this.text = text;
        this.options = options;
    }
}