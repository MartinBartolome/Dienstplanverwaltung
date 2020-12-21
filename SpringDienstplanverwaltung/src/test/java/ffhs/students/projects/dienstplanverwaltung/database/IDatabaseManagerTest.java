package ffhs.students.projects.dienstplanverwaltung.database;

import ffhs.students.projects.dienstplanverwaltung.database.sql.SqlDatabaseManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class IDatabaseManagerTest {
    @Autowired  public IDatabaseManager dbManager;

    /* Bedingung für den Test ist ein Lokal mit der id = 10000
    dieses muss (falls nicht vorhanden) "per Hand" angelegt werden
     */
    long localID = 10000;
    String testUserName = "UnitTestUser";
    String testUserPassword = "passwort";

    @BeforeAll
    static void beforeAll(){
        System.out.println("beforeAll");


    }


    @Test
    void getLocalById() {
        Optional<ILocal> testLocal = dbManager.getLocalById(localID);
        Assertions.assertTrue(testLocal.isPresent());
    }

    @Test
    void getAllLocals() {
        List<ILocal> locals = dbManager.getAllLocals();
        Optional<ILocal> testLocal = locals.stream().filter(local -> local.getId() == localID).findFirst();
        Assertions.assertTrue(testLocal.isPresent());
    }

    @Test
    void createUserIfNotExist() {
        dbManager.createUserIfNotExist(testUserName,testUserPassword);
        Optional<IUser> user = dbManager.getUser(testUserName);
        Assertions.assertTrue(user.isPresent());
    }

    @Test
    void getUser() {
        createUserIfNotExist();
    }

    @Test
    void getUserForNicknameAndPassword() {
        createUserIfNotExist();

        Optional<IUser> user = dbManager.getUserForNicknameAndPassword(testUserName,testUserPassword);
        Assertions.assertTrue(user.isPresent());

        user = dbManager.getUserForNicknameAndPassword(testUserName,"falschesPasswort");
        Assertions.assertFalse(user.isPresent());

        user = dbManager.getUserForNicknameAndPassword("falscherUsername",testUserPassword);
        Assertions.assertFalse(user.isPresent());
    }

    @Test
    void updateLocal() {
        Optional<ILocal> testLocal = dbManager.getLocalById(localID);
        Assertions.assertTrue(testLocal.isPresent());

        String title = testLocal.get().getTitle();
        boolean isActive = testLocal.get().isActive();

        title = title.equals("test1") ? "test2" : "test1";
        isActive = !isActive;

        dbManager.updateLocal(localID,title,isActive);
        testLocal = dbManager.getLocalById(localID);
        Assertions.assertTrue(testLocal.isPresent());

        assertEquals(isActive, testLocal.get().isActive());
        assertEquals(title, testLocal.get().getTitle());
    }

    @Test
    void localSetState() {
        Optional<ILocal> testLocal = dbManager.getLocalById(localID);
        Assertions.assertTrue(testLocal.isPresent());

        boolean isActive = testLocal.get().isActive();
        isActive = !isActive;

        dbManager.localSetState(localID,isActive);
        testLocal = dbManager.getLocalById(localID);
        Assertions.assertTrue(testLocal.isPresent());

        assertEquals(isActive, testLocal.get().isActive());
    }

    // grantLocal genehmigt ein Lokal
    // in diesem Prozess wird der Besitzer zum Mitarbeiter
    // und erhält die Rolle Manager
    @Test
    void grantLocal() {
        Optional<ILocal> testLocal = dbManager.getLocalById(localID);
        Assertions.assertTrue(testLocal.isPresent());
        ((SqlDatabaseManager) dbManager).updateLocal(localID,testLocal.get().getTitle(),testLocal.get().isActive(),false);

        createUserIfNotExist();
        Optional<IUser> user = dbManager.getUser(testUserName);
        Assertions.assertTrue(user.isPresent());

        ((SqlDatabaseManager) dbManager).createManagerRole(localID);
        ((SqlDatabaseManager) dbManager).setOwnerForLocal(localID,testUserName);
        assertEquals(testLocal.get().getOwner().getNickname(),testUserName);

        testLocal = dbManager.getLocalById(localID);
        Assertions.assertTrue(testLocal.isPresent());
        Assertions.assertFalse(testLocal.get().isGranted());

        // Status ist granted
        dbManager.grantLocal(localID);
        testLocal = dbManager.getLocalById(localID);
        Assertions.assertTrue(testLocal.isPresent());
        Assertions.assertTrue(testLocal.get().isGranted());

        // Admin Rolle existiert für Lokal?
        Optional<IServiceRole> adminRole = testLocal.get().getAdminRole();
        Assertions.assertTrue(adminRole.isPresent());

        // Owner ist Admin?
        Optional<IEmployee> admin  = testLocal.get()
                .getEmployees().stream()
                .filter(empl -> empl.getUser().getNickname().equals(testUserName))
                .findFirst();
        Assertions.assertTrue(admin.isPresent());
    }




    @Test
    void getShift() {

    }

    @Test
    void getShifts() {
    }

    @Test
    void getShiftTemplates() {
    }

    @Test
    void getShiftTemplateById() {
    }

    @Test
    void getEmployeeForName() {
    }

    @Test
    void getGrantedLocalsForUser() {
    }



    @Test
    void getSlotForSlotIdAndShift() {
    }

    @Test
    void getOwnedLocalsForUser() {
    }

    @Test
    void createOrUpdateEmployee() {
    }

    @Test
    void createShift() {
    }

    @Test
    void createEmployeeInLocal() {
    }



    @Test
    void createOrUpdateShiftTemplate() {
    }

    @Test
    void requestNewLocal() {
    }

    @Test
    void assignEmployeeToSlot() {
    }

    @Test
    void applyEmployeeToSlot() {
    }

    @Test
    void addServiceRole() {
    }

    @Test
    void updateServiceRole() {
    }



    @Test
    void setIsCanceledForShift() {
    }
}