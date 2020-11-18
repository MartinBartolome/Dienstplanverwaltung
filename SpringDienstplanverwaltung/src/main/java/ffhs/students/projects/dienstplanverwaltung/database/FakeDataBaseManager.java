package ffhs.students.projects.dienstplanverwaltung.database;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FakeDataBaseManager implements IDatabaseManager {
    private List<IShift> shifts;
    @Override
    public List<IShift> getShifts(int localId, LocalDate from, LocalDate to) {
        // todo
        return shifts;
    }

    private List<IShiftTemplate> shiftTemplates;
    @Override
    public List<IShiftTemplate> getShiftTemplates(int localId) {
        return shiftTemplates.stream()
                //.filter(e -> e.getLocal() == localId)
                .collect(Collectors.toList());
    }

    private List<IEmployee> employees;
    @Override
    public List<IEmployee> getEmployees(int localId) {
        return employees.stream()
                .filter(e -> e.getLocal().getId() == localId)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<IShift> getShift(int localId, LocalDate day, int shiftTemplateId) {
        return shifts.stream()
                //.filter(s -> s.getShiftTemplate() == shiftTemplateId && s.getDay().equals(day))
                .findAny();
    }

    @Override
    public Optional<IShift> createShift(int shiftTemplateId, LocalDate day) {
        Optional<IShiftTemplate> template = getShiftTemplate(shiftTemplateId);
        if (!template.isPresent())
            return Optional.empty();

        int shiftId = shifts.size();
        //List<ISlot> slots = createSlotsFromTemplate(template.get(),shiftId);
        IShift shift = new Shift(template.get(),day,slots, shiftId);
        shifts.add(shift);
        return Optional.of(shift);
    }





    private List<ISlotType> slotTypes;
    @Override
    public Optional<ISlotType> getSlotType(int localId, String title) {
        return slotTypes.stream()
                .filter(st -> st.getTitle().equals(title))
                .findAny();
    }

    private List<ISlot> slots;
    @Override
    public Optional<ISlot> getSlotForShiftAndType(IShift shift, ISlotType slotType) {
        return slots.stream()
                .filter(slot -> slot.getShift().getId() == shift.getId() && slot.getSlotType() == slotType)
                .findAny();
    }
    private List<ISlot> createSlotsFromTemplate(IShiftTemplate template,IShift shift){
        return template.getSlots().stream()
                .map((ISlot slot) -> createSlotForTemplateSlot(slot,shift))
                .collect(Collectors.toList());
    }
    private ISlot createSlotForTemplateSlot(ISlot slot,IShift shift){
        int id = slots.size();
        ISlot newSlot = new Slot(id,shift,slot.getAssigned(),slot.getApplied(),slot.getSlotType(),slot.getNumberOfEmployeesNeeded());
        slots.add(newSlot);
        return newSlot;
    }


    @Override
    public int assignEmployeeToSlot(IEmployee employee, ISlot slot, boolean isAssigned) {
        slot.getAssigned().add(employee);
        return 0;
    }

    @Override
    public Optional<IEmployee> getEmployeeForName(int localId, String employeeName) {
        return employees.stream()
                .filter(e -> e.getUser().getEmail().equals(employeeName))
                .findAny();
    }

    @Override
    public int applyEmployeeToSlot(IEmployee employee, ISlot slot, boolean isApplied) {
        slot.getApplied().add(employee);
        return 0;
    }

    @Override
    public Optional<IShiftTemplate> getShiftTemplate(int id) {
        return shiftTemplates.stream().filter(st -> st.getId() == id).findFirst();
    }


    public FakeDataBaseManager(){
    }
}
