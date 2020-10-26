package ffhs.students.projects.dienstplanverwaltung.database;

import ffhs.students.projects.dienstplanverwaltung.database.IEmployee;
import ffhs.students.projects.dienstplanverwaltung.database.IUser;

class Employee implements IEmployee {

    private IUser user;

    public Employee(IUser user) {
        this.user = user;
    }

    @Override
    public IUser getUser() {
        return user;
    }
}
