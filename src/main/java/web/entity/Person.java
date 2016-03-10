package web.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "Person")
public class Person implements Serializable
{
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "FirstName", nullable = false, length = 50)
    private String firstName;
    @Column(name = "LastName", nullable = false, length = 50)
    private String lastName;
    @Column(name = "Patronymic", nullable = false, length = 50)
    private String patronymic;
    @Column(name = "BirthDate", length = 10, nullable = false)
    private String date;
    @Column(name = "Address", nullable = false, length = 255)
    private String address;
    @Column(name = "DefaultPhone", nullable = false)
    private int phone;
    @Column(name = "DefaultEmail", nullable = false)
    private int email;

    public Person(){}

    public Person(String firstName, String lastName,String patronymic, String date, String address, int phone, int email)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.date = date;
        this.address = address;
        this.setPhone(phone);
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getEmail() {
        return email;
    }

    public void setEmail(int email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }
}
