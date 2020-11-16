package ffhs.students.projects.dienstplanverwaltung.database.sql;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftTemplateRepository extends JpaRepository<ShiftTemplateEntity, Integer> {
}
