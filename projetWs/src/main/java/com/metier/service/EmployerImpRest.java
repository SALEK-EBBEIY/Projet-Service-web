package com.metier.service;

import com.metier.model.Employer;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.metier.dao.EmployerDoa;
@ApplicationPath(value = "Employers")
//@Path("/Employers")

@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.APPLICATION_JSON)
public class EmployerImpRest extends Metadata{
	EmployerDoa pdo = new EmployerDoa();

	public EmployerImpRest() {
		// TODO Auto-generated constructor stub
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllPersons() {

		List<Employer> Employers = pdo.getEmployer();

		// Return the list of Employers as JSON
		return Response.ok(Employers).build();
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response  updatePerson(@PathParam("id") int id, Employer var1) {
		Employer existingEmployer = pdo.getPers(id); // Implement this method
		if (existingEmployer != null) {

			Employer per = new Employer();
			per.setAge(var1.getAge());
			per.setNom(var1.getNom());
			per.setPreNom(var1.getPreNom());
			pdo.updateEmployer(var1.getId(), per);

			return Response.ok().build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

	}

	@DELETE
	@Path("/{id}")
	public Response  deletePerson(@PathParam("id") int var1) {
		Employer existingEmployer = pdo.getPers(var1); // Implement this method
		if (existingEmployer != null) {
			pdo.deleteEmployer(var1);
			return Response.ok().build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPerson(String nom, String prenom, int age) {
		pdo.saveEmployer(nom, age, prenom);
		return Response.status(Response.Status.CREATED).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Employer getPerson(@PathParam("id") int var1) {
		Employer Employer = pdo.getPers(var1);
		return Employer;
	}

}
