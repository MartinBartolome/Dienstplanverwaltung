package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.administration.basecomponents.ListItem;
import ffhs.students.projects.dienstplanverwaltung.administration.employeesconfig.EmployeeConfig;
import ffhs.students.projects.dienstplanverwaltung.database.*;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table
class EmployeeEntity implements IEmployee, ISaveable {
    // member
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean isActive;
    private double hourlyRate;
    private String currency;
    private int monthlyContingent;
    @ManyToOne
    @JoinColumn()
    private UserEntity user;
    @ManyToOne
    @JoinColumn()
    private LocalEntity local;
    @ManyToMany (mappedBy ="employees")
    private List<ServiceRoleEntity> serviceRoles;
    @ManyToMany (mappedBy ="assigned")
    private List<SlotEntity> slotsAssignedTo;
    @ManyToMany (mappedBy ="applied")
    private List<SlotEntity> slotsAppliedTo;


    // getter
    public long getId() {  return id; }
    public boolean isActive() { return isActive; }
    public double getHourlyRate() {  return hourlyRate;  }
    public String getCurrency() { return currency;  }
    public int getMonthlyContingent() {  return monthlyContingent; }
    @Override public IUser getUser() { return user;  }
    @Override public ILocal getLocal() { return local; }
    @Override public boolean isEqual(IEmployee employee) { return employee.getUser().getNickname().equals(user.getNickname());  }
    public List<IServiceRole> getServiceRoles() {
        return serviceRoles.stream()
                .map(IServiceRole.class::cast)
                .collect(Collectors.toList());
    }
    @Override public boolean isAdmin(){ return serviceRoles.stream().anyMatch(ServiceRoleEntity::isAdminRole); }
    @Override public SlotUserInteraction getAllowedSlotInteraction(ISlot slot) {
        if (slot.getShift() != null && slot.getShift().getIsCanceled())
            return SlotUserInteraction.None;
        
        boolean isAdmin = this.isAdmin();
        boolean isApplicable = allowsApplicationForSlot(slot);
        if (isAdmin && isApplicable) return SlotUserInteraction.ApplyAndAssign;
        if (isAdmin) return SlotUserInteraction.Assign;
        if (isApplicable) return SlotUserInteraction.Apply;
        return SlotUserInteraction.None;
    }
    private boolean allowsApplicationForSlot(ISlot slot){
        Set<Long> neededServiceRolesIDs = slot.getServiceRoles().stream().map(IServiceRole::getId).collect(Collectors.toSet());
        Set<Long> employeeServiceRolesIDs = serviceRoles.stream().map(IServiceRole::getId).collect(Collectors.toSet());
        neededServiceRolesIDs.retainAll(employeeServiceRolesIDs);
        return !neededServiceRolesIDs.isEmpty();
    }

    //Setter


    // Konstruktoren
    public EmployeeEntity() { }
    public EmployeeEntity(UserEntity user, LocalEntity local){
        this.user = user;
        this.local = local;
        this.serviceRoles = new ArrayList<>();
        this.slotsAssignedTo = new ArrayList<>();
        this.slotsAppliedTo = new ArrayList<>();
        local.addEmployee(this);
    }
    public static Optional<IEmployee> createManagerForLocal(IUser user, ILocal local){
        if (!(user instanceof  UserEntity) ||  !(local instanceof LocalEntity))
            return Optional.empty();

        Optional<IServiceRole> adminRole =  local.getAdminRole();
        if (!adminRole.isPresent())
            return Optional.empty();

        Optional<IEmployee> employeeForUser = local.getEmployees().stream()
                .filter(employee -> employee.getUser().getNickname().equals(user.getNickname()))
                .findFirst();

        if (employeeForUser.isPresent() && (employeeForUser.get() instanceof EmployeeEntity) ){
            ((EmployeeEntity) employeeForUser.get()).addServiceRole(adminRole.get().getId());
            return employeeForUser;
        }

        EmployeeEntity newManager = new EmployeeEntity((UserEntity) user,(LocalEntity) local);
        newManager.addServiceRole(adminRole.get().getId());
        return Optional.of(newManager);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return id == that.id;
    }
    @Override
    public int hashCode() {  return Objects.hash(id); }
}
