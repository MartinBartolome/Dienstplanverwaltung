package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.database.*;

import javax.persistence.*;
import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Entity
@Table
class ShiftEntity implements IShift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne
    @JoinColumn()
    private LocalEntity local;


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
    public Optional<IShiftTemplate> getShiftTemplate() { return Optional.ofNullable(shiftTemplate); }


    private boolean isCanceled;
    @Override
    public boolean getIsCanceled() { return isCanceled; }


    @OneToMany(mappedBy = "shift")
    private List<SlotEntity> slots;
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

    public ShiftEntity() {}
    public ShiftEntity(ShiftTemplateEntity template, LocalDate day){
        this.day = day;
        title = template.getTitle();
        shiftTemplate = template;
        isCanceled = false;
        fromTime = template.getFromTime();
        toTime = template.getToTime();
        slots = new ArrayList<>();
        local = (LocalEntity) template.getLocal(); //todo
    }

    public void addSlot(SlotEntity slot){
        slots.add(slot);
    }
}
