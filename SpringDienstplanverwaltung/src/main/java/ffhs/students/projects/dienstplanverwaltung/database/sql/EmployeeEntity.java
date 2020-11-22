package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.database.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table
class EmployeeEntity implements IEmployee, ISaveable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean isActive;
    private double hourlyRate;
    private String currency;
    private int monthlyContingent;

    public boolean isActive() { return isActive; }
    public double getHourlyRate() {  return hourlyRate;  }
    public String getCurrency() { return currency;  }
    public int getMonthlyContingent() {  return monthlyContingent; }




    @Override
    public IUser getUser() { return user;  }

    @Override
    public ILocal getLocal() {
        return local;
    }

    @Override
    public boolean isEqual(IEmployee employee) {
        return employee.getUser().getNickname() == user.getNickname();
    }

    //todo - wie kann man den User als zweiten Teil des Keys verwenden?
    //@EmbeddedId
    @ManyToOne
    @JoinColumn()
    private UserEntity user;

    @ManyToOne
    @JoinColumn()
    private LocalEntity local;

    public List<IServiceRole> getServiceRoles() {
        return serviceRoles.stream()
                .map(IServiceRole.class::cast)
                .collect(Collectors.toList());
    }

    @ManyToMany (mappedBy ="employees")
    private List<ServiceRoleEntity> serviceRoles;

    @ManyToMany (mappedBy ="assigned")
    private List<SlotEntity> slotsAssignedTo;

    @ManyToMany (mappedBy ="applied")
    private List<SlotEntity> slotsAppliedTo;

    public EmployeeEntity() { }
    public EmployeeEntity(UserEntity user, LocalEntity local){
        this.user = user;
        this.local = local;
    }

    public long getId() {  return id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
