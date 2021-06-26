/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Dang Phat
 */
public class Candidate {
    private int id;
    private String firstName;
    private String lastName;
    private int yearOfBirth; //edit the variable name to match the content of the data it stores
    private String address;
    private String phone;
    private String email;
    private int type;
    protected ArrayList<Skill> listSkills;

    public Candidate() {
    }

    public Candidate(int id, String firstName, String lastName, int yearOfBirth, String address, String phone, String email, int type, ArrayList<Skill> listSkills) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.type = type;
        this.listSkills = listSkills;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ArrayList<Skill> getListSkills() {
        return listSkills;
    }

    public void setListSkills(ArrayList<Skill> listSkills) {
        this.listSkills = listSkills;
    }
    
    public String saveFormat() {
        return id + "|" + firstName + "|" + lastName + "|" + yearOfBirth + "|" + address + "|" + phone + "|" + email + "|" + type;
    }     
}
