package ffhs.students.projects.dienstplanverwaltung.administration;


import ffhs.students.projects.dienstplanverwaltung.database.ILocal;
import ffhs.students.projects.dienstplanverwaltung.database.IServiceRole;
import ffhs.students.projects.dienstplanverwaltung.database.IUser;

public class ListItem {
    public enum DisplayType{ Default,Highlighted,Inactiv  } // Wie wird es angezeigt?

    private final DisplayType displayType;
    private final String title;

    public ListItem(DisplayType displayType, String title) {
        this.displayType = displayType;
        this.title = title;
    }

    public ListItem(ILocal local, IUser user){
        this.displayType = local.getOwner() == user ? DisplayType.Highlighted : DisplayType.Default;
        this.title = local.getTitle();
    }
    public ListItem(IServiceRole serviceRole){
        this.displayType = serviceRole.isActive() ? DisplayType.Default : DisplayType.Inactiv;
        this.title = serviceRole.getName();
    }
    public DisplayType getDisplayType() {  return displayType; }
    public String getTitle() {   return title;  }
}
