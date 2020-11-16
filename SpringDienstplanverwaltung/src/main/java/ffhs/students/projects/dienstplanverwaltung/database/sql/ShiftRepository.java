package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.database.IShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface ShiftRepository extends JpaRepository<ShiftEntity, Integer> {
    List<IShift> findAllByDayBetween(@Param("day") LocalDate start, LocalDate end);

}
