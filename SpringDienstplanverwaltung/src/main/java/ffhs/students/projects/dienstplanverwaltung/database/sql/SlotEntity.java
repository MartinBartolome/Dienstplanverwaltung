package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.Helper;
import ffhs.students.projects.dienstplanverwaltung.administration.shiftconfig.SlotData;
import ffhs.students.projects.dienstplanverwaltung.database.*;
import ffhs.students.projects.dienstplanverwaltung.shiftplan.ISlotDisplay;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
@Table
public
class SlotEntity implements ISlot, ISlotDisplay,IDeleteable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;



    @Override
    public long getSlotId() { return id;  }

    @ManyToMany(cascade = CascadeType.DETACH)
    private List<EmployeeEntity> assigned;

    @ManyToMany(cascade = CascadeType.DETACH)
    private List<EmployeeEntity> applied;

    @ManyToMany(mappedBy ="slots")
    private List<ServiceRoleEntity> serviceRoles;

    @ManyToOne
    @JoinColumn()
    private SlotTypeEntity slotType;

    @ManyToOne
    @JoinColumn()
    private ShiftEntity shift;

    public void setShiftTemplate(ShiftTemplateEntity shiftTemplate) {
        this.shiftTemplate = shiftTemplate;
    }

    @ManyToOne
    @JoinColumn()
    private ShiftTemplateEntity shiftTemplate;

    @Override
    public IShift getShift() {
        return shift;
    }



    @Override
    public List<IEmployee> getAssigned() {
        return assigned.stream()
                .map(IEmployee.class::cast)
                .collect(Collectors.toList());
    }

    @Override
    public List<IEmployee> getApplied() {
        return applied.stream()
                .map(IEmployee.class::cast)
                .collect(Collectors.toList());
    }

    public void update(SlotEntity newSlot,SlotRepository repo){
        slotType = newSlot.slotType;
        numberOfEmployeesNeeded = newSlot.numberOfEmployeesNeeded;
        serviceRoles = newSlot.serviceRoles;
        save(repo);
    }

    private int numberOfEmployeesNeeded;
    @Override
    public int getNumberOfEmployeesNeeded() {  return numberOfEmployeesNeeded; }

    @Override
    public void applyEmployee(IEmployee employee) {
        if (applied.stream().anyMatch(empl -> empl.isEqual(employee)))
            return;

        applied.add((EmployeeEntity)employee); //todo?
    }
    public void assignEmployee(IEmployee employee) {
        if (numberOfEmployeesNeeded <= assigned.size())
            return;

        if (assigned.stream().anyMatch(empl -> empl.isEqual(employee)))
            return;

        assigned.add((EmployeeEntity)employee); //todo?
    }

    @Override
    public void unApplyEmployee(IEmployee employee) {
        applied.remove(employee);
    }

    @Override
    public void unAssignEmployee(IEmployee employee) {
        assigned.remove(employee);
    }


    @Override
    public ISlotType getSlotType() { return slotType; }

    public SlotEntity(){}
    public SlotEntity(SlotTypeEntity slotType,ShiftEntity shift,int numberOfEmployeesNeeded,List<EmployeeEntity> assigned, List<EmployeeEntity> applied){
        this.slotType = slotType;
        this.shift = shift;
        this.numberOfEmployeesNeeded = numberOfEmployeesNeeded;
        this.applied = applied;
        this.assigned = assigned;
    }
    public SlotEntity(SlotEntity slotTemplate,ShiftEntity shift,SlotRepository repo){
        this.slotType = slotTemplate.slotType;
        this.numberOfEmployeesNeeded = slotTemplate.numberOfEmployeesNeeded;
        this.shift = shift;
        this.save(repo);
    }
    public void addToShiftTemplate(ShiftTemplateEntity shiftTemplate){
        this.shiftTemplate = shiftTemplate;
    }
    public List<IServiceRole> getServiceRoles() {
        return serviceRoles.stream()
                .map(IServiceRole.class::cast)
                .collect(Collectors.toList());
    }
    public SlotEntity(long id, List<ServiceRoleEntity> serviceRoles, SlotTypeEntity slotType, int numberOfEmployeesNeeded) {
        this.id = id;
        this.serviceRoles = serviceRoles;
        this.slotType = slotType;
        this.numberOfEmployeesNeeded = numberOfEmployeesNeeded;
    }
}
