package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.database.*;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table
class LocalEntity implements ILocal, ISaveable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Override
    public long getId() { return id; }

    public void setTitle(String title) {
        if (!title.isEmpty())
            this.title = title;
    }

    private String title;
    public String getTitle() { return title; }

    public void setActive(boolean active) { isActive = active; }

    private boolean isActive;

    public void setGranted(boolean granted) {  isGranted = granted;  }

    public boolean isGranted() {  return isGranted;  }
    private boolean isGranted;


    @ManyToOne
    @JoinColumn()
    private UserEntity owner;
    public UserEntity getOwner() {  return owner; }

    @OneToMany(mappedBy = "local")
    private List<ServiceRoleEntity> serviceRoles;

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

    public List<IServiceRole> getServiceRoles() {
        return serviceRoles.stream()
                .map(IServiceRole.class::cast)
                .collect(Collectors.toList());
    }



    public LocalEntity(){}
    public LocalEntity(String title, IUser owner){
        this.title = title;
        if (owner instanceof UserEntity)
            this.owner = (UserEntity)owner;
        isActive = true;
    }


}
