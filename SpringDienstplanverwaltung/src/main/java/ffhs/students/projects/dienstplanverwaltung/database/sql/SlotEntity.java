package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.database.*;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table
public class SlotEntity implements ISlot, ISlotDisplay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Override
    public long getSlotId() { return id;  }

    @ManyToMany(cascade = CascadeType.DETACH)
    private List<EmployeeEntity> assigned;

    @ManyToMany(cascade = CascadeType.DETACH)
    private List<EmployeeEntity> applied;

    @ManyToOne
    @JoinColumn()
    private SlotTypeEntity slotType;

    @ManyToOne
    @JoinColumn()
    private ShiftEntity shift;

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


    private int numberOfEmployeesNeeded;
    @Override
    public int getNumberOfEmployeesNeeded() {  return numberOfEmployeesNeeded; }

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

    public void addToShiftTemplate(ShiftTemplateEntity shiftTemplate){
        this.shiftTemplate = shiftTemplate;
    }
}
