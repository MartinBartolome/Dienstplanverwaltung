package ffhs.students.projects.dienstplanverwaltung.administration.shiftconfig;

import ffhs.students.projects.dienstplanverwaltung.database.ISlotType;

import java.util.List;
import java.util.Optional;

public class SlotData {
    private final long id;
    private final String slotTypeString;
    private List<Long> serviceRolesIds;

    public int getNumberOfEmployeesNeeded() { return numberOfEmployeesNeeded;  }

    private int numberOfEmployeesNeeded;

    public long getId() { return id; }
    public String getSlotType() {  return slotTypeString;  }
    public List<Long> getServiceRolesIds() { return serviceRolesIds; }

    public SlotData(long id, String slotTypeString) {
        this.id = id;
        this.slotTypeString = slotTypeString;
    }
    public static Optional<SlotData> getSlotDataWithId(long id, List<SlotData> slotInfos){
        return slotInfos.stream()
                .filter(info -> info.getId() == id)
                .findFirst();
    }
    public void setServiceRolesIds(List<Long> serviceRolesIds) {   this.serviceRolesIds = serviceRolesIds; }
}
