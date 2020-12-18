package ffhs.students.projects.dienstplanverwaltung.database.sql;

import ffhs.students.projects.dienstplanverwaltung.database.ILocal;
import ffhs.students.projects.dienstplanverwaltung.database.ISaveable;
import ffhs.students.projects.dienstplanverwaltung.database.IUser;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public
class UserEntity implements IUser, ISaveable {
    @Id
    @Column(length = 64)
    private String nickname;

    private String password;

    public UserEntity(String nickname, String password) {
        this.nickname = nickname;
        this.password =password;
    }



    private boolean isSysadmin;
    public UserEntity(String nickname, String password, boolean isSysadmin){
        this.nickname = nickname;
        this.password = password;
        this.isSysadmin = isSysadmin;
    }
    public boolean isSysadmin() {  return isSysadmin; }

    public String getNickname() {
        return nickname;
    }
    public UserEntity(String nickname){this.nickname = nickname;}
    public UserEntity() { }



    public List<EmployeeEntity> getEmployees() {  return employees;  }

    @OneToMany(mappedBy = "user")
    private List<EmployeeEntity> employees;

    @OneToMany(mappedBy = "owner")
    private List<LocalEntity> ownedLocals;
}
