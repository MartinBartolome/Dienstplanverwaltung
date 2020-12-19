package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.database.*;
import ffhs.students.projects.dienstplanverwaltung.shiftplan.ISlotDisplay;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table
class SlotEntity implements ISlot, ISlotDisplay,IDeleteable, ISaveable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToMany(cascade = CascadeType.DETACH)
    private List<EmployeeEntity> assigned;
    @ManyToMany(cascade = CascadeType.DETACH)
    private List<EmployeeEntity> applied;
    @ManyToMany(mappedBy ="slots")
    private List<ServiceRoleEntity> serviceRoles;
    private long templateSlotId;
    @ManyToOne
    @JoinColumn()
    private ShiftEntity shift;
    @ManyToOne
    @JoinColumn()
    private ShiftTemplateEntity shiftTemplate;
    private String title;
    private int numberOfEmployeesNeeded;

    //Getter
    public long getTemplateSlotId() { return templateSlotId;  }
    @Override public long getSlotId() { return id;  }
    @Override public IShift getShift() {
        return shift;
    }
    @Override public String getTitle() { return title; }
    @Override public int getNumberOfEmployeesNeeded() {  return numberOfEmployeesNeeded; }
    @Override public List<IEmployee> getAssigned() {
        return assigned.stream()
                .map(IEmployee.class::cast)
                .collect(Collectors.toList());
    }
    @Override public List<IEmployee> getApplied() {
        return applied.stream()
                .map(IEmployee.class::cast)
                .collect(Collectors.toList());
    }
    public List<IServiceRole> getServiceRoles() {
        return serviceRoles.stream()
                .map(IServiceRole.class::cast)
                .collect(Collectors.toList());
    }

    // Setter
    public void setShiftTemplate(ShiftTemplateEntity shiftTemplate) {
        this.shiftTemplate = shiftTemplate;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    // Konstruktoren
    public SlotEntity(){}
    public SlotEntity(ShiftEntity shift,int numberOfEmployeesNeeded,List<EmployeeEntity> assigned, List<EmployeeEntity> applied){
        this.shift = shift;
        this.numberOfEmployeesNeeded = numberOfEmployeesNeeded;
        this.applied = applied;
        this.assigned = assigned;
    }
    public SlotEntity(SlotEntity slotTemplate,ShiftEntity shift,SlotRepository repo){
        this.numberOfEmployeesNeeded = slotTemplate.numberOfEmployeesNeeded;
        this.shift = shift;
        this.templateSlotId = slotTemplate.id;
        this.title = slotTemplate.title;
        shift.addSlot(this);
        assigned = new ArrayList<>();
        applied = new ArrayList<>();
        serviceRoles = new ArrayList<>();
        slotTemplate.serviceRoles.forEach(this::addServiceRole);
        this.save(repo);
    }
    public SlotEntity(long id, List<ServiceRoleEntity> serviceRoles,  int numberOfEmployeesNeeded,String title) {
        this.id = id;
        this.serviceRoles = serviceRoles;
        this.numberOfEmployeesNeeded = numberOfEmployeesNeeded;
        this.title = title;
    }

    // Aktualisierungen
    public void update(SlotEntity newSlot,SlotRepository repo){
        title = newSlot.title;
        numberOfEmployeesNeeded = newSlot.numberOfEmployeesNeeded;
        updateServiceRoles(newSlot.serviceRoles);
        save(repo);
    }
    private void updateServiceRoles(List<ServiceRoleEntity> newServiceRoles){
        List<ServiceRoleEntity> toRemove = this.serviceRoles.stream()
                .filter(sr -> !newServiceRoles.contains(sr)).collect(Collectors.toList());

        List<ServiceRoleEntity> toAdd = newServiceRoles.stream()
                .filter(nsr -> !serviceRoles.contains(nsr))
                .collect(Collectors.toList());

        toRemove.forEach(this::removeServiceRole);
        toAdd.forEach(this::addServiceRole);
    }
    private void addServiceRole(ServiceRoleEntity serviceRole){
        if (serviceRoles.stream().anyMatch(sr -> sr.getId() == serviceRole.getId()))
            return;
        serviceRole.addSlot(this);
        serviceRoles.add(serviceRole);
    }
    private void removeServiceRole(ServiceRoleEntity serviceRole){
        if (serviceRoles.stream().anyMatch(sr -> sr.getId() == serviceRole.getId())){
            serviceRole.removeSlot(this);
            serviceRoles.remove(serviceRole);
        }
    }
    @Override public void applyEmployee(IEmployee employee) {
        if (applied.stream().anyMatch(empl -> empl.isEqual(employee)))
            return;
        applied.add((EmployeeEntity)employee); //todo?
    }
    @Override public void assignEmployee(IEmployee employee) {
        if (numberOfEmployeesNeeded <= assigned.size())
            return;
        if (assigned.stream().anyMatch(empl -> empl.isEqual(employee)))
            return;
        assigned.add((EmployeeEntity)employee); //todo?
    }
    @Override public void unApplyEmployee(IEmployee employee) {
        applied.remove(employee);
    }
    @Override public void unAssignEmployee(IEmployee employee) {
        assigned.remove(employee);
    }
    public void addToShiftTemplate(ShiftTemplateEntity shiftTemplate){
        this.shiftTemplate = shiftTemplate;
    }

    //Helper
    public void getCopyForTemplate(ShiftTemplateEntity template, SlotRepository repo){
        SlotEntity copy = new SlotEntity();
        copy.title = this.title;
        copy.shiftTemplate = template;
        copy.numberOfEmployeesNeeded = this.numberOfEmployeesNeeded;
        template.addSlot(copy);
        copy.assigned = new ArrayList<>();
        copy.applied = new ArrayList<>();
        copy.serviceRoles = new ArrayList<>();
        copy.save(repo);
    }
}
