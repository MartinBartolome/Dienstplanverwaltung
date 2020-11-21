package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.database.ILocal;
import ffhs.students.projects.dienstplanverwaltung.database.ISlot;
import ffhs.students.projects.dienstplanverwaltung.database.ISlotType;
import ffhs.students.projects.dienstplanverwaltung.database.IUser;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table
class LocalEntity implements ILocal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public UserEntity getOwner() {  return owner; }

    private String title;
    public String getTitle() { return title; }

    @ManyToOne
    @JoinColumn()
    private UserEntity owner;

    @OneToMany(mappedBy = "local")
    private List<EmployeeEntity> employees;

    @OneToMany(mappedBy = "local")
    private List<ShiftTemplateEntity> shiftTemplates;

    @OneToMany(mappedBy = "local")
    private List<ShiftEntity> shifts;

    @OneToMany(mappedBy = "local")
    private List<SlotTypeEntity> slotTypes;
    public List<ISlotType> getSlotTypes(){
        return slotTypes.stream()
                .map(ISlotType.class::cast)
                .collect(Collectors.toList());
    }

    @Override
    public long getId() { return id; }

    public LocalEntity(){}
    public LocalEntity(String title, IUser owner){
        this.title = title;
        if (owner instanceof UserEntity)
            this.owner = (UserEntity)owner;
    }
}
