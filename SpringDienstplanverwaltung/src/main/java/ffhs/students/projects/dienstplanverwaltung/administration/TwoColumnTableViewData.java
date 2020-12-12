package ffhs.students.projects.dienstplanverwaltung.administration;

import ffhs.students.projects.dienstplanverwaltung.database.ILocal;
import ffhs.students.projects.dienstplanverwaltung.database.IUser;

import java.util.List;
import java.util.stream.Collectors;

public class TwoColumnTableViewData {
    private TableViewData table1;
    private TableViewData table2;

    public TwoColumnTableViewData(List<ILocal> locals){
        String title1 = "Lokal";
        List<ListItem> items1 = locals.stream()
                .map(ListItem::new)
                .collect(Collectors.toList());
        table1 = new TableViewData(title1,false,items1);

        String title2 = "Besitzer";
        List<ListItem> items2 = locals.stream().map(ILocal::getOwner)
                .map(ListItem::new)
                .collect(Collectors.toList());
        table2 = new TableViewData(title2,false,items2);
    }

    public TableViewData getTable2() { return table2; }
    public TableViewData getTable1() { return table1; }
}
