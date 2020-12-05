package ffhs.students.projects.dienstplanverwaltung.administration.shiftconfig;

import ffhs.students.projects.dienstplanverwaltung.administration.TableViewData;
import ffhs.students.projects.dienstplanverwaltung.database.IServiceRole;
import ffhs.students.projects.dienstplanverwaltung.database.ISlot;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SlotConfig {
    private long id; //SlotId
    private String title;
    private int numberOfEmployeesNeeded;



    private TableViewData serviceRoleTable;

    public SlotConfig(ISlot slot,List<IServiceRole> localServiceRoles){
        numberOfEmployeesNeeded = slot.getNumberOfEmployeesNeeded();
        id = slot.getSlotId();
        slotTypeString = slot.getSlotType().getTitle();
        title = slot.getSlotType().getTitle();
        serviceRoleTable = TableViewData.getForServiceRoles(localServiceRoles,slot.getServiceRoles());
    }

    public String getTitle() { return title; }
    public TableViewData getServiceRoleTable() {  return serviceRoleTable; }








    private String slotTypeString;
    public void setSlotType(String slotType) {  this.slotTypeString = slotType; }
    public String getSlotType() {  return slotTypeString;  }


    public int getNumberOfEmployeesNeeded() { return numberOfEmployeesNeeded;  }


    public long getId() { return id; }



    public SlotConfig() {}


    /*
    public SlotConfig(long id, String slotTypeString) {
        this.id = id;
        this.slotTypeString = cleanTypeString(slotTypeString);
    }
     */

    //todo entfernen von Slottype als index
    private static String cleanTypeString(String typeString){

        if (typeString == null || !typeString.contains("("))
            return typeString;
        return typeString.substring(0,typeString.indexOf("(")-1);
    }

    public static Optional<SlotConfig> getSlotDataWithId(long id, List<SlotConfig> slotInfos){
        return slotInfos.stream()
                .filter(info -> info.getId() == id)
                .findFirst();
    }
}
