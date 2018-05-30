package com.netbot.form.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.netbot.form.domain.Domain;

public interface DomainRepository extends CrudRepository<Domain, String> {
	
    @Override
    Optional<Domain> findById(String id);

    @Query(value = "{ Stage : ?0, ContactLink : true}", count = true)
    List<Domain> domainsByStage(String stage); 
    
    
}
