package ffhs.students.projects.dienstplanverwaltung;

public class User implements IUser{
    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
