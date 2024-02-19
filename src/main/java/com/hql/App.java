package com.hql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class App {
	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.configure("emp.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session s = factory.openSession();
		String query = "from Employee as s where city=:x and name=:n";
		Query<Employee> q = s.createQuery(query);
		q.setParameter("x", "Hyderbad");
		q.setParameter("n", "wasim");
		List<Employee> list = q.list();
		for (Employee e : list) {
			System.out.println(e.getId() + ":" + e.getName() + ":" + e.getCity() + ":" + e.getCompany());
		}
		System.out.println("-------------------Delete-----------------");
		Transaction tx = s.beginTransaction();
		@SuppressWarnings("unchecked")
		Query<Employee> q1 = s.createQuery("delete from Employee s where s.city=:c");
		q1.setParameter("c", "Hyderbad");
		int r = q1.executeUpdate();
		System.out.println("deleted...");
		System.out.println(r);
		System.out.println("------------------------Update---------------------");
		Query q2 = s.createQuery("update Employee set city=:c where name=:n");
		q2.setParameter("c", "Hyderbad");
		q2.setParameter("n", "slaman");
		int r1 = q2.executeUpdate();
		System.out.println("Updated..");
		System.out.println(r1);
		tx.commit();
//		Employee emp = new Employee();
//		emp.setId(101);
//		emp.setName("wasim");
//		emp.setCompany("TCS");
//		emp.setCity("Hyderbad");
//		Employee emp1 = new Employee();
//		emp1.setId(102);
//		emp1.setName("slaman");
//		emp1.setCompany("INFOSYS");
//		emp1.setCity("Pune");
//		Employee emp2 = new Employee();
//		emp2.setId(103);
//		emp2.setName("Lukman");
//		emp2.setCompany("Congnivetizen");
//		emp2.setCity("Hyderbad");

//		Transaction tx = s.beginTransaction();
//		s.save(emp);
//		s.save(emp1);
//		s.save(emp2);
//		tx.commit();
		s.close();
		factory.close();
	}
}
