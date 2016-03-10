package web.entity;

import javax.persistence.*;

@Entity
@Table(name = "Phone")
public class Phone
{
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "PersonID")
    private int personID;

    @Column(name="PhoneNumber")
    private String phonenumber;

    @Column(name="Type")
    private String type;

    @Column(name="Def")
    private int def;

    public Phone(){}

    public Phone(int personID, String phonenumber, String type, int def)
    {
        this.personID = personID;
        this.phonenumber = phonenumber;
        this.type = type;
        this.setDef(def);
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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
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
