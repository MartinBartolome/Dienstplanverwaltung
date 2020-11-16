package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.database.ILocal;

import javax.persistence.*;
import java.util.List;


@Entity
@Table
public class LocalEntity implements ILocal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "local")
    private List<EmployeeEntity> employees;

    @OneToMany(mappedBy = "local")
    private List<ShiftTemplateEntity> shifts;

    @Override
    public long getId() { return id; }

    public LocalEntity(){}
}
