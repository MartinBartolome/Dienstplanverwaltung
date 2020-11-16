package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.database.IEmployee;
import ffhs.students.projects.dienstplanverwaltung.database.IShift;
import ffhs.students.projects.dienstplanverwaltung.database.IShiftTemplate;
import ffhs.students.projects.dienstplanverwaltung.database.ISlot;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table
public class ShiftEntity implements IShift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "shift")
    private List<SlotEntity> slots;

    @Override
    public long getId() {  return id;  }

    private LocalDate day;
    @Override
    public LocalDate getDay() {  return day; }

    private String title;
    @Override
    public String getTitle() { return title; }

    @ManyToOne
    @JoinColumn()
    private ShiftTemplateEntity shiftTemplate;
    @Override
    public IShiftTemplate getShiftTemplate() { return shiftTemplate; }


    private boolean isCanceled;
    @Override
    public boolean getIsCanceled() { return isCanceled; }


    @Override
    public List<ISlot> getSlots() {
        return slots.stream()
                .map(ISlot.class::cast)
                .collect(Collectors.toList());
    }

    private LocalTime fromTime;
    @Override
    public LocalTime getFromTime() { return fromTime; }

    private LocalTime toTime;
    @Override
    public LocalTime getToTime() { return toTime;  }


    public ShiftEntity(ShiftTemplateEntity template, LocalDate day){
        this.day = day;
        title = template.getTitle();
        shiftTemplate = template;
        isCanceled = false;
        fromTime = template.getFromTime();
        toTime = template.getToTime();
    }
}
