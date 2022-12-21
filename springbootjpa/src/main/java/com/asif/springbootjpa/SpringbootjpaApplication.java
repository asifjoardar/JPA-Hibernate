package com.asif.springbootjpa;

import com.asif.springbootjpa.models.Employee;
import com.asif.springbootjpa.repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

@SpringBootApplication
@EnableTransactionManagement
public class SpringbootjpaApplication {

	/*@PersistenceUnit
	private EntityManagerFactory emf;*/

	/*@PersistenceContext //(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;*/

	@Autowired
	EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootjpaApplication.class, args);
	}
	@PostConstruct
	public void start(){
		/*Employee employee = new Employee();
		employee.setAge(20);
		employee.setName("ashraf");
		employee.setSsn("1234");
		employee.setDob(new Date());*/

		/*EntityManager entityManager = emf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(employee);
		transaction.commit();
		entityManager.close();*/

		/*Employee employee = entityManager.find(Employee.class, 1);
		System.out.println(employee);*/

		Optional<Employee> employee =  employeeRepository.findById(1); //may or may not be exists, that's why using Optional api
		System.out.println(employee.get());
		updateEmployee(employee.get());
	}

	@Transactional //(rollbackOn = SQLException.class)
	private void updateEmployee(Employee employee) {
		//get transaction -> imperative approach
		// Start transaction
		employee.setName("asif");
		employeeRepository.save(employee); //declarative approach
		// end transaction
		// roll back
	}
}
