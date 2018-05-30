package com.netbot.form.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "domains")
public class Domain {
	@Id
	String id;

	@Field("Url")
	String url;

	@Field("Protocol")
	String protocol;
	
	@Field("ParentDomain")
	String parentDomain;
	
	@Field("Country")
	String country;
	
	@Field("Language")
	String language;
	
	@Field("Active")
	Boolean active;
	
	@Field("ContactPage")
	String contactPage;

	@Field("ContactLink")
	Boolean contactLink;
	
	@Field("ContactForm")
	Boolean contactForm;

	@Field("Captcha")
	Boolean captcha;
	
	@Field("FormSubmitted")
	Boolean formSubmitted;
	
	@Field("Stage")
	String stage;

	@Field("Message")
	String message;
	
    @Field("CreatedOn")
    String createdOn;
    
    @Field("UpdatedOn")
    String updatedOn;
    
    @Field("Machine")
    String machine;

	public Domain() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getContactPage() {
		return contactPage;
	}

	public void setContactPage(String contactPage) {
		this.contactPage = contactPage;
	}

	public Boolean getContactLink() {
		return contactLink;
	}

	public void setContactLink(Boolean contactLink) {
		this.contactLink = contactLink;
	}

	public Boolean getContactForm() {
		return contactForm;
	}

	public void setContactForm(Boolean contactForm) {
		this.contactForm = contactForm;
	}

	public Boolean getCaptcha() {
		return captcha;
	}

	public void setCaptcha(Boolean captcha) {
		this.captcha = captcha;
	}

	public Boolean getFormSubmitted() {
		return formSubmitted;
	}

	public void setFormSubmitted(Boolean formSubmitted) {
		this.formSubmitted = formSubmitted;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getParentDomain() {
		return parentDomain;
	}

	public void setParentDomain(String parentDomain) {
		this.parentDomain = parentDomain;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getMachine() {
		return machine;
	}

	public void setMachine(String machine) {
		this.machine = machine;
	}

}
