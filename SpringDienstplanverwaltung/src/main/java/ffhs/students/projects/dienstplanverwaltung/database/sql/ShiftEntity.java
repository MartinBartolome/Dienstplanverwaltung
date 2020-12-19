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
    private LocalDate day;
    private String title;
    @ManyToOne
    @JoinColumn()
    private ShiftTemplateEntity shiftTemplate;
    private boolean isCanceled;
    @OneToMany(mappedBy = "shift")
    private List<SlotEntity> slots;
    private LocalTime fromTime;
    private LocalTime toTime;

    // Getter
    @Override public long getId() {  return id;  }
    @Override public LocalDate getDay() {  return day; }
    @Override public String getTitle() { return title; }
    @Override public Optional<IShiftTemplate> getShiftTemplate() { return Optional.ofNullable(shiftTemplate); }
    @Override public boolean getIsCanceled() { return isCanceled; }
    @Override
    public List<ISlot> getSlots() {
        return slots.stream()
                .map(ISlot.class::cast)
                .collect(Collectors.toList());
    }
    @Override public LocalTime getFromTime() { return fromTime; }
    @Override public LocalTime getToTime() { return toTime;  }

    // Setter
    public void setIsCanceled(boolean canceled) { isCanceled = canceled; }

    // Konstruktoren
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

    // Aktualisierungen
    public void addSlot(SlotEntity slot){
        slots.add(slot);
    }
}
