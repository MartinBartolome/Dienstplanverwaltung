package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.database.ILocal;
import ffhs.students.projects.dienstplanverwaltung.database.IShift;
import ffhs.students.projects.dienstplanverwaltung.database.IShiftTemplate;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ShiftRepository extends JpaRepository<ShiftEntity, Integer> {
    List<IShift> findAllByLocalAndDayBetween(ILocal local,LocalDate start, LocalDate end);
    Optional<IShift> findFirstByDayIsAndShiftTemplateIsAndLocalIs(LocalDate day, IShiftTemplate shiftTemplate,ILocal local);
}
