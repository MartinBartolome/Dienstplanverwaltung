package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.Helper;
import ffhs.students.projects.dienstplanverwaltung.administration.ListItem;
import ffhs.students.projects.dienstplanverwaltung.administration.shiftconfig.ShiftTemplateConfig;
import ffhs.students.projects.dienstplanverwaltung.administration.shiftconfig.SlotConfig;
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


        UserEntity martin = userRepository.save(new UserEntity("Martin"));
        UserEntity celine = userRepository.save(new UserEntity("Celine"));
        UserEntity matthias = userRepository.save(new UserEntity("Matthias"));

        LocalEntity local = localRepository.save(new LocalEntity("City Cafe",martin));
        LocalEntity local2 = localRepository.save(new LocalEntity("Staubsauger e.V.",matthias));

        IServiceRole serviceRole1 = serviceRoleRepository.save(new ServiceRoleEntity("Barkeeper",local,true));
        IServiceRole serviceRole2 = serviceRoleRepository.save(new ServiceRoleEntity("Kellner",local,true));


        EmployeeEntity eMartin = employeeRepository.save(new EmployeeEntity(martin,local));
        EmployeeEntity eCeline = employeeRepository.save(new EmployeeEntity(celine,local));
        EmployeeEntity eMatthias = employeeRepository.save(new EmployeeEntity(matthias,local));
        EmployeeEntity eMatthias2 = employeeRepository.save(new EmployeeEntity(matthias,local2));

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


    public void addServiceRole(long localId, String title){
        Optional<ILocal> local = localRepository.findById(localId);
        if (!local.isPresent())
            return;

        Optional<IServiceRole> serviceRole = serviceRoleRepository.findFirstByLocalAndName(local.get(),title);
        if (serviceRole.isPresent())
            return;

        serviceRoleRepository.save(new ServiceRoleEntity(title,(LocalEntity)local.get(),true));
    }

    public Optional<IServiceRole> updateServiceRole(long serviceRoleId, String title, boolean isActive ){
        Optional<IServiceRole> serviceRole = serviceRoleRepository.findById(serviceRoleId);
        if (!serviceRole.isPresent())
            return Optional.empty();

        ((ServiceRoleEntity)serviceRole.get()).setName(title);
        ((ServiceRoleEntity)serviceRole.get()).setActive(isActive);
        ((ServiceRoleEntity)serviceRole.get()).save(serviceRoleRepository);
        return serviceRole;
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

    public List<ILocal> getLocalsForUser(IUser user){
        if (!(user instanceof UserEntity))
            return new ArrayList<>();

        return ((UserEntity)user).getEmployees().stream()
                .map(EmployeeEntity::getLocal)
                .collect(Collectors.toList());
    }

    public List<ILocal> getOwnedLocalsForUser(IUser user){
        if (!(user instanceof UserEntity))
            return new ArrayList<>();

        return localRepository.findAllByOwner(user);
    }

    public ILocal requestNewLocal(IUser user,String localName){
        Optional<ILocal> localWithName = getOwnedLocalsForUser(user).stream().filter(loc -> loc.getTitle().equals(localName)).findFirst();
        if (localWithName.isPresent())
            return localWithName.get();

        LocalEntity local = new LocalEntity(localName,user);
        local.setGranted(false);
        local.save(localRepository);
        return local;
    }

    public Optional<ILocal> updateLocal(long localId, String title, boolean isActive){
        Optional<ILocal> local = localRepository.findById(localId);
        if (!local.isPresent())
            return Optional.empty();

        ((LocalEntity)local.get()).setTitle(title);
        ((LocalEntity)local.get()).setActive(isActive);
        ((LocalEntity)local.get()).save(localRepository);
        return local;
    }
    public List<ILocal> getAllLocals(){
        return localRepository.findAll().stream()
                .map(ILocal.class::cast)
                .collect(Collectors.toList());
    }
    public Optional<ILocal> localSetState(long localId, boolean isGranted, boolean isActive){
        Optional<ILocal> local = localRepository.findById(localId);
        if (!local.isPresent())
            return Optional.empty();

        ((LocalEntity)local.get()).setGranted(isGranted);
        ((LocalEntity)local.get()).setActive(isActive);
        ((LocalEntity)local.get()).save(localRepository);
        return local;
    }
    public Optional<IUser> getUser(String nickName){
        return userRepository.findByNickname(nickName);
    }

    public Optional<IShiftTemplate> createOrUpdateShiftTemplate(ILocal local,ShiftTemplateConfig shiftTemplateConfig){
        Optional<IShiftTemplate> shiftTemplate;
        if (shiftTemplateConfig.getId() == -1) //create
            shiftTemplate = Optional.of(shiftTemplateRepository.save(new ShiftTemplateEntity(local)));
        else // update
            shiftTemplate = shiftTemplateRepository.findById( shiftTemplateConfig.getId() );
        if (!shiftTemplate.isPresent())
            return Optional.empty();

        //Daten für Aktualisierung aufbauen/transformieren
        String recurrenceString = shiftTemplateConfig.getRecurrenceOptions().getSelectedItem().getTitle();
        List<String> weekDaysStrings = shiftTemplateConfig.getDays().getItems().stream()
                .filter(ListItem::getSelected)
                .map(ListItem::getTitle)
                .collect(Collectors.toList());
        List<DayOfWeek> weekDays = weekDaysStrings.stream().map(Helper::getWeekDay).collect(Collectors.toList());
        List<SlotEntity> newSlots = shiftTemplateConfig.getSlotInfos().stream()
                .map(this::getForSlotInfo)
                .collect(Collectors.toList());

        //Daten aktualisieren
        ShiftTemplateEntity shiftTemplateEntity = ((ShiftTemplateEntity)shiftTemplate.get());
        shiftTemplateEntity.setFromDate(Helper.dateFromString(shiftTemplateConfig.getFromDate()));
        shiftTemplateEntity.setToDate(Helper.dateFromString(shiftTemplateConfig.getToDate()));
        shiftTemplateEntity.setRecurrenceType(Helper.getRecurrenceType(recurrenceString));
        shiftTemplateEntity.setWeekdays(weekDays);
        shiftTemplateEntity.updateSlots(newSlots,slotRepository);
        shiftTemplateEntity.setTitle(shiftTemplate.get().getTitle());
        shiftTemplateEntity.setToTime(Helper.timeFromString(shiftTemplateConfig.getEndTime()));
        shiftTemplateEntity.setFromTime(Helper.timeFromString(shiftTemplateConfig.getStartTime()));
        shiftTemplateEntity.save(shiftTemplateRepository);
        return Optional.of(shiftTemplateEntity);
    }

    private SlotEntity getForSlotInfo(SlotConfig info){
        long id = info.getId();
        int numberOfEmployeesNeeded = info.getNumberOfEmployeesNeeded();
        SlotTypeEntity slotType = slotTypedRepository.findByTitle(info.getSlotType()).orElse(null);
        List<ServiceRoleEntity> slectedServiceRoles = info.getServiceRoleTable().getItems().stream()
                .filter(ListItem::getSelected)
                .map(ListItem::getId)
                .map(srId -> serviceRoleRepository.findById(srId))
                .flatMap(Optional::stream)
                .map(ServiceRoleEntity.class::cast)
                .collect(Collectors.toList());
        return new SlotEntity(id,slectedServiceRoles,slotType,numberOfEmployeesNeeded);
    }


    @Autowired
    private ServiceRoleRepository serviceRoleRepository;
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
