package ffhs.students.projects.dienstplanverwaltung.administration.basecomponents;

import ffhs.students.projects.dienstplanverwaltung.Helper;
import ffhs.students.projects.dienstplanverwaltung.database.IShiftTemplate;
import ffhs.students.projects.dienstplanverwaltung.database.RecurrenceType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DropDownData {
    private List<ListItem>dropDownOptions;
    private final ListItem selectedItem;

    public static DropDownData getForRecurrences(){
        DropDownData result = new DropDownData();
        result.dropDownOptions = Stream.of(RecurrenceType.values())
                .map(Helper::getRecurrenceString).map(optionString -> new ListItem(optionString,-1))
                .collect(Collectors.toList());
        return result;
    }

    public DropDownData(IShiftTemplate shiftTemplate){
        dropDownOptions = Stream.of(RecurrenceType.values())
                .map(Helper::getRecurrenceString).map(optionString -> new ListItem(optionString,-1))
                .collect(Collectors.toList());
        selectedItem = dropDownOptions.stream()
                .filter(item -> item.getTitle().equals(Helper.getRecurrenceString(shiftTemplate.getRecurrence())))
                .findFirst().orElse(null);
    }
    public DropDownData(){
        dropDownOptions = new ArrayList<>();
        selectedItem = new ListItem();
    }
    public List<ListItem> getDropDownOptions() {  return dropDownOptions; }
    public ListItem getSelectedItem() {  return selectedItem;  }
}
