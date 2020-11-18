package ffhs.students.projects.dienstplanverwaltung.database;

class User implements IUser {
    private String email;

    public User(String name) {
        this.email = name;
    }

    @Override
    public String getEmail() {
        return email;
    }
}
