package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        SlotTypeEntity bar = slotTypedRepository.save(new SlotTypeEntity("bar",local));


        List<DayOfWeek> days = new ArrayList<>(Arrays.asList( DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,
                DayOfWeek.FRIDAY,DayOfWeek.SATURDAY,DayOfWeek.SUNDAY));


        ShiftTemplateEntity shiftTemplate = new ShiftTemplateEntity(local,"Abend",
                LocalTime.of(18,0), LocalTime.of(23,0),
                RecurrenceType.Weekly,LocalDate.of(2020,1,1),null,
                days);

        //List<EmployeeEntity> assigned = new ArrayList<>(Arrays.asList(eCeline));
        //List<EmployeeEntity> applied = new ArrayList<>(Arrays.asList(eMatthias,eMartin));
        SlotEntity slot = new SlotEntity(bar,null,2, new ArrayList<>(), new ArrayList<>());
        slot.addToShiftTemplate(shiftTemplate);

        shiftTemplateRepository.save(shiftTemplate);
        slotRepository.save(slot);
    }


    @Override
    public List<IShift> getShifts(ILocal local, LocalDate from, LocalDate to) {
        return shiftRepository.findAllByLocalAndDayBetween(local,from,to);
    }

    @Override
    public List<IShiftTemplate> getShiftTemplates(ILocal local) {
        return shiftTemplateRepository.findByLocalId(local.getId());
    }

    @Override
    public List<IEmployee> getEmployees(int localId) {
        return null;
    }

    @Override
    public Optional<IShift> getShift(ILocal local, LocalDate day, Optional<IShiftTemplate> shiftTemplate) {
        if (!shiftTemplate.isPresent())
            return Optional.empty();

        return shiftRepository.findFirstByDayIsAndShiftTemplateIsAndLocalIs(day, shiftTemplate.get(), local);
    }

    @Override
    public IShift createShift(IShiftTemplate shiftTemplate, LocalDate day) {
        ShiftEntity shift = shiftRepository.save(new ShiftEntity((ShiftTemplateEntity) shiftTemplate,day)); //todo ??

        List<SlotEntity> templateSlots = shiftTemplate.getSlots().stream().map(SlotEntity.class::cast).collect(Collectors.toList());
        templateSlots.forEach(templateSlot -> new SlotEntity(templateSlot,shift,slotRepository));

        return shift;
    }

    @Override
    public Optional<ISlotType> getSlotType(ILocal local, String title) {
        return local.getSlotTypes()
                .stream()
                .filter(st-> st.getTitle().equals(title))
                .findFirst();
    }

    @Override
    public Optional<ISlot> getSlotForShiftAndType(IShift shift, ISlotType slotType) {
         return shift.getSlots()
                 .stream()
                .filter(st -> st.getSlotType() == slotType )
                .findFirst();
    }



    @Override
    public Optional<IEmployee> getEmployeeForName(ILocal local, String userName) {
        Optional<IUser> user = userRepository.findByNickname(userName);
        if (!user.isPresent())
            return Optional.empty();

        return employeeRepository.findFirstByUserAndLocal(user.get(),local);
    }

    @Override
    public void assignEmployeeToSlot(IEmployee employee, ISlot slot, boolean isAssigned) {
        if (isAssigned)
            slot.assignEmployee(employee);
        else
            slot.unAssignEmployee(employee);
        slot.save(slotRepository);
    }
    @Override
    public void applyEmployeeToSlot(IEmployee employee, ISlot slot, boolean isApplied) {
        if (isApplied)
            slot.applyEmployee(employee);
        else
            slot.unApplyEmployee(employee);
        slot.save(slotRepository);
    }



    @Override
    public Optional<ILocal> getLocalById(long localID) {
        return localRepository
                .findById(localID);
    }

    @Override
    public Optional<IShiftTemplate> getShiftTemplateById(long shiftTemplateId) {
        return shiftTemplateRepository
                .findById(shiftTemplateId);
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
