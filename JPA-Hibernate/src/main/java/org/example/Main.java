package org.example;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.setName("foo bar");
        employee.setAge(20);
        employee.setDate(new Date());
        employee.setSsn("123");

        Employee employee1 = new Employee();
        employee1.setName("foo bar");
        employee1.setAge(30);
        employee1.setDate(new Date());
        employee1.setSsn("456");

        AccessCard card = new AccessCard();
        card.setIssueDate(new Date());
        card.setActive(true);
        card.setFirmareVersion("1.0.0");
        card.setOwner(employee);
        employee.setAccessCard(card);

        AccessCard card1 = new AccessCard();
        card1.setIssueDate(new Date());
        card1.setActive(false);
        card1.setFirmareVersion("1.1.0");
        card1.setOwner(employee1);
        employee1.setAccessCard(card1);

        PayStub payStub = new PayStub();
        payStub.setPayPeriodEnd(new Date());
        payStub.setPayPeriodStart(new Date());
        payStub.setSalary(1000);
        payStub.setEmployee(employee);
        //employee.addPayStub(payStub);

        PayStub payStub1 = new PayStub();
        payStub1.setPayPeriodEnd(new Date());
        payStub1.setPayPeriodStart(new Date());
        payStub1.setSalary(1000);
        payStub1.setEmployee(employee);
        //employee.addPayStub(payStub);

        employee.setPayStubs(List.of(payStub, payStub1));

        EmailGroup emailGroup = new EmailGroup();
        emailGroup.setName("abc");
        EmailGroup emailGroup1 = new EmailGroup();
        emailGroup1.setName("def");
        employee.setEmailGroups(List.of(emailGroup, emailGroup1));
        emailGroup1.setEmployees(List.of(employee));
        emailGroup.setEmployees(List.of(employee));
        employee1.setEmailGroups(List.of(emailGroup));
        emailGroup.setEmployees(List.of(employee1));


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        entityManager.persist(employee);
        entityManager.persist(employee1);
        entityManager.persist(card);
        entityManager.persist(card1);
        entityManager.persist(payStub);
        entityManager.persist(payStub1);
        entityManager.persist(emailGroup);
        entityManager.persist(emailGroup1);

        entityTransaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}