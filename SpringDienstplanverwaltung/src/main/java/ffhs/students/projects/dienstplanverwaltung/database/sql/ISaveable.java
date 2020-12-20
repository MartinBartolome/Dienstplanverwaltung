package ffhs.students.projects.dienstplanverwaltung.database.sql;
import org.springframework.data.jpa.repository.JpaRepository;

interface ISaveable {
    default void save(JpaRepository repo)
    {
        repo.save(this);
    }
}
