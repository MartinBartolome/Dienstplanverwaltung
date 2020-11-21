package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.database.ISlotType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
class SlotTypeEntity implements ISlotType {
    @Id
    @Column(length = 64)
    private String title;

    @Override
    public String getTitle() { return title; }

    @ManyToOne
    @JoinColumn()
    private LocalEntity local;

    @OneToMany(mappedBy = "slotType")
    private List<SlotEntity> slots;

    public SlotTypeEntity(){}

    public SlotTypeEntity(String title,LocalEntity local){
        this.title = title;
        this.local = local;
    }
}
