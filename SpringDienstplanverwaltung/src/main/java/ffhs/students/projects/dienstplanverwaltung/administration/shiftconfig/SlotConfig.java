package ffhs.students.projects.dienstplanverwaltung.administration.shiftconfig;

import ffhs.students.projects.dienstplanverwaltung.administration.basecomponents.TableViewData;
import ffhs.students.projects.dienstplanverwaltung.database.IServiceRole;
import ffhs.students.projects.dienstplanverwaltung.database.ISlot;
import java.util.List;

public class SlotConfig {
    private long id; //SlotId
    private String title;
    private int numberOfEmployeesNeeded;
    private TableViewData serviceRoleTable;

    public SlotConfig() {}
    public SlotConfig(ISlot slot,List<IServiceRole> localServiceRoles){
        numberOfEmployeesNeeded = slot.getNumberOfEmployeesNeeded();
        id = slot.getSlotId();
        title = slot.getTitle();
        serviceRoleTable = TableViewData.getForServiceRoles(localServiceRoles,slot.getServiceRoles());
    }

    public String getTitle() { return title; }
    public TableViewData getServiceRoleTable() {  return serviceRoleTable; }
    public int getNumberOfEmployeesNeeded() { return numberOfEmployeesNeeded;  }
    public long getId() { return id; }
}
