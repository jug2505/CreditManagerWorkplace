package com.example.WorkplaceAutomation.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
public class Contract {
    private @Id @GeneratedValue Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Person person;
    private BigDecimal wishedSum;
    private Boolean isApproved;
    private Short creditTerm;
    private BigDecimal approvedSum;
    private Date signDate;
    private Boolean isSigned;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public BigDecimal getWishedSum() {
        return wishedSum;
    }

    public void setWishedSum(BigDecimal wishedSum) {
        this.wishedSum = wishedSum;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public Short getCreditTerm() {
        return creditTerm;
    }

    public void setCreditTerm(Short creditTerm) {
        this.creditTerm = creditTerm;
    }

    public BigDecimal getApprovedSum() {
        return approvedSum;
    }

    public void setApprovedSum(BigDecimal approvedSum) {
        this.approvedSum = approvedSum;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public Boolean getSigned() {
        return isSigned;
    }

    public void setSigned(Boolean signed) {
        isSigned = signed;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", person=" + person +
                ", wishedSum=" + wishedSum +
                ", isApproved=" + isApproved +
                ", creditTerm=" + creditTerm +
                ", approvedSum=" + approvedSum +
                ", signDate=" + signDate +
                ", isSigned=" + isSigned +
                '}';
    }
}