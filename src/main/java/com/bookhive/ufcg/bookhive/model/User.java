package com.bookhive.ufcg.bookhive.model;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "usuario")
public class User {

    @Id
    private String username;

    @Column(name = "nome")
    private String firstName;

    @Column(name = "sobrenome")
    private String lastName;

    @Column(name = "data_nascimento")
    private Date dateOfBirth;

    @Column(name = "senha")
    private String password;

    @Lob
    @Column(name = "foto_perfil")
    private byte[] profilePicture;

    public User() {
    }

    public User(String firstName, String lastName, String username, Date dateOfBirth,String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
    }

    public User(String firstName, String lastName, String username, Date dateOfBirth, String password, byte[] profilePicture) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.profilePicture = profilePicture;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }
    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getFullName() {return firstName +" "+ lastName;}

    public String getUsername() { return username; }

    public Date getDateOfBirth() { return dateOfBirth; }

    public String getPassword() { return password; }

    public void setFirstName(String firstName) { this.firstName = firstName;}

    public void setLastName(String lastName) { this.lastName = lastName;}

    public void setUsername(String username) { this.username = username;}

    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth;}

    public void setPassword(String password) { this.password = password;}

} 
