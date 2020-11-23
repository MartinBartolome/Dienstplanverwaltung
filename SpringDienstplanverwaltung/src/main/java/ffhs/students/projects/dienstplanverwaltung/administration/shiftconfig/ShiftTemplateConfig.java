package ffhs.students.projects.dienstplanverwaltung.administration.shiftconfig;

import ffhs.students.projects.dienstplanverwaltung.Helper;
import ffhs.students.projects.dienstplanverwaltung.administration.DropDownData;
import ffhs.students.projects.dienstplanverwaltung.administration.TableViewData;
import ffhs.students.projects.dienstplanverwaltung.database.IShiftTemplate;

public class ShiftTemplateConfig {
    private final DropDownData recurrenceOptions;
    private final TableViewData days;
    private final TableViewData slots;
    private final String fromDate;
    private final String toDate;
    private final String title;
    private final long id;

    public ShiftTemplateConfig(IShiftTemplate shiftTemplate){
        recurrenceOptions = new DropDownData(shiftTemplate);
        days = TableViewData.getWeekDayTableForShiftTemplate(shiftTemplate);
        slots = TableViewData.getForSlots(shiftTemplate.getSlots());
        fromDate = Helper.stringFromDate( shiftTemplate.getFrom() );
        toDate = Helper.stringFromDate( shiftTemplate.getTo() );
        title = shiftTemplate.getTitle();
        id = shiftTemplate.getId();
    }

    public DropDownData getRecurrenceOptions() {  return recurrenceOptions;  }
    public TableViewData getDays() { return days;  }
    public TableViewData getSlots() { return slots; }
    public String getFromDate() { return fromDate; }
    public String getToDate() { return toDate;  }
    public String getTitle() { return title; }
    public long getId() { return id; }
}
