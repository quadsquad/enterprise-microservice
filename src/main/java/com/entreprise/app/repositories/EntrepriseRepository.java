package com.entreprise.app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.entreprise.app.entities.Entreprise;

@Repository
public interface EntrepriseRepository extends MongoRepository<Entreprise, String> {

}
