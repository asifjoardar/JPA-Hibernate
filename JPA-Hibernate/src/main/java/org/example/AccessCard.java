package org.example;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class AccessCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Date issueDate;
    private boolean isActive;
    private String firmareVersion;

    @OneToOne //(mappedBy = "accessCard")
    private Employee owner;

    public Employee getOwner() {
        return owner;
    }

    public void setOwner(Employee owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getFirmareVersion() {
        return firmareVersion;
    }

    public void setFirmareVersion(String firmareVersion) {
        this.firmareVersion = firmareVersion;
    }

    @Override
    public String toString() {
        return "AccessCard{" +
                "id=" + id +
                ", issueDate=" + issueDate +
                ", isActive=" + isActive +
                ", firmareVersion='" + firmareVersion + '\'' +
                '}';
    }
}
