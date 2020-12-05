package ffhs.students.projects.dienstplanverwaltung.database.sql;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SlotRepository extends JpaRepository<SlotEntity, Integer> {

}

