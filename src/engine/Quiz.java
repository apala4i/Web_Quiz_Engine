package engine;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import lombok.Getter;

import javax.validation.*;
import javax.validation.constraints.*;

@Setter
@Getter
public class Quiz {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @NotNull(message = "title field is required")
    private String title;

    @NotNull(message = "text field is required")
    private String text;

    @NotNull
    @Size(min = 2, message = "min options size = 2")
    private String[] options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int[] answer = new int[]{};

    public Quiz(){}

    public Quiz(String title, String text, String[] options) {
        this.title = title;
        this.text = text;
        this.options = options;
    }
}
