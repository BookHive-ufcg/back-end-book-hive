package com.bookhive.ufcg.bookhive.dto;

public class UserDTO {
    
    private String firstName;

    private String lastName;

    private String username;

    private String dateOfBirth;

    private String password; 

    public UserDTO(String firstName, String lastName, String username, String dateOfBirth,String password) {
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

    public String getDateOfBirth() { return dateOfBirth; }

    public String getPassword() { return password; }

    public void setFirstName(String firstName) { this.firstName = firstName;}

    public void setLastName(String lastName) { this.lastName = lastName;}

    public void setUsername(String username) { this.username = username;}

    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth;}

    public void setPassword(String password) { this.password = password;}

} 