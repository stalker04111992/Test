package web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Roles")
public class Role
{
    @Id
    @Column(name="id", nullable = false)
    private int id;
    @Column(name="userID", nullable = false)
    private int userId;
    @Column(name="role", nullable = false, length = 20)
    private String role;

    public Role(){}

    public Role(int userId, String role)
    {
        this.userId = userId;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
