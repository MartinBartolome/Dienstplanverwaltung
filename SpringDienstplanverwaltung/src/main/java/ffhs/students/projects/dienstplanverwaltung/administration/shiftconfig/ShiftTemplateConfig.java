package ffhs.students.projects.dienstplanverwaltung.administration.shiftconfig;

import ffhs.students.projects.dienstplanverwaltung.Helper;
import ffhs.students.projects.dienstplanverwaltung.administration.basecomponents.DropDownData;
import ffhs.students.projects.dienstplanverwaltung.administration.basecomponents.TableViewData;
import ffhs.students.projects.dienstplanverwaltung.database.IServiceRole;
import ffhs.students.projects.dienstplanverwaltung.database.IShiftTemplate;
import ffhs.students.projects.dienstplanverwaltung.database.ISlot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ShiftTemplateConfig {
    private final DropDownData recurrenceOptions;
    private final TableViewData days;
    private final TableViewData slots;
    private final String fromDate;
    private final String toDate;
    private final String title;
    private final long id;
    private final List<SlotConfig> slotInfos;
    private final String startTime;
    private final String endTime;

    public ShiftTemplateConfig(IShiftTemplate shiftTemplate,List<IServiceRole> localServiceRoles ){
        recurrenceOptions = new DropDownData(shiftTemplate);
        days = TableViewData.getWeekDayTableForShiftTemplate(shiftTemplate);
        slots = TableViewData.getForSlots(shiftTemplate.getSlots());
        fromDate = Helper.stringFromDate( shiftTemplate.getFrom() );
        toDate = Helper.stringFromDate( shiftTemplate.getTo() );
        title = shiftTemplate.getTitle();
        id = shiftTemplate.getId();
        startTime = Helper.stringFromTime(shiftTemplate.getFromTime());
        endTime =  Helper.stringFromTime(shiftTemplate.getToTime());
        slotInfos = generateSlotInfos(shiftTemplate.getSlots(),localServiceRoles);
    }
    public ShiftTemplateConfig(){
        recurrenceOptions = DropDownData.getForRecurrences();
        days = TableViewData.getWeekDayTable();
        slots = TableViewData.getForSlots(new ArrayList<>());
        fromDate = "";
        toDate = "";
        title = "";
        id = -1;
        startTime = "";
        endTime ="";
        slotInfos = new ArrayList<>();
    }
    public DropDownData getRecurrenceOptions() {  return recurrenceOptions;  }
    public TableViewData getDays() { return days;  }
    public TableViewData getSlots() { return slots; }
    public String getFromDate() { return fromDate; }
    public String getToDate() { return toDate;  }
    public String getTitle() { return title; }
    public long getId() { return id; }
    public String getStartTime() {  return startTime; }
    public String getEndTime() {  return endTime;  }
    public boolean isValid(){
        if (recurrenceOptions == null || title.isEmpty() || fromDate.isEmpty())
            return false;

        Optional<LocalDate> from = Helper.dateFromString(getFromDate());
        if (!from.isPresent())
            return false;

        Optional<LocalTime> start = Helper.timeFromString(startTime);
        Optional<LocalTime> end = Helper.timeFromString(endTime);
        return start.isPresent() && end.isPresent();
    }

    public List<SlotConfig> getSlotInfos(){
        return slotInfos;
    }
    private List<SlotConfig> generateSlotInfos(List<ISlot> slots,List<IServiceRole> localServiceRoles){
        return slots.stream()
                .map(slot -> new SlotConfig(slot,localServiceRoles))
                .collect(Collectors.toList());
    }
}
