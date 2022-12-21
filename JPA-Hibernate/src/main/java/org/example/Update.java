package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class Update {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Employee employee = entityManager.find(Employee.class, 2);
        EmailGroup emailGroup = entityManager.find(EmailGroup.class, 8);

        employee.setEmailGroups(List.of(emailGroup));
        emailGroup.setEmployees(List.of(employee));

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        /*entityManager.persist(emailGroup);
        entityManager.persist(employee);*/

        entityTransaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
