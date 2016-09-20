package bo.edu.ucbcba.videoclub.model;

import bo.edu.ucbcba.videoclub.exceptions.ValidationException;

import javax.persistence.*;
/**
 * Created by privado on 16/05/2016.
 */
@Entity
public class Client {
    @Id
    private String ci; // Primary Key, and Auto Generated

    @Column(length = 20)
    private String firstname;

    @Lob
    @Column(length = 30)
    private String lastname; // Lob will create as TEXT instead of VARCHAR

    @Lob
    @Column(length = 30)
    private String address; // In minutes

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        if (firstname == null)
            throw new ValidationException("Null Firstname");
        if (firstname.isEmpty())
            throw new ValidationException("Firstname can't be empty");
        if (firstname.length() > 255)
            throw new ValidationException("Firstname is too long");
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}


