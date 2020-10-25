package ffhs.students.projects.dienstplanverwaltung.database;

import ffhs.students.projects.dienstplanverwaltung.database.ISlotType;

class SlotType implements ISlotType {
    private String title;

    public SlotType(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
