package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.Helper;
import ffhs.students.projects.dienstplanverwaltung.administration.basecomponents.ListItem;
import ffhs.students.projects.dienstplanverwaltung.administration.employeesconfig.EmployeeConfig;
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
    @Override public List<IShift> getShifts(ILocal local, LocalDate from, LocalDate to) {
        return shiftRepository.findAllByLocalAndDayBetween(local,from,to);
    }
    @Override public List<IShiftTemplate> getShiftTemplates(ILocal local) {
        return shiftTemplateRepository.findByLocalId(local.getId());
    }
    @Override public Optional<IShift> getShift(ILocal local, LocalDate day, IShiftTemplate shiftTemplate) {
        return shiftRepository.findFirstByDayIsAndShiftTemplateIsAndLocalIs(day, shiftTemplate, local);
    }
    @Override  public IShift createShift(IShiftTemplate shiftTemplate, LocalDate day) {
        ShiftEntity shift = shiftRepository.save(new ShiftEntity((ShiftTemplateEntity) shiftTemplate,day)); //todo ??
        List<SlotEntity> templateSlots = shiftTemplate.getSlots().stream().map(SlotEntity.class::cast).collect(Collectors.toList());
        templateSlots.forEach(templateSlot -> new SlotEntity(templateSlot,shift,slotRepository));
        return shift;
    }

    @Override public Optional<IEmployee> getEmployeeForName(ILocal local, String userName) {
        Optional<IUser> user = userRepository.findByNickname(userName);
        if (!user.isPresent())
            return Optional.empty();

        return employeeRepository.findFirstByUserAndLocal(user.get(),local);
    }
    @Override public void assignEmployeeToSlot(IEmployee employee, ISlot slot, boolean isAssigned) {
        if (!(slot instanceof SlotEntity))
            return;

        if (isAssigned)
            slot.assignEmployee(employee);
        else
            slot.unAssignEmployee(employee);

        ((SlotEntity)slot).save(slotRepository);
    }
    @Override public void applyEmployeeToSlot(IEmployee employee, ISlot slot, boolean isApplied) {
        if (!(slot instanceof SlotEntity))
            return;

        if (isApplied)
            slot.applyEmployee(employee);
        else
            slot.unApplyEmployee(employee);
        ((SlotEntity)slot).save(slotRepository);
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

    public List<ILocal> getGrantedLocalsForUser(IUser user){
        if (!(user instanceof UserEntity))
            return new ArrayList<>();

        return ((UserEntity)user).getEmployees().stream()
                .map(EmployeeEntity::getLocal)
                .filter(ILocal::isGranted)
                .collect(Collectors.toList());
    }

    public List<ILocal> getOwnedLocalsForUser(IUser user){
        if (!(user instanceof UserEntity))
            return new ArrayList<>();

        return localRepository.findAllByOwner(user);
    }

    public void requestNewLocal(IUser user, String localName){
        Optional<ILocal> localWithName = getOwnedLocalsForUser(user).stream().filter(loc -> loc.getTitle().equals(localName)).findFirst();
        if (localWithName.isPresent())
            return;

        LocalEntity local = new LocalEntity(localName,user);
        local.setGranted(false);
        local.save(localRepository);
        createManagerRole(local.getId());
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
    public void localSetState(long localId, boolean isActive){
        Optional<ILocal> local = localRepository.findById(localId);
        if (!local.isPresent())
            return;

        ((LocalEntity)local.get()).setActive(isActive);
        ((LocalEntity)local.get()).save(localRepository);
    }
    public void grantLocal(long localId){
        Optional<ILocal> local = localRepository.findById(localId);
        if (!local.isPresent())
            return;

        IUser owner = local.get().getOwner();
        Optional<EmployeeEntity> manager = EmployeeEntity.createManagerForLocal(owner,local.get());
        if (!manager.isPresent())
            return;

        manager.get().save(employeeRepository);
        ((LocalEntity)local.get()).setGranted(true);
        ((LocalEntity)local.get()).save(localRepository);
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


        Optional<LocalDate> fromDate = Helper.dateFromString(shiftTemplateConfig.getFromDate());
        Optional<LocalDate> toDate = Helper.dateFromString(shiftTemplateConfig.getToDate());
        Optional<LocalTime> startTime = Helper.timeFromString(shiftTemplateConfig.getStartTime());
        Optional<LocalTime> endTime = Helper.timeFromString(shiftTemplateConfig.getEndTime());

        //Daten aktualisieren
        ShiftTemplateEntity shiftTemplateEntity = ((ShiftTemplateEntity)shiftTemplate.get());
        shiftTemplateEntity.setTitle(shiftTemplateConfig.getTitle());
        fromDate.ifPresent(shiftTemplateEntity::setFromDate);
        toDate.ifPresent(shiftTemplateEntity::setToDate);
        startTime.ifPresent(shiftTemplateEntity::setFromTime);
        endTime.ifPresent(shiftTemplateEntity::setToTime);
        shiftTemplateEntity.setRecurrenceType(Helper.getRecurrenceType(recurrenceString));
        shiftTemplateEntity.setWeekdays(weekDays);
        shiftTemplateEntity.updateSlots(newSlots,slotRepository);

        shiftTemplateEntity.save(shiftTemplateRepository);
        return Optional.of(shiftTemplateEntity);
    }


    private SlotEntity getForSlotInfo(SlotConfig info){
        long id = info.getId();
        int numberOfEmployeesNeeded = info.getNumberOfEmployeesNeeded();
        String title = info.getTitle();
        List<ServiceRoleEntity> selectedServiceRoles = getSelectedServiceRole(info);
        return new SlotEntity(id,selectedServiceRoles,numberOfEmployeesNeeded,title);
    }
    private List<ServiceRoleEntity> getSelectedServiceRole(SlotConfig info){
        if (info.getServiceRoleTable() == null)
            return new ArrayList<>();
        return info.getServiceRoleTable().getItems().stream()
                .filter(ListItem::getSelected)
                .map(ListItem::getId)
                .map(srId -> serviceRoleRepository.findById(srId))
                .flatMap(Optional::stream)
                .map(ServiceRoleEntity.class::cast)
                .collect(Collectors.toList());
    }

    // Finde Slot anhand von ID, oder wenn nicht vorhanden für Template
    public Optional<ISlot> getSlotForSlotIdAndShift(long slotId,IShift shift){
        Optional<ISlot> slot = shift.getSlots().stream().filter(sl -> sl.getSlotId() == slotId).findFirst();
        if (!slot.isPresent())
            slot = shift.getSlots().stream().filter(sl -> sl.getTemplateSlotId() == slotId).findFirst();
        return slot;
    }

    public Optional<IEmployee> createOrUpdateEmployee(EmployeeConfig employeeConfig,ILocal local){
        Optional<IEmployee> employee = getEmployeeForName(local,employeeConfig.getNickName());
        if (!employee.isPresent() || !(employee.get() instanceof EmployeeEntity))
            return Optional.empty();

        ((EmployeeEntity) employee.get()).updateWithConfig( employeeConfig, employeeRepository);
        return employee;
    }

    @Override
    public boolean createEmployeeInLocal(IUser user, ILocal local){
        if (!(user instanceof UserEntity) || !(local instanceof LocalEntity))
            return false;

        EmployeeEntity newEmployee = new EmployeeEntity((UserEntity)user, (LocalEntity) local);
        newEmployee.save(employeeRepository);
        return true;
    }
    public void setIsCanceledForShift(IShift dbShift, boolean isCanceled){
        if (!(dbShift instanceof ShiftEntity))
            return;

        ((ShiftEntity) dbShift).setIsCanceled(isCanceled);
        ((ShiftEntity) dbShift).save(shiftRepository);
    }

    public void createManagerRole(long localId){
        Optional<ILocal> local = localRepository.findById(localId);
        if (!local.isPresent())
            return;

        Optional<IServiceRole> adminRole = local.get().getServiceRoles()
                .stream().filter(IServiceRole::isAdminRole)
                .findFirst();

        if (adminRole.isPresent())
            return;

        ServiceRoleEntity.createManagerRole((LocalEntity)local.get()).save(serviceRoleRepository);
    }

    public boolean createUserIfNotExist(String username, String password){
        if (username == null || password ==  null)
            return false;

        Optional<IUser> existingUser = getUser(username);
        if (existingUser.isPresent())
            return false;

        UserEntity newUser = new UserEntity(username,password);
        newUser.save(userRepository);
        return true;
    }

    public Optional<IUser> getUserForNicknameAndPassword(String username, String password){
        if (username == null || password ==  null)
            return Optional.empty();

        return userRepository.findByNicknameAndAndPassword(username,password);
    }

    public void createSysAdminIfNotExists(){
        String sysAdminName = "Sysadmin";
        Optional<IUser> exitingSysAdmin = getUser(sysAdminName);
        if (exitingSysAdmin.isPresent())
            return;

        String sysAdminPassword = "password";
        UserEntity sysAdmin = new UserEntity(sysAdminName,sysAdminPassword,true);
        sysAdmin.save(userRepository);
    }


    @Autowired private ServiceRoleRepository serviceRoleRepository;
    @Autowired private LocalRepository localRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private EmployeeRepository employeeRepository;
    @Autowired private SlotRepository slotRepository;
    @Autowired private ShiftTemplateRepository shiftTemplateRepository;
    @Autowired private ShiftRepository shiftRepository;



    public void createFakeDate(){
        createSysAdminIfNotExists();

        /*
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



        List<DayOfWeek> days = new ArrayList<>(Arrays.asList( DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,
                DayOfWeek.FRIDAY,DayOfWeek.SATURDAY,DayOfWeek.SUNDAY));


        ShiftTemplateEntity shiftTemplate = new ShiftTemplateEntity(local,"Abend",
                LocalTime.of(18,0), LocalTime.of(23,0),
                RecurrenceType.Weekly,LocalDate.of(2020,1,1),null,
                days);

        //List<EmployeeEntity> assigned = new ArrayList<>(Arrays.asList(eCeline));
        //List<EmployeeEntity> applied = new ArrayList<>(Arrays.asList(eMatthias,eMartin));
        SlotEntity slot = new SlotEntity(null,2, new ArrayList<>(), new ArrayList<>());
        slot.setTitle("Bar");
        slot.addToShiftTemplate(shiftTemplate);

        shiftTemplateRepository.save(shiftTemplate);
        slotRepository.save(slot);

        createManagerRole(local.getId());
        createManagerRole(local2.getId());

         */
    }
}
