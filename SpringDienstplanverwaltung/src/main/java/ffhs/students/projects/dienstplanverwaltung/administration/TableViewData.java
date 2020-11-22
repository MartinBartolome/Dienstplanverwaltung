package ffhs.students.projects.dienstplanverwaltung.administration;

import ffhs.students.projects.dienstplanverwaltung.database.ILocal;
import ffhs.students.projects.dienstplanverwaltung.database.IServiceRole;
import ffhs.students.projects.dienstplanverwaltung.database.IUser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TableViewData {
    private final String title;
    private final boolean showsAddButton;
    private final List<ListItem> items;


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
    public String getTitle() {
        return title;
    }

    public boolean isShowsAddButton() {
        return showsAddButton;
    }

    public List<ListItem> getItems() {
        return items;
    }
}
