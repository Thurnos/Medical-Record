package com.informatics.medical_record_spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name="username" , nullable = false,unique = true)
    private String name;

    @Column(name="email" , nullable = false,unique = true)
    private String email;

    @Column(name = "password_hash" ,nullable = false )
    private String passwordHash;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    @JsonManagedReference
    private Role role;



    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore  // Forward reference for Patients
    private List<Patients> patients;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore  // Forward reference for Doctors
    private List<Doctors> doctors;
//    @JsonIgnore
//    @OneToMany(mappedBy = "user")
//    private List<Doctors> doctor;
//
//    @JsonIgnore
//    @OneToMany(mappedBy = "user")
//    private List<Patients> patients;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public User() {
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public String get_role(){
        return role.getRolename();
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User(Integer id, String name, String email, String passwordHash, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }
}
