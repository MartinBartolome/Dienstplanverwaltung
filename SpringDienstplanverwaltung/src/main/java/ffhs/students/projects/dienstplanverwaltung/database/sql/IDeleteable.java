package ffhs.students.projects.dienstplanverwaltung.database.sql;
import org.springframework.data.jpa.repository.JpaRepository;

interface IDeleteable {
    default void delete(JpaRepository repo) { repo.delete(this); }
}
