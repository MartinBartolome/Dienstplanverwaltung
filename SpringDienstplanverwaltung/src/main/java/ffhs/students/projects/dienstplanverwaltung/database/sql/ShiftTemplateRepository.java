package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.database.IShiftTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShiftTemplateRepository extends JpaRepository<ShiftTemplateEntity, Integer> {
    List<IShiftTemplate> findByLocalId(@Param("local_id") long localId);
}
