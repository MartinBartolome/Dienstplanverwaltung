package ffhs.students.projects.dienstplanverwaltung.administration.basecomponents;

public class ResponseInfo {
    private final boolean success;
    private final String message;
    private boolean userIsSysAdmin = false;

    public ResponseInfo(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResponseInfo(boolean success, String message,boolean userIsSysAdmin) {
        this.success = success;
        this.message = message;
        this.userIsSysAdmin = userIsSysAdmin;
    }

    public boolean getSuccess() { return success;  }
    public String getMessage() { return message;  }
    public boolean isUserIsSysAdmin() { return userIsSysAdmin; }
}
