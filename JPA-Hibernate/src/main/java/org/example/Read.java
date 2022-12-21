package org.example;

import jakarta.persistence.*;

public class Read {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Employee employee = entityManager.find(Employee.class, 1);
        System.out.println(employee);
        System.out.println("*********************");
        System.out.println(employee.getAccessCard());
    }
}
