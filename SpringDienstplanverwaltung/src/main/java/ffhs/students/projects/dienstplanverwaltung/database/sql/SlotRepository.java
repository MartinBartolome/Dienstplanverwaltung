package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.database.ILocal;
import ffhs.students.projects.dienstplanverwaltung.database.ISlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface SlotRepository extends JpaRepository<SlotEntity, Integer> {
    Optional<ISlot> findById(long id);
}

