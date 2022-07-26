package com.example.WorkplaceAutomation.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
public class Person {
    private @Id @Column(length = 10) String seriesNumber;
    private @Column(length = 32) String firstName;
    private @Column(length = 32) String lastName;
    private @Column(length = 32) String patronym;
    private Date passportDate;
    private @Column(length = 128) String passportOrganName;
    private @Column(length = 6) String passportOrganCode;
    private Date birthDate;
    private Boolean isMarried;
    private @Column(length = 255) String address;
    private @Column(length = 12) String contactNumber;
    private @OneToMany(mappedBy = "person", fetch = FetchType.EAGER) Collection<WorkPeriod> workPeriod;


    public String getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(String seriesNumber) {
        this.seriesNumber = seriesNumber;
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

    public String getPatronym() {
        return patronym;
    }

    public void setPatronym(String patronym) {
        this.patronym = patronym;
    }

    public Date getPassportDate() {
        return passportDate;
    }

    public void setPassportDate(Date passportDate) {
        this.passportDate = passportDate;
    }

    public String getPassportOrganName() {
        return passportOrganName;
    }

    public void setPassportOrganName(String passportOrganName) {
        this.passportOrganName = passportOrganName;
    }

    public String getPassportOrganCode() {
        return passportOrganCode;
    }

    public void setPassportOrganCode(String passportOrganCode) {
        this.passportOrganCode = passportOrganCode;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getMarried() {
        return isMarried;
    }

    public void setMarried(Boolean married) {
        isMarried = married;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Collection<WorkPeriod> getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Collection<WorkPeriod> workPeriod) {
        this.workPeriod = workPeriod;
    }

    @Override
    public String toString() {
        return "Person{" +
                "seriesNumber=" + seriesNumber +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronym='" + patronym + '\'' +
                ", passportDate=" + passportDate +
                ", passportOrganName='" + passportOrganName + '\'' +
                ", passportOrganCode=" + passportOrganCode +
                ", birthDate=" + birthDate +
                ", isMarried=" + isMarried +
                ", address='" + address + '\'' +
                ", contactNumber=" + contactNumber +
                ", workPeriod=" + workPeriod +
                '}';
    }
}
