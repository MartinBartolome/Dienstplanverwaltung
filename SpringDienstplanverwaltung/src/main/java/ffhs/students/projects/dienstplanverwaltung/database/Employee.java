package ffhs.students.projects.dienstplanverwaltung.database;

import ffhs.students.projects.dienstplanverwaltung.database.IEmployee;
import ffhs.students.projects.dienstplanverwaltung.database.IUser;

class Employee implements IEmployee {
    private final ILocal local;
    private IUser user;

    public Employee(IUser user,ILocal local) {
        this.local = local;
        this.user = user;
    }

    @Override
    public IUser getUser() {
        return user;
    }
    @Override
    public ILocal getLocal() { return local; }
}
