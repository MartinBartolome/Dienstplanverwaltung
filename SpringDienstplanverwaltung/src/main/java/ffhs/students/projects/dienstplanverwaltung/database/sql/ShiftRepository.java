package ffhs.students.projects.dienstplanverwaltung.database.sql;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftRepository extends JpaRepository<ShiftEntity, Integer> {
}
