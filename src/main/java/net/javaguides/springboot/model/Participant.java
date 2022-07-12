package net.javaguides.springboot.model;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
@Table(name = "participants")

public class Participant {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
   @Column(name = "first_name")
   //TODO CR: Prefer camel case
   private String first_name;
   @Column(name = "last_name")
   private String last_name;
   @Column(name = "email_id")
    private String email_id;

    public Participant(){
        super();
    }
    public Participant(String firstname, String lastname, String emailid) {
        super();
        this.first_name = firstname;
        this.last_name = lastname;
        this.email_id = emailid;
    }

    // TODO CR: This can be removed by @Getter annotation over class, keep target of less code, more capabilities
    // And remove as much boilerplate as you can
    // https://projectlombok.org/features/GetterSetter
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public String getEmail_id() {
        return email_id;
    }
    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }
}
