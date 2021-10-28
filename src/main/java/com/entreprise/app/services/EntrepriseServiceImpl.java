package com.entreprise.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.entreprise.app.entities.Emploi;
import com.entreprise.app.entities.Entreprise;
import com.entreprise.app.repositories.EmploiRepository;
import com.entreprise.app.repositories.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements EntrepriseService {
	@Autowired
	private EntrepriseRepository entr_rep;
	
	@Autowired
	private EmploiRepository emp_rep;
	
	@Autowired
	private RestTemplateBuilder restTemplateBuilder;
	
	public ResponseEntity<?> getAllEntreprises() {
		List<Entreprise> entreprises = entr_rep.findAll();
		if (entreprises.size() > 0) {
			return new ResponseEntity<List<Entreprise>>(entreprises, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Entreprises not available!", HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<?> addEntreprise(Entreprise entreprise) {
		try {
			entreprise.setEmplois(new ArrayList<Object>());
			entr_rep.save(entreprise);
			return new ResponseEntity<Entreprise>(entreprise, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<?> updateEntreprise(Entreprise entreprise, String id_entr) {
		try {
			if (entr_rep.findById(id_entr).isPresent()) {
				Entreprise existedEntr = entr_rep.findById(id_entr).get();
				if (entreprise.getNom_entr() == null) {
					existedEntr.setNom_entr(existedEntr.getNom_entr());
				} else {
					existedEntr.setNom_entr(entreprise.getNom_entr());
				}
				if (entreprise.getDesc_entr() == null) {
					existedEntr.setDesc_entr(existedEntr.getDesc_entr());
				} else {
					existedEntr.setDesc_entr(entreprise.getDesc_entr());
				}
				if (entreprise.getNumber_empl_entr() == 0) {
					existedEntr.setNumber_empl_entr(existedEntr.getNumber_empl_entr());
				} else {
					existedEntr.setNumber_empl_entr(entreprise.getNumber_empl_entr());
				}
				if (entreprise.getEmail_entr() == null) {
					existedEntr.setEmail_entr(existedEntr.getEmail_entr());
				} else {
					existedEntr.setEmail_entr(entreprise.getEmail_entr());
				}
				if (entreprise.getLogo_entr() == null) {
					existedEntr.setLogo_entr(existedEntr.getLogo_entr());
				} else {
					existedEntr.setLogo_entr(entreprise.getLogo_entr());
				}
				if (entreprise.getWebsite_entr() == null) {
					existedEntr.setWebsite_entr(existedEntr.getWebsite_entr());
				} else {
					existedEntr.setWebsite_entr(entreprise.getWebsite_entr());
				}
				if (entreprise.getAddress_entr() == null) {
					existedEntr.setAddress_entr(existedEntr.getAddress_entr());
				} else {
					existedEntr.setAddress_entr(entreprise.getAddress_entr());
				}
				if (entreprise.getRegion_entr() == null) {
					existedEntr.setRegion_entr(existedEntr.getRegion_entr());
				} else {
					existedEntr.setRegion_entr(entreprise.getRegion_entr());
				}
				if (entreprise.getDomaine_entr() == null) {
					existedEntr.setDomaine_entr(existedEntr.getDomaine_entr());
				} else {
					existedEntr.setDomaine_entr(entreprise.getDomaine_entr());
				}
				if (entreprise.getNum_tel_entr_one() == null) {
					existedEntr.setNum_tel_entr_one(existedEntr.getNum_tel_entr_one());
				} else {
					existedEntr.setNum_tel_entr_one(entreprise.getNum_tel_entr_one());
				}
				if (entreprise.getNum_tel_entr_two() == null) {
					existedEntr.setNum_tel_entr_two(existedEntr.getNum_tel_entr_two());
				} else {
					existedEntr.setNum_tel_entr_two(entreprise.getNum_tel_entr_two());
				}
				entr_rep.save(existedEntr);
			} else {
				return new ResponseEntity<>("Entreprise not found!", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Entreprise>(entr_rep.findById(id_entr).get(), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<?> deleteEntreprise(String id_entr) {
		try {
			if (entr_rep.findById(id_entr).isPresent()) {
				entr_rep.delete(entr_rep.findById(id_entr).get());
				return new ResponseEntity<>("Entreprise deleted successfully", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Entreprise not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<?> addEmpToEntreprise(String id_entr, String id_emp) {
		try {
			if (entr_rep.findById(id_entr).isPresent() && emp_rep.findById(id_emp).isPresent()) {
				Entreprise existedEntr = entr_rep.findById(id_entr).get();
				//Emploi existedEmp = emp_rep.findById(id_emp).get();
				Object existedEmp = restTemplateBuilder.build().getForObject("https://jobrecruitement.herokuapp.com/emploibyid/"+id_emp, Object.class);
				if (existedEntr.getEmplois().size() == 0) {
					existedEntr.getEmplois().add(existedEmp);
					existedEntr.setEmplois(existedEntr.getEmplois());
				} else {
					ArrayList<Object> id_list = new ArrayList<Object>();
					for (int i = 0; i<existedEntr.getEmplois().size(); i++) {
						id_list.add(existedEntr.getEmplois().get(i));
					}
						if (id_list.contains(existedEmp)) {
							return new ResponseEntity<>("Employment already exists", HttpStatus.NOT_ACCEPTABLE);
						} else {
							existedEntr.getEmplois().add(existedEmp);
							existedEntr.setEmplois(existedEntr.getEmplois());
						}
				}
				entr_rep.save(existedEntr);
			} else {
				return new ResponseEntity<>("Entreprise not found!", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>("Entreprise Updated Successfully", HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	public ResponseEntity<?> rmvEmpFromEntreprise(String id_entr, String id_emp) {
		try {
			if (entr_rep.findById(id_entr).isPresent() && emp_rep.findById(id_emp).isPresent()) {
				Entreprise existedEntr = entr_rep.findById(id_entr).get();
				ArrayList<Object> id_list = new ArrayList<Object>();
				for (int i = 0; i<existedEntr.getEmplois().size(); i++) {
					id_list.add(existedEntr.getEmplois().get(i));
				}
				if (id_list.contains(id_emp)) {
					existedEntr.getEmplois().remove(id_list.indexOf(id_emp));
					existedEntr.setEmplois(existedEntr.getEmplois());
				} else {
					return new ResponseEntity<>("Cannot delete employment from entreprise", HttpStatus.NOT_ACCEPTABLE);
				}
				entr_rep.save(existedEntr);
			} else {
				return new ResponseEntity<>("Entreprise not found!", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>("Entreprise Updated Successfully", HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
