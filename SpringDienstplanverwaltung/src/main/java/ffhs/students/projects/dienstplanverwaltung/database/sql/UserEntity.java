package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.database.IUser;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
class UserEntity implements IUser {
    @Id
    @Column(length = 64)
    private String nickname;


    public String getNickname() {
        return nickname;
    }
    public UserEntity(String nickname){this.nickname = nickname;}
    public UserEntity() { }


    @OneToMany(mappedBy = "user")
    private List<EmployeeEntity> employees;


}
