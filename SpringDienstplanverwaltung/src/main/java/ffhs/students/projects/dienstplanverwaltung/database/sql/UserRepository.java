package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.database.IUser;
import ffhs.students.projects.dienstplanverwaltung.database.sql.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<IUser> findByNickname(String nickname);
    Optional<IUser> findByNicknameAndAndPassword(String nickname,String password);
}

