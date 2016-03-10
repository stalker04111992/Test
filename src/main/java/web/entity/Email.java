package web.entity;


import javax.annotation.Generated;
import javax.persistence.*;
import javax.swing.text.StringContent;

@Entity
@Table(name = "Email")
public class Email
{
    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;
    @Column(name = "personID")
    private int personID;
    @Column(name = "Email")
    private String email;
    @Column(name="Type")
    private String type;
    @Column(name="Def")
    private int def;

    public Email(){}

    public Email(int personID, String email, String type, int def)
    {
        this.personID = personID;
        this.email = email;
        this.type = type;
        this.def = def;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }
}
