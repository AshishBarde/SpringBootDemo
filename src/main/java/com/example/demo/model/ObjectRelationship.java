package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hovs.benefit.jpa.model.BeneficiaryMapping;
import com.hovs.benefit.jpa.model.Coverage;
import com.hovs.benefit.jpa.model.EmployeeEnrollMappingTrn;
import com.hovs.benefit.jpa.model.EmployeeEnrollmentTrn;
import com.hovs.benefit.jpa.model.HealthQuestionare;

@Audited
@Entity
@Table(name = "", schema = "")
public class ObjectRelationship {

	//parent class
	@NotAudited
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="employeeEnrollMappingTrn", cascade=CascadeType.ALL,orphanRemoval=true)
	private List<BeneficiaryMapping> beneficiaryMapping;
	
	@NotAudited
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="employeeEnrollMappingTrn", cascade=CascadeType.ALL,orphanRemoval=true)
	private List<HealthQuestionare> healthQuestionare;
	
	//child class

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="EMPLOYEEENROLLMAPID")
	private EmployeeEnrollMappingTrn employeeEnrollMappingTrn;
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="EMPLOYEEENROLLID")
	private EmployeeEnrollmentTrn employeeEnrollmentTrn;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="employeeEnrollmentTrn",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<EmployeeEnrollMappingTrn> employeeEnrollMappingTrn;
	
	@ManyToOne
	@JoinColumn(name="COVERAGEID")
	private Coverage coverage;
	
}
