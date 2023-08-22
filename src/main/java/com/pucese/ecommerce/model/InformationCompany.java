package com.pucese.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "InformationCompany", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ruc")
})
public class InformationCompany {
	
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	
	
	 @Column(name = "comercialName")
	 private String comercialName;
	
	 @Column(name = "telephone")
	 private String telephone;
	 
	 @Column(name = "ciiu")
	 private String ciiu;
	 
	 @Column(name = "representante")
	 private String representante;
	 
	 @Column(name = "ruc")
	 private String ruc;
	 
	 @Column(name = "generalActivity")
	 private String generalActivity;
	 
	 @Column(name = "provincia")
	 private String provincia;
	 
	 @Column(name = "canton")
	 private String canton;
	 
	 @Column(name = "parroquia")
	 private String parroquia;

	public InformationCompany(Long id, String comercialName, String telephone, String ciiu, String representante,
			String ruc, String generalActivity, String provincia, String canton, String parroquia) {
		super();
		this.id = id;
		this.comercialName = comercialName;
		this.telephone = telephone;
		this.ciiu = ciiu;
		this.representante = representante;
		this.ruc = ruc;
		this.generalActivity = generalActivity;
		this.provincia = provincia;
		this.canton = canton;
		this.parroquia = parroquia;
	}

	public InformationCompany() {
		super();
	}
	 
	@OneToOne
	@JoinColumn(name="company_id")
	CompanyUser companyUser;

}
