package ffhs.students.projects.dienstplanverwaltung;

public class SlotType implements ISlotType{
    private String title;

    public SlotType(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
