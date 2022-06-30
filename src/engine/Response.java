package engine;

public class Response {
    private boolean success;
    private String feedback;

    private static final String CORRECT_TEXT = "Congratulations";
    private static final String INCORRECT_TEXT = "Wrong answer!";

    private Response(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }


    public static Response correct() {
        return new Response(true, CORRECT_TEXT);
    }

    public static Response incorrect() {
        return new Response(false, INCORRECT_TEXT);
    }

    public String getFeedback() {
        return feedback;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

