package com.metier.service;

import java.util.ArrayList;
import java.util.List;
 
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import com.metier.dao.EmployerDoa;
import com.metier.model.Employer;

@WebService(name="EmployerService")
public class PersoneServiceImp  {
	EmployerDoa pdo = new EmployerDoa();

	 
	@WebMethod(operationName = "ajouterEmployer")
	public void addPerson(
			@WebParam(name = "nom")
			String nom,
			@WebParam(name = "prenom")
			String prenom,
			@WebParam(name = "age") 
			int age) {
		// TODO Auto-generated method stub
		pdo.saveEmployer(nom, age, prenom);
	}

	 
	@WebMethod
	public Employer getPerson(int var1) {
		Employer Employer = pdo.getPers(var1);
		return Employer;
	}
 
	@WebMethod
	public List<Employer> getAllPersons() {
		List<Employer> list = new ArrayList<Employer>();
		list = pdo.getEmployer();
		return list;
	}
 
	@WebMethod
	public void updatePerson(Employer var1) {
		Employer per = new Employer();
		per.setAge(var1.getAge());
		per.setNom(var1.getNom());
		per.setPreNom(var1.getPreNom());
		pdo.updateEmployer(var1.getId(), per);
	}
 
	@WebMethod
	public void deletePerson(int var1) {
		pdo.deleteEmployer(var1);
	}

}
