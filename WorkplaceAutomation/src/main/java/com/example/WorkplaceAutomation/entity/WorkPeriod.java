package com.example.WorkplaceAutomation.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class WorkPeriod {
    private @Id @GeneratedValue Long id;
    private Date startDate;
    private Date endDate;
    private @Column(length = 128) String jobPosition;
    private @Column(length = 128) String organisationName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Person person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}
