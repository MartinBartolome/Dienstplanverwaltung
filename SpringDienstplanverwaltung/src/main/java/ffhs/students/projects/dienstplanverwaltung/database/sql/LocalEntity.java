package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.database.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table
class LocalEntity implements ILocal, ISaveable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private boolean isActive;
    private boolean isGranted;
    @ManyToOne
    @JoinColumn()
    private UserEntity owner;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "local")
    private List<ServiceRoleEntity> serviceRoles;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "local")
    private List<EmployeeEntity> employees;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "local")
    private List<ShiftTemplateEntity> shiftTemplates;
    @OneToMany(mappedBy = "local")
    private List<ShiftEntity> shifts;


    //Getter
    @Override public long getId() { return id; }
    public String getTitle() { return title; }
    public boolean isActive() { return isActive;  }
    public boolean isGranted() {  return isGranted;  }
    public UserEntity getOwner() {  return owner; }
    public List<IEmployee> getEmployees() {
        return employees.stream()
                .map(IEmployee.class::cast)
                .collect(Collectors.toList());
    }
    public List<IShiftTemplate> getShiftTemplates() {
        return shiftTemplates.stream()
                .map(IShiftTemplate.class::cast)
                .collect(Collectors.toList());
    }
    public List<IServiceRole> getServiceRoles() {
        if (serviceRoles == null)
            return new ArrayList<>();
        return serviceRoles.stream()
                .map(IServiceRole.class::cast)
                .collect(Collectors.toList());
    }

    // Setter
    public void setTitle(String title) {
        if (!title.isEmpty())
            this.title = title;
    }
    public void setActive(boolean active) { isActive = active; }
    public void setGranted(boolean granted) {  isGranted = granted;  }

    //Konstruktoren
    public LocalEntity(){}
    public LocalEntity(String title, IUser owner){
        this.title = title;
        if (owner instanceof UserEntity)
            this.owner = (UserEntity)owner;
        isActive = true;
        serviceRoles = new ArrayList<>();
        employees = new ArrayList<>();
        shifts = new ArrayList<>();
        shiftTemplates = new ArrayList<>();
    }

    // Aktualisierungen
    public void addServiceRole(ServiceRoleEntity serviceRole){
        if (serviceRoles == null)
            serviceRoles = new ArrayList<>();
        serviceRoles.add(serviceRole);
    }
    public void addEmployee(EmployeeEntity employee){
        employees.add(employee);
    }

    // Unittests
    //public void setOwner(UserEntity owner){ this.owner = owner; }
    /*
    public void initLists(){
        serviceRoles = new ArrayList<>();
        employees = new ArrayList<>();
        shifts = new ArrayList<>();
        shiftTemplates = new ArrayList<>();
    }
    */

    private boolean isUnittest;
    public static LocalEntity createUnittestLocal(IUser owner){
        String localTilte = "UnitTestLokal";

        LocalEntity testLocal = new LocalEntity();
        testLocal.title = localTilte;
        if (owner instanceof UserEntity)
            testLocal.owner = (UserEntity)owner;

        testLocal.isActive = true;
        testLocal.serviceRoles = new ArrayList<>();
        testLocal.employees = new ArrayList<>();
        testLocal.shifts = new ArrayList<>();
        testLocal.shiftTemplates = new ArrayList<>();
        testLocal.isUnittest = true;
        return testLocal;
    }
}
