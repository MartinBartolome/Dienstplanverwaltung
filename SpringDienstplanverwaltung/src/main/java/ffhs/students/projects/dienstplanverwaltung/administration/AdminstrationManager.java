package ffhs.students.projects.dienstplanverwaltung.administration;

import ffhs.students.projects.dienstplanverwaltung.database.IDatabaseManager;
import ffhs.students.projects.dienstplanverwaltung.database.ILocal;
import ffhs.students.projects.dienstplanverwaltung.database.IUser;
import ffhs.students.projects.dienstplanverwaltung.database.sql.SqlDatabaseManager;

import java.util.List;
import java.util.Optional;

public class AdminstrationManager {
    static public IDatabaseManager databaseManager = new SqlDatabaseManager();

    public static TableViewData getChooseLocal(String userNickName){
        if (userNickName.isEmpty())
            return new TableViewData();
        Optional<IUser> user = databaseManager.getUser(userNickName);
        if (!user.isPresent())
            return new TableViewData();

        List<ILocal> locals = databaseManager.getLocalsForUser(user.get());
        return new TableViewData(locals,user.get());
    }
}
