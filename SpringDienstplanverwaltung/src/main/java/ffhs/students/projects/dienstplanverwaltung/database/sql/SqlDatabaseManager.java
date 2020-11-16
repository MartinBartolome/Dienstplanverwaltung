package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.database.*;
import ffhs.students.projects.dienstplanverwaltung.database.sql.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SqlDatabaseManager implements IDatabaseManager {
    public void createFakeDate(){
        LocalEntity local = localRepository.save(new LocalEntity());

        UserEntity martin = userRepository.save(new UserEntity("Martin"));
        UserEntity celine = userRepository.save(new UserEntity("Celine"));
        UserEntity matthias = userRepository.save(new UserEntity("Matthias"));

        EmployeeEntity eMartin = employeeRepository.save(new EmployeeEntity(martin,local));
        EmployeeEntity eCeline = employeeRepository.save(new EmployeeEntity(celine,local));
        EmployeeEntity eMatthias = employeeRepository.save(new EmployeeEntity(matthias,local));

        SlotTypeEntity bar = slotTypedRepository.save(new SlotTypeEntity("bar"));


        List<DayOfWeek> days = new ArrayList<>(Arrays.asList( DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,
                DayOfWeek.FRIDAY,DayOfWeek.SATURDAY,DayOfWeek.SUNDAY));

        List<EmployeeEntity> assigned = new ArrayList<>(Arrays.asList(eCeline));
        List<EmployeeEntity> applied = new ArrayList<>(Arrays.asList(eMatthias,eMartin));
        ShiftTemplateEntity shiftTemplate = new ShiftTemplateEntity(local,"Abend",
                LocalTime.of(18,0), LocalTime.of(23,0),
                RecurrenceType.Weekly,LocalDate.of(2020,1,1),null,
                days);

        SlotEntity slot = new SlotEntity(bar,null,2,assigned,applied);
        slot.addToShiftTemplate(shiftTemplate);

        shiftTemplateRepository.save(shiftTemplate);
        slotRepository.save(slot);
    }


    @Override
    public List<IShift> getShifts(int localId, LocalDate from, LocalDate to) {
        return null;
    }

    @Override
    public List<IShiftTemplate> getShiftTemplates(int localId) {
        return null;
    }

    @Override
    public List<IEmployee> getEmployees(int localId) {
        return null;
    }

    @Override
    public Optional<IShift> getShift(int localId, LocalDate day, int shiftTemplateId) {
        return Optional.empty();
    }

    @Override
    public Optional<IShift> createShift(int shiftTemplateId, LocalDate day) {
        return Optional.empty();
    }

    @Override
    public Optional<ISlotType> getSlotType(int localId, String title) {
        return Optional.empty();
    }

    @Override
    public Optional<ISlot> getSlotForShiftAndType(IShift shift, ISlotType slotType) {
        return Optional.empty();
    }

    @Override
    public int assignEmployeeToSlot(IEmployee employee, ISlot slot, boolean isAssigned) {
        return 0;
    }

    @Override
    public Optional<IEmployee> getEmployeeForName(int localId, String employeeName) {
        return Optional.empty();
    }

    @Override
    public int applyEmployeeToSlot(IEmployee employee, ISlot slot, boolean isApplied) {
        return 0;
    }

    @Override
    public Optional<IShiftTemplate> getShiftTemplate(int id) {
        return Optional.empty();
    }

    @Autowired
    private LocalRepository localRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private SlotTypeRepository slotTypedRepository;
    @Autowired
    private SlotRepository slotRepository;
    @Autowired
    private ShiftTemplateRepository shiftTemplateRepository;
    @Autowired
    private ShiftRepository shiftRepository;
}
