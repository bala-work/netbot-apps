package com.netbot.form.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netbot.form.domain.Domain;
import com.netbot.form.repository.DomainRepository;

@Component
public class Writer implements ItemWriter<Domain> {

	@Autowired
	DomainRepository domainRepository;
	
	@Override
	public void write(List<? extends Domain> domains) throws Exception {
		System.out.println(" ------------ Writer.write");
		for (Domain domain : domains) {
			domainRepository.save(domain);
		}
	}

}