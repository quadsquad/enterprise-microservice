package com.entreprise.app.services;

import org.springframework.http.ResponseEntity;

import com.entreprise.app.entities.Entreprise;

public interface EntrepriseService {
	public ResponseEntity<?> getAllEntreprises();
	public ResponseEntity<?> addEntreprise(Entreprise entreprise);
	public ResponseEntity<?> updateEntreprise(Entreprise entreprise, String id_entr);
	public ResponseEntity<?> deleteEntreprise(String id_entr);
	public ResponseEntity<?> addEmpToEntreprise(String id_entr, String id_emp);
	public ResponseEntity<?> rmvEmpFromEntreprise(String id_entr, String id_emp);
}
