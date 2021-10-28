package com.entreprise.app.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.entreprise.app.entities.Entreprise;
import com.entreprise.app.services.EntrepriseService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EntrepriseController {
	
	@Autowired
	public EntrepriseService entr_service;
	
	@Autowired
	private RestTemplateBuilder restTemplateBuilder;
	
	@GetMapping("/entreprises")
	public ResponseEntity<?> getAllEntreprises() {
		return entr_service.getAllEntreprises();
	}
	
	@PostMapping("/add-entreprise")
	public void addEntreprise(@RequestBody Entreprise entreprise) {
		entr_service.addEntreprise(entreprise);
	}
	
	@PutMapping("/update-entreprise/{id_entr}")
	public void updateEntreprise(@RequestBody Entreprise entreprise, @PathVariable String id_entr) {
		entr_service.updateEntreprise(entreprise, id_entr);
	}
	
	@DeleteMapping("/delete-entreprise/{id_entr}")
	public void deleteEntreprise(@PathVariable String id_entr) {
		entr_service.deleteEntreprise(id_entr);
	}
	
	@PutMapping("/addEmpToEntreprise/{id_entr}/{id_emp}")
	public void addEmpToEntreprise(@PathVariable String id_entr, @PathVariable String id_emp) {
		entr_service.addEmpToEntreprise(id_entr, id_emp);
	}
	
	@PutMapping("/delEmpFromEntreprise/{id_emp}/{id_emp}")
	public void rmvEmpFromEntreprise(@PathVariable String id_entr, @PathVariable String id_emp) {
		entr_service.rmvEmpFromEntreprise(id_entr, id_emp);
	}
	
	//Used for communication between two microservices
	@GetMapping("/centres-from-extern")
	public List<Object> getCentres() {
		Object[] centres = restTemplateBuilder.build().getForObject("https://zuul-server-qs.herokuapp.com/formation/all-centres", Object[].class);
		return Arrays.asList(centres);
	}
	
	@GetMapping("/get-centre/{id_centre}")
	public ResponseEntity<?> getOneCentre(@PathVariable String id_centre) {
		Object centre = restTemplateBuilder.build().getForObject("https://zuul-server-qs.herokuapp.com/formation/get-centre/"+id_centre, Object.class);
		return new ResponseEntity<>(centre, HttpStatus.OK);
	}
	
	public static String getBearerTokenHeader() {
	    return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
	  }
	
	@GetMapping(value = "/getuserconnect")
	public ResponseEntity<?> getConnectedUser() {
		
		//Object response = rest.getForObject("http://authrecruit/getuserconnected", Object.class);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", getBearerTokenHeader());
	    HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	    ResponseEntity<String> response = restTemplateBuilder.build().exchange("http://localhost:8084/getuserconnected", HttpMethod.GET, entity, String.class);

		return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
	}

}
