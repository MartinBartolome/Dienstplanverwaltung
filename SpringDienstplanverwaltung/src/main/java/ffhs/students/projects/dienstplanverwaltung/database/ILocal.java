package ffhs.students.projects.dienstplanverwaltung.database;

import java.util.List;

public interface ILocal {
    long getId();
    List<ISlotType> getSlotTypes();
}
