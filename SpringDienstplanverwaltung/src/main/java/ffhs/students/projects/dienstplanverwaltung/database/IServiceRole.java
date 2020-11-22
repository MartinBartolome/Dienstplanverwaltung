package ffhs.students.projects.dienstplanverwaltung.database;


public interface IServiceRole {
    String getName();
    boolean isActive();
    ILocal getLocal();
}
