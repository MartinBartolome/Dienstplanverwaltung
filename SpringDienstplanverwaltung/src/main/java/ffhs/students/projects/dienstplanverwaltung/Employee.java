package ffhs.students.projects.dienstplanverwaltung;

public class Employee implements IEmployee{

    private IUser user;

    public Employee(IUser user) {
        this.user = user;
    }

    @Override
    public IUser getUser() {
        return user;
    }
}
