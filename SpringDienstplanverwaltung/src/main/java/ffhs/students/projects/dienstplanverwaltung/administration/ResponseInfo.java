package ffhs.students.projects.dienstplanverwaltung.administration;

public class ResponseInfo {
    private final boolean success;
    private final String message;

    public ResponseInfo(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean getSuccess() { return success;  }
    public String getMessage() { return message;  }
}
