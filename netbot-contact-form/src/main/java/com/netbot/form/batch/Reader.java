package com.netbot.form.batch;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netbot.form.domain.Domain;
import com.netbot.form.repository.DomainRepository;

@Component
public class Reader implements ItemReader<Domain> {

	private List<Domain> domains = null;
	private Boolean dbFetch = false;
	
	private int count = 0;
	
	@Autowired
	DomainRepository domainRepository;

	@Override
	public Domain read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		System.out.println(" ------------ Reader.read");
		
		if(!dbFetch)
			domains = domainRepository.domainsByStage("Stage2");
		
		if(domains == null || domains.size() == 0) {
			return null;
		}
		
		if (count < domains.size()) {
			return domains.get(count++);
		} else {
			count = 0;
		}
		
		return null;
	}

}