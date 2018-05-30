package com.netbot.form.batch;

import org.springframework.batch.item.ItemProcessor;

import com.netbot.form.domain.Domain;
import com.netbot.form.util.CheckContactForm;

public class Processor implements ItemProcessor<Domain, Domain> {

	@Override
	public Domain process(Domain domain) throws Exception {
		System.out.println(" ------------ Processor.Process");
		domain = new CheckContactForm().updateFormStatus(domain);

		if (domain.getContactLink()) {
			domain.setFormSubmitted(false);
			domain.setStage("Stage3");
		} else if (!domain.getContactForm()) {
			domain.setMessage("No Contact Form Found");
		} else if (domain.getCaptcha()) {
			domain.setMessage("Captcha Found in Contact Form");
		}

		return domain;
	}

}