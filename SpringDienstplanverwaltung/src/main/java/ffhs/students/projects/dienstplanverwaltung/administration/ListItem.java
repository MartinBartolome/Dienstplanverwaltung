package ffhs.students.projects.dienstplanverwaltung.administration;


import ffhs.students.projects.dienstplanverwaltung.database.ILocal;
import ffhs.students.projects.dienstplanverwaltung.database.IServiceRole;
import ffhs.students.projects.dienstplanverwaltung.database.IUser;

public class ListItem {
    public enum DisplayType{ Default,Highlighted, Weakend ,Inactiv  } // Wie wird es angezeigt?

    private final DisplayType displayType;
    private final String title;
    private long id;

    public ListItem(DisplayType displayType, String title) {
        this.displayType = displayType;
        this.title = title;
    }
    //Lokale für Mitarbeiter
    public ListItem(ILocal local, IUser user){
        this.displayType = local.getOwner() == user ? DisplayType.Highlighted : DisplayType.Default;
        this.title = local.getTitle();
        this.id = local.getId();
    }
    //Lokale in Besitz
    public ListItem(ILocal local){
        this.displayType = local.isGranted() ? DisplayType.Default : DisplayType.Weakend;
        this.title = local.getTitle();
        this.id = local.getId();
    }
    public ListItem(IServiceRole serviceRole){
        this.displayType = serviceRole.isActive() ? DisplayType.Default : DisplayType.Inactiv;
        this.title = serviceRole.getName();
        this.id = serviceRole.getId();
    }
    public DisplayType getDisplayType() {  return displayType; }
    public String getTitle() {   return title;  }
    public long getId() { return id;  }
}
