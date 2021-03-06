package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.database.ILocal;
import ffhs.students.projects.dienstplanverwaltung.database.IServiceRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface ServiceRoleRepository  extends JpaRepository<ServiceRoleEntity, Integer> {
    Optional<IServiceRole> findFirstByLocalAndName(ILocal local, String name);
    Optional<IServiceRole> findById(long id);
}
