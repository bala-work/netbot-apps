package com.netbot.form.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netbot.form.domain.Domain;

@Configuration
public class BatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	public Reader reader;
	
	@Autowired
	public Writer writer;

	@Bean
	public Job processJob() {
		System.out.println(" ------------ processJob");
		return jobBuilderFactory.get("processJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener())
				.flow(orderStep1()).end().build();
	}

	@Bean
	public Step orderStep1() {
		System.out.println(" ------------ orderStep1");
		return stepBuilderFactory.get("orderStep1").<Domain, Domain>chunk(1)
				.reader(reader)
				.processor(new Processor()).writer(writer).build();
	}

	@Bean
	public JobExecutionListener listener() {
		System.out.println(" ------------ listener");
		return new JobCompletionListener();
	}

}