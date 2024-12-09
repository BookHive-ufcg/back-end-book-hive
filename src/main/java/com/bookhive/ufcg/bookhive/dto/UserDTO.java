package com.bookhive.ufcg.bookhive.dto;

import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

public class UserDTO {
    
    private String firstName;

    private String lastName;

    private String username;

    private Date dateOfBirth;

    private String password;

    private MultipartFile profilePicture;

    public UserDTO(String firstName, String lastName, String username, Date dateOfBirth,String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
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

    public MultipartFile getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(MultipartFile profilePicture) {
        this.profilePicture = profilePicture;
    }
} 