package ffhs.students.projects.dienstplanverwaltung.database;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@DataJpaTest
class sqlDatabaseManagerTest {
   // @Autowired private DataSource dataSource;
    //@Autowired private JdbcTemplate jdbcTemplate;
    //@Autowired private EntityManager entityManager;
    //@Autowired private UserRepository userRepository;



    @Test
    void injectedComponentsAreNotNull(){
        //assertNotNull(dataSource);
        //assertNotNull(jdbcTemplate);
        //assertNotNull(entityManager);
        //assertNotNull(userRepository);
    }
}