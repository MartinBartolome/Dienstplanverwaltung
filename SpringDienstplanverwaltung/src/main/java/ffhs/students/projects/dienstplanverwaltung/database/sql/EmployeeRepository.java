package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.database.IEmployee;
import ffhs.students.projects.dienstplanverwaltung.database.ILocal;
import ffhs.students.projects.dienstplanverwaltung.database.IUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    Optional<IEmployee> findFirstByUserAndLocal(IUser user, ILocal local);
}

