package ffhs.students.projects.dienstplanverwaltung.administration;

import ffhs.students.projects.dienstplanverwaltung.database.ILocal;
import ffhs.students.projects.dienstplanverwaltung.database.IUser;

import java.util.List;
import java.util.stream.Collectors;

public class TwoColumnTableViewData {
    private String title1;
    private String title2;
    private List<ListItem> items1;
    private List<ListItem> items2;

    public TwoColumnTableViewData(List<ILocal> locals){
        title1 = "Lokal";
        title2 =  "Besitzer";
        items1 = locals.stream()
                .map(ListItem::new)
                .collect(Collectors.toList());
        items2 = locals.stream().map(ILocal::getOwner)
                .map(ListItem::new)
                .collect(Collectors.toList());
    }

    public String getTitle1() { return title1;  }
    public String getTitle2() { return title2; }
    public List<ListItem> getItems1() { return items1; }
    public List<ListItem> getItems2() { return items2; }
}
