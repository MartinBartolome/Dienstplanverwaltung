package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.database.ILocal;
import ffhs.students.projects.dienstplanverwaltung.database.IUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface LocalRepository extends JpaRepository<LocalEntity, Integer> {
     Optional<ILocal> findById(long id);
     List<ILocal> findAllByOwner(IUser owner);

     //Unittest
     Optional<ILocal> findFirstByIsUnittest(boolean isUnitTest);
}
