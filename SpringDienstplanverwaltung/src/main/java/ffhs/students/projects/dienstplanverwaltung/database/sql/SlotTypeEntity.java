package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.database.ISlotType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class SlotTypeEntity implements ISlotType {
    @Id
    @Column(length = 64)
    private String title;

    @Override
    public String getTitle() { return title; }

    @OneToMany(mappedBy = "slotType")
    private List<SlotEntity> slots;

    public SlotTypeEntity(){}

    public SlotTypeEntity(String title){ this.title = title; }
}
