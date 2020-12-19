package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.database.ILocal;
import ffhs.students.projects.dienstplanverwaltung.database.IUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface LocalRepository extends JpaRepository<LocalEntity, Integer> {
    public Optional<ILocal> findById(long id);
    public List<ILocal> findAllByOwner(IUser owner);
}
