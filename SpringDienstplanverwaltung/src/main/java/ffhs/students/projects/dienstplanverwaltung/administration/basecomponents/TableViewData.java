package ffhs.students.projects.dienstplanverwaltung.administration.basecomponents;

import ffhs.students.projects.dienstplanverwaltung.Helper;
import ffhs.students.projects.dienstplanverwaltung.database.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TableViewData {
    private final String title;
    private final boolean showsAddButton;
    private final List<ListItem> items;

    public static TableViewData getForOwnedLocals(List<ILocal> locals){
        String title = "Lokale in meinem Besitz";
        List<ListItem> items = locals.stream()
                .map(ListItem::new)
                .collect(Collectors.toList());
        return new TableViewData(title,true,items);
    }


    //Lokale, in denen User Mitarbeiter ist
    public TableViewData(List<ILocal> locals, IUser user){
        this.title = "Meine Lokale";
        this.showsAddButton = false;
        items = locals.stream()
                .map(item -> new ListItem(item,user))
                .collect(Collectors.toList());
    }
    public TableViewData(List<IServiceRole> serviceRoles){
        this.title = "Dienstrollen";
        this.showsAddButton = true;
        items = serviceRoles.stream()
                .map(ListItem::new)
                .collect(Collectors.toList());
    }
    public TableViewData(List<IServiceRole> localServiceRoles, List<IServiceRole> employeeServiceRoles){
        this.title = "Dienstrollen";
        this.showsAddButton = true;
        items = localServiceRoles.stream()
                .map(role -> new ListItem(role,employeeServiceRoles.contains(role)))
                .collect(Collectors.toList());
    }



    public static TableViewData getForServiceRoles(List<IServiceRole> localRoles, List<IServiceRole> slotRoles){
        String title = "Dienstrollen";
        List<ListItem> items = localRoles.stream()
                .map(localRole -> new ListItem(localRole.getName(),slotRoles.contains(localRole),localRole.getId()))
                .collect(Collectors.toList());
        return new TableViewData(title,true,items);
    }
    public static TableViewData getForShiftTemplates(List<IShiftTemplate> shiftTemplates){
        String title = "Schichttemplates";
        List<ListItem> items = shiftTemplates.stream()
                .map(ListItem::new)
                .collect(Collectors.toList());
        return new TableViewData(title,true,items);
    }
    public static TableViewData getForSlots(List<ISlot> slots){
        String title = "Slots";
        List<ListItem> items = slots.stream()
                .map(ListItem::new)
                .collect(Collectors.toList());
        return new TableViewData(title,true,items);
    }

    public static TableViewData getWeekDayTableForShiftTemplate(IShiftTemplate shiftTemplate){
        String title = "Tage";
        List<ListItem> items = Helper.weekDayList().stream()
                .map(day -> new ListItem(Helper.getDayString(day),
                        shiftTemplate.getWeekDays().contains(day)))
                .collect(Collectors.toList());
        return new TableViewData(title,false,items);
    }
    public static TableViewData getWeekDayTable(){
        String title = "Tage";
        List<ListItem> items = Helper.weekDayList().stream()
                .map(day -> new ListItem(Helper.getDayString(day),false))
                .collect(Collectors.toList());
        return new TableViewData(title,false,items);
    }

    public static TableViewData getForEmployees(List<IEmployee> employees) {
        String title = "Mitarbeiter";
        List<ListItem> items = employees.stream().map(ListItem::new).collect(Collectors.toList());
        return new TableViewData(title,true,items);
    }

    public TableViewData(String title, boolean showsAddButton, List<ListItem> items) {
        this.title = title;
        this.showsAddButton = showsAddButton;
        this.items = items;
    }
    public TableViewData() {
        this.title = "DataError";
        this.showsAddButton = true;
        this.items = new ArrayList<>();
    }
    public TableViewData(String title) {
        this.title = title;
        this.showsAddButton = false;
        this.items = new ArrayList<>();
    }
    public String getTitle() {
        return title;
    }
    public boolean isShowsAddButton() { return showsAddButton; }
    public List<ListItem> getItems() {
        return items;
    }
}
