package ffhs.students.projects.dienstplanverwaltung.administration.basecomponents;


import ffhs.students.projects.dienstplanverwaltung.database.*;

public class ListItem {
    public enum DisplayType{ Default,Highlighted, Weakend ,Inactiv  } // Wie wird es angezeigt?

    private DisplayType displayType;
    private String title;
    private long id;
    private boolean isSelected;
    private boolean isEditable;
    private boolean isDeleteable;

    public ListItem(){  }
    public ListItem(ISlot slot) {
        this.displayType = DisplayType.Default;
        this.title = slot.getSlotStringWithNeededEmpl();
        this.isSelected = false;
        this.id = slot.getSlotId();
    }
    public ListItem( String title, long id) {
        this.displayType = DisplayType.Default;
        this.title = title;
        this.isSelected = false;
        this.id = id;
    }
    public ListItem( String title, boolean isSelected) {
        this.displayType = DisplayType.Default;
        this.title = title;
        this.isSelected = isSelected;
    }
    public ListItem( String title, boolean isSelected,long id) {
        this.displayType = DisplayType.Default;
        this.title = title;
        this.isSelected = isSelected;
        this.id = id;
    }
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
        this.isDeleteable = !serviceRole.isAdminRole();
        this.isEditable = !serviceRole.isAdminRole();
    }
    public ListItem(IUser user){
        this.displayType = DisplayType.Default;
        this.title = user.getNickname();
        this.id = -1;
    }
    public ListItem(IShiftTemplate shiftTemplate){
        this.displayType = DisplayType.Default;
        this.title = shiftTemplate.getLongTitle();
        this.id = shiftTemplate.getId();
    }

    public ListItem(IEmployee employee){
        this.displayType = DisplayType.Default;
        this.title = employee.getUser().getNickname();
        this.id = employee.getId();
    }
    public ListItem(IServiceRole serviceRole, boolean isSelected){
        this.displayType = DisplayType.Default;
        this.title = serviceRole.getName();
        this.id = serviceRole.getId();
        this.isSelected = isSelected;
    }

    //get
    public DisplayType getDisplayType() {  return displayType; }
    public String getTitle() {   return title;  }
    public long getId() { return id;  }
    public boolean getSelected() { return isSelected;  }
    public boolean getIsEditable() { return isEditable; }
    public boolean getIsDeleteable() { return isDeleteable; }

    //set
    public void setSelected(boolean selected) { isSelected = selected;  }
}
