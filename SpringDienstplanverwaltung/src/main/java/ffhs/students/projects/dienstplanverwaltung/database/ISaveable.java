package ffhs.students.projects.dienstplanverwaltung.database;

import ffhs.students.projects.dienstplanverwaltung.database.sql.SlotRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISaveable {
    default void save(JpaRepository repo)
    {
        repo.save(this);
    }
}
