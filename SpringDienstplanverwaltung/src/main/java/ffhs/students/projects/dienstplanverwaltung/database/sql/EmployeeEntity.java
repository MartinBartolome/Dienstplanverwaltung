package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.database.IEmployee;
import ffhs.students.projects.dienstplanverwaltung.database.ILocal;
import ffhs.students.projects.dienstplanverwaltung.database.IUser;
import ffhs.students.projects.dienstplanverwaltung.database.Local;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class EmployeeEntity implements IEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Override
    public IUser getUser() { return user;  }

    @Override
    public ILocal getLocal() {
        return null;
    }

    //todo - wie kann man den User als zweiten Teil des Keys verwenden?
    //@EmbeddedId
    @ManyToOne
    @JoinColumn()
    private UserEntity user;

    @ManyToOne
    @JoinColumn()
    private LocalEntity local;

    @ManyToMany (mappedBy ="assigned")
    private List<SlotEntity> slotsAssignedTo;

    @ManyToMany (mappedBy ="applied")
    private List<SlotEntity> slotsAppliedTo;

    public EmployeeEntity() { }
    public EmployeeEntity(UserEntity user, LocalEntity local){
        this.user = user;
        this.local = local;
    }

    public long getId() {
        return id;
    }
}
