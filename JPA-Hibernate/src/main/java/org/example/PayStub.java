package org.example;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class PayStub {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Date payPeriodStart;
    private Date payPeriodEnd;
    private float salary;

    @ManyToOne
    //@JoinColumn(name = "paystub_for")
    private Employee employee;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPayPeriodStart() {
        return payPeriodStart;
    }

    public void setPayPeriodStart(Date payPeriodStart) {
        this.payPeriodStart = payPeriodStart;
    }

    public Date getPayPeriodEnd() {
        return payPeriodEnd;
    }

    public void setPayPeriodEnd(Date payPeriodEnd) {
        this.payPeriodEnd = payPeriodEnd;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
