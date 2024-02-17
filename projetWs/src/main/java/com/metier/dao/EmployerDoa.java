package com.metier.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.metier.model.Employer;

import org.hibernate.Query;
import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
 
public class EmployerDoa {

	Connection connection;

	

	public void saveEmployer(String nom, int age,String prenom) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Employer per = new Employer();
			per.setNom(nom);
			per.setPreNom(prenom);
			per.setAge(age);
			session.save(per);
			transaction.commit();
			System.out.println("Records inserted sucessessfully");
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public List<Employer> getEmployer() {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Employer");
		List<Employer> per = query.list();
		 

		session.close();
		return per.stream() 
                .collect(Collectors.toList());
	}

	public Employer getPers(int id) {
		Session session = HibernateUtil.getSession();
		Employer Employer = session.get(Employer.class, id);
		return Employer;
	}

	public void updateEmployer(int id, Employer per) {

		Session session = HibernateUtil.getSession();
		 Transaction tx = session.beginTransaction();
		Employer Employer = session.load(Employer.class, id);
//		Employer.set(id);
		Employer.setPreNom(per.getPreNom());
		Employer.setNom(per.getNom());
//		session.update(Employer);
		tx.commit();
		session.close();
 

	}

	public int deleteEmployer(int id) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from Employer where id = :id";
		Query query = session.createQuery(hql);
		query.setInteger("id", id);
		int rowCount = query.executeUpdate();
		System.out.println("Rows affected: " + rowCount);
		tx.commit();
		session.close();
		return rowCount;
	}

}
