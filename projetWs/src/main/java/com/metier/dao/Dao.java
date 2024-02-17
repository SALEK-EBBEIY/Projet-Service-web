package com.metier.dao;

import java.util.List;

import com.metier.model.Employer;

public interface Dao  {

	List<Employer> aEmployerne();

	Employer getById(int id);

	void sauvgarde(String nom,String prenom,int age );

	void modifier(Employer pers );

	void suprimer(int t);
}