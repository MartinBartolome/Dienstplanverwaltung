package ffhs.students.projects.dienstplanverwaltung.database.sql;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface SlotTypeRepository extends JpaRepository<SlotTypeEntity, Integer> {
    public Optional<SlotTypeEntity> findByTitle(String title);
}
