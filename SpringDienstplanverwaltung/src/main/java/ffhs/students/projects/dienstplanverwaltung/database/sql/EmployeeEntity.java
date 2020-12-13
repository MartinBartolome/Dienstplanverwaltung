package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.administration.ListItem;
import ffhs.students.projects.dienstplanverwaltung.administration.employeesconfig.EmployeeConfig;
import ffhs.students.projects.dienstplanverwaltung.database.*;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.*;
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



    @Override
    public SlotUserInteraction getAllowedSlotInteraction(ISlot slot) {
        boolean isAdmin = this.isAdmin();
        boolean isApplicable = allowsApplicationForSlot(slot);

        if (isAdmin && isApplicable) return SlotUserInteraction.ApplyAndAssign;
        if (isAdmin) return SlotUserInteraction.Assign;
        if (isApplicable) return SlotUserInteraction.Apply;
        return SlotUserInteraction.None;
    }
    private boolean isAdmin(){
        return serviceRoles
                .stream()
                .anyMatch(ServiceRoleEntity::isAdminRole);
    }
    private boolean allowsApplicationForSlot(ISlot slot){
        Set<Long> neededServiceRolesIDs = slot.getServiceRoles().stream().map(IServiceRole::getId).collect(Collectors.toSet());
        Set<Long> employeeServiceRolesIDs = serviceRoles.stream().map(IServiceRole::getId).collect(Collectors.toSet());
        neededServiceRolesIDs.retainAll(employeeServiceRolesIDs);
        return !neededServiceRolesIDs.isEmpty();
    }


    // Aktualisierung
    public void updateWithConfig(EmployeeConfig employeeConfig, EmployeeRepository repo) {
        isActive = employeeConfig.getIsActive();
        currency = employeeConfig.getCurrency();
        hourlyRate = employeeConfig.getHourlyRate();
        monthlyContingent = employeeConfig.getMonthlyContingent();

        Set<Long> newServiceRoleIds = employeeConfig.getServiceRoles()
                .getItems().stream()
                .filter(ListItem::getSelected)
                .map(ListItem::getId)
                .collect(Collectors.toSet());
        updateServiceRoles(newServiceRoleIds,repo);
        save(repo);
    }
    private void updateServiceRoles(Set<Long> newServiceRoles, EmployeeRepository repo){
        Set<Long> employeeRoleIds = getServiceRoles().stream()
                .map(IServiceRole::getId)
                .collect(Collectors.toSet());

        Set<Long> toRemove = new HashSet<>(employeeRoleIds);
        toRemove.removeAll(newServiceRoles);

        Set<Long> toAdd = new HashSet<>(newServiceRoles);
        toAdd.removeAll(employeeRoleIds);

        toRemove.forEach(this::removeServiceRole);
        toAdd.forEach(this::addServiceRole);

        save(repo);
    }
    private void addServiceRole(Long serviceRoleId){
        Optional<ServiceRoleEntity> serviceRole = helperGetLocalServiceRoleForId(serviceRoleId);
        if (!serviceRole.isPresent())
            return;

        serviceRoles.add(serviceRole.get());
        serviceRole.get().addEmployee(this);
    }
    private void removeServiceRole(Long serviceRoleId){
        Optional<ServiceRoleEntity> serviceRole = helperGetLocalServiceRoleForId(serviceRoleId);
        if (!serviceRole.isPresent())
            return;

        serviceRoles.remove(serviceRole.get());
        serviceRole.get().removeEmployee(this);
    }

    private Optional<ServiceRoleEntity> helperGetLocalServiceRoleForId(Long serviceRoleId){
        Optional<IServiceRole> serviceRole =  local
                .getServiceRoles().stream()
                .filter(sr -> sr.getId() == serviceRoleId)
                .findFirst();
        if (!serviceRole.isPresent() || !(serviceRole.get() instanceof ServiceRoleEntity))
            return Optional.empty();

        return Optional.of((ServiceRoleEntity) serviceRole.get());
    }
}
