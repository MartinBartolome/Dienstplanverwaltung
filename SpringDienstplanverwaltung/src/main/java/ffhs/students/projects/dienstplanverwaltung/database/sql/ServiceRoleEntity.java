package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.database.ISaveable;
import ffhs.students.projects.dienstplanverwaltung.database.IServiceRole;
import ffhs.students.projects.dienstplanverwaltung.database.ISlot;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table
public class ServiceRoleEntity implements IServiceRole, ISaveable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public void setName(String name) {
        if (!name.isEmpty())
            this.name = name;
    }

    private boolean isAdminRole;
    public boolean isAdminRole() {  return isAdminRole;  }

    @ManyToMany()
    private List<SlotEntity> slots;

    @ManyToMany()
    private List<EmployeeEntity> employees;

    public void setActive(boolean active) { isActive = active; }

    private String name;
    private boolean isActive;
    @ManyToOne
    @JoinColumn()
    private LocalEntity local;

    public ServiceRoleEntity(String name,LocalEntity local, boolean isActive) {
        this.name = name;
        this.local = local;
        this.isActive = isActive;
        local.addServiceRole(this);
    }

    public static ServiceRoleEntity createManagerRole(LocalEntity local){
        ServiceRoleEntity managerRole = new ServiceRoleEntity();
        managerRole.name = "Manager";
        managerRole.local = local;
        managerRole.isActive = true;
        managerRole.isAdminRole = true;
        local.addServiceRole(managerRole);
        return managerRole;
    }

    public ServiceRoleEntity() {  }

    public String getName() { return name;  }
    public boolean isActive() {  return isActive; }
    public LocalEntity getLocal() {  return local; }
    public long getId() {  return id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceRoleEntity that = (ServiceRoleEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void addSlot(SlotEntity slot){
        slots.add(slot);
    }
    public void removeSlot(SlotEntity slot){
        slots.remove(slot);
    }

    public void addEmployee(EmployeeEntity employee) {
        employees.add(employee);
    }

    public void removeEmployee(EmployeeEntity employeeEntity) {
        employees.remove(employeeEntity);
    }
}
