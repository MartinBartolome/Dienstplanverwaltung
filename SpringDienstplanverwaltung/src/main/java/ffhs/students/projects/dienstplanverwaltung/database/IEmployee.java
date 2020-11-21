package ffhs.students.projects.dienstplanverwaltung.database;

public interface IEmployee {
    IUser getUser();
    ILocal getLocal();
    boolean isEqual(IEmployee employee);
}
