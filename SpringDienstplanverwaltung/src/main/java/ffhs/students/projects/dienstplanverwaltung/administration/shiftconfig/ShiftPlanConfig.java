package ffhs.students.projects.dienstplanverwaltung.administration.shiftconfig;

import ffhs.students.projects.dienstplanverwaltung.administration.basecomponents.TableViewData;
import ffhs.students.projects.dienstplanverwaltung.database.ILocal;
import ffhs.students.projects.dienstplanverwaltung.database.IShiftTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShiftPlanConfig {
    private final TableViewData shiftTemplatesTable;
    private final List<ShiftTemplateConfig> shiftTemplateConfigs;



    private final ShiftTemplateConfig emptyShiftTemplateConfig;
    public ShiftPlanConfig(ILocal local){
        List<IShiftTemplate> shiftTemplates = local.getShiftTemplates();
        shiftTemplatesTable = TableViewData.getForShiftTemplates(shiftTemplates);
        shiftTemplateConfigs = shiftTemplates.stream()
                .map(template -> new ShiftTemplateConfig(template,local.getServiceRoles()))
                .collect(Collectors.toList());
        emptyShiftTemplateConfig = new ShiftTemplateConfig();
    }
    public ShiftPlanConfig(){
        shiftTemplatesTable = new TableViewData();
        shiftTemplateConfigs = new ArrayList<>();
        emptyShiftTemplateConfig = new ShiftTemplateConfig();
    }

    public TableViewData getShiftTemplatesTable() { return shiftTemplatesTable;  }
    public List<ShiftTemplateConfig> getShiftTemplateConfigs() { return shiftTemplateConfigs; }
    public ShiftTemplateConfig getEmptyShiftTemplateConfig() { return emptyShiftTemplateConfig; }
}
