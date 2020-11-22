package ffhs.students.projects.dienstplanverwaltung.database;


import java.util.List;

public interface IEmployee {
    long getId();
    IUser getUser();
    ILocal getLocal();
    boolean isEqual(IEmployee employee);
    List<IServiceRole> getServiceRoles();
    boolean isActive();
    double getHourlyRate();
    String getCurrency();
    int getMonthlyContingent();
}
