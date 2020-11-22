package ffhs.students.projects.dienstplanverwaltung.administration;

import ffhs.students.projects.dienstplanverwaltung.database.ILocal;

import java.util.List;
import java.util.stream.Collectors;

public class SysAdminTenantConfig {
    private final String tab1Title;
    private final String tab2Title;
    private final String tab3Title;

    private final TwoColumnTableViewData table1Data;
    private final TwoColumnTableViewData table2Data;
    private final TwoColumnTableViewData table3Data;

    public String getTab1Title() { return tab1Title;  }
    public String getTab2Title() {  return tab2Title; }
    public String getTab3Title() {  return tab3Title;  }
    public TwoColumnTableViewData getTable1Data() { return table1Data; }
    public TwoColumnTableViewData getTable2Data() { return table2Data;  }
    public TwoColumnTableViewData getTable3Data() { return table3Data;  }



    public SysAdminTenantConfig(List<ILocal> locals){
        tab1Title = "aktiv";
        tab2Title = "beantragt";
        tab3Title = "still gelegt";

        List<ILocal> activeLocals = locals.stream()
                .filter(ILocal::isGranted)
                .filter(ILocal::isActive)
                .collect(Collectors.toList());

        List<ILocal> requestedLocals = locals.stream()
                .filter(local -> !local.isGranted())
                .collect(Collectors.toList());

        List<ILocal> inactiveLocals = locals.stream()
                .filter(local -> !local.isActive())
                .collect(Collectors.toList());

        table1Data = new TwoColumnTableViewData(activeLocals);
        table2Data = new TwoColumnTableViewData(requestedLocals);
        table3Data = new TwoColumnTableViewData(inactiveLocals);
    }
}
