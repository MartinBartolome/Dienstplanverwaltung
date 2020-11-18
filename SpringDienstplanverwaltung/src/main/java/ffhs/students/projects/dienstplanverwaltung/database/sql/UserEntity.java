package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.database.IUser;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class UserEntity implements IUser {
    @Id
    @Column(length = 64)
    private String email;

    public String getEmail() {
        return email;
    }
    public UserEntity(String email){
        this.email = email;
    }
    public UserEntity() { }


    @OneToMany(mappedBy = "user")
    private List<EmployeeEntity> employees;


}
