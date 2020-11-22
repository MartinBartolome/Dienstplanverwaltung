package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.database.IServiceRole;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class ServiceRoleEntity implements IServiceRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private boolean isActive;
    @ManyToOne
    @JoinColumn()
    private LocalEntity local;

    public ServiceRoleEntity(String name,LocalEntity local, boolean isActive) {
        this.name = name;
        this.local = local;
        this.isActive = isActive;
    }

    public ServiceRoleEntity() {  }

    public String getName() { return name;  }
    public boolean isActive() {  return isActive; }
    public LocalEntity getLocal() {  return local; }

}
