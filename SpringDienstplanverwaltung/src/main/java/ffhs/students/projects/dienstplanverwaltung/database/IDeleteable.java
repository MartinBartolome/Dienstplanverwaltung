package ffhs.students.projects.dienstplanverwaltung.database;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IDeleteable {
    default void delete(JpaRepository repo)
    {
        repo.delete(this);
    }
}
