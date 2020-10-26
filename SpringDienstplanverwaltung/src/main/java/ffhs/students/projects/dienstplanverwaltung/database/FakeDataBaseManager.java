package ffhs.students.projects.dienstplanverwaltung.database;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FakeDataBaseManager implements IDatabaseManager {
    @Override
    public List<IShift> getShifts(int localId, LocalDate from, LocalDate to) {
        // todo
        return new ArrayList<>();
    }

    @Override
    public List<IShiftTemplate> getShiftTemplates(int localId) {
        //todo
        User martin = new User("Martin");
        User matthias = new User("Matthias");
        User celine = new User("Celine");
        Employee eMartin = new Employee(martin);
        Employee eMatthias = new Employee(matthias);
        Employee eCeline = new Employee(celine);

        List<IShiftTemplate> result = new ArrayList<>();
        List<IEmployee> assigned = new ArrayList<>(Arrays.asList(eCeline));
        List<IEmployee> applied = new ArrayList<>(Arrays.asList(eMatthias,eMartin));
        Slot slot = new Slot(0,assigned,applied, new SlotType("Bar"));
        List<ISlot> slots = new ArrayList<>(Arrays.asList(slot));
        ShiftTemplate template1 = new ShiftTemplate(0,"Abend", RecurrenceType.Weekly,LocalDate.of(2020,1,1),null,
                new ArrayList<>(Arrays.asList(
                        DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY,DayOfWeek.SATURDAY,DayOfWeek.SUNDAY))
                ,slots);
        result.add(template1);
        return result;
    }
}
