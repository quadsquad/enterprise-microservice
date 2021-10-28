package com.entreprise.app.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;


@Document(collection="entreprise")
public class Entreprise {
	
	@Id
	private String id_entr;
	
	private String nom_entr;
	
	private String desc_entr;
	
	private int number_empl_entr;
	
	private String domaine_entr;
	
	private String email_entr;
	
	private String logo_entr;
	
	private String website_entr;
	
	private String address_entr;
	
	private String region_entr;
	
	private String num_tel_entr_one;
	
	private String num_tel_entr_two;
	
	private ArrayList<Object> emplois;

	public String getId_entr() {
		return id_entr;
	}

	public void setId_entr(String id_entr) {
		this.id_entr = id_entr;
	}

	public String getNom_entr() {
		return nom_entr;
	}

	public void setNom_entr(String nom_entr) {
		this.nom_entr = nom_entr;
	}

	public String getDesc_entr() {
		return desc_entr;
	}

	public void setDesc_entr(String desc_entr) {
		this.desc_entr = desc_entr;
	}

	public int getNumber_empl_entr() {
		return number_empl_entr;
	}

	public void setNumber_empl_entr(int number_empl_entr) {
		this.number_empl_entr = number_empl_entr;
	}

	public String getDomaine_entr() {
		return domaine_entr;
	}

	public void setDomaine_entr(String domaine_entr) {
		this.domaine_entr = domaine_entr;
	}

	public String getEmail_entr() {
		return email_entr;
	}

	public void setEmail_entr(String email_entr) {
		this.email_entr = email_entr;
	}

	public String getLogo_entr() {
		return logo_entr;
	}

	public void setLogo_entr(String logo_entr) {
		this.logo_entr = logo_entr;
	}

	public String getWebsite_entr() {
		return website_entr;
	}

	public void setWebsite_entr(String website_entr) {
		this.website_entr = website_entr;
	}

	public String getAddress_entr() {
		return address_entr;
	}

	public void setAddress_entr(String address_entr) {
		this.address_entr = address_entr;
	}

	public String getRegion_entr() {
		return region_entr;
	}

	public void setRegion_entr(String region_entr) {
		this.region_entr = region_entr;
	}

	public String getNum_tel_entr_one() {
		return num_tel_entr_one;
	}

	public void setNum_tel_entr_one(String num_tel_entr_one) {
		this.num_tel_entr_one = num_tel_entr_one;
	}
	
	public String getNum_tel_entr_two() {
		return num_tel_entr_two;
	}

	public void setNum_tel_entr_two(String num_tel_entr_two) {
		this.num_tel_entr_two = num_tel_entr_two;
	}

	public ArrayList<Object> getEmplois() {
		return emplois;
	}

	public void setEmplois(ArrayList<Object> emplois) {
		this.emplois = emplois;
	}

}