package com.pucese.ecommerce.dto.company;

public class CompanySignUpDTO {
	
	 	private String email;
	    private String comercial_name;
	    private String password;
	    private String ruc;
	    private String ciiu;
	    private String representante;
	    private String provider;
	    private String general_activity;
	    private String provincia;
	    private String canton;
	    private String parroquia;
	    private String telephone;
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getComercial_name() {
			return comercial_name;
		}
		public void setComercial_name(String comercial_name) {
			this.comercial_name = comercial_name;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getRuc() {
			return ruc;
		}
		public void setRuc(String ruc) {
			this.ruc = ruc;
		}
		public String getCiiu() {
			return ciiu;
		}
		public void setCiiu(String ciiu) {
			this.ciiu = ciiu;
		}
		public String getRepresentante() {
			return representante;
		}
		public void setRepresentante(String representante) {
			this.representante = representante;
		}
		public String getProvider() {
			return provider;
		}
		public void setProvider(String provider) {
			this.provider = provider;
		}
		public String getGeneral_activity() {
			return general_activity;
		}
		public void setGeneral_activity(String general_activity) {
			this.general_activity = general_activity;
		}
		public String getProvincia() {
			return provincia;
		}
		public void setProvincia(String provincia) {
			this.provincia = provincia;
		}
		public String getCanton() {
			return canton;
		}
		public void setCanton(String canton) {
			this.canton = canton;
		}
		public String getParroquia() {
			return parroquia;
		}
		public void setParroquia(String parroquia) {
			this.parroquia = parroquia;
		}
		public String getTelephone() {
			return telephone;
		}
		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}
		public CompanySignUpDTO(String email, String comercial_name, String password, String ruc, String ciiu,
				String representante, String provider, String general_activity, String provincia, String canton,
				String parroquia, String telephone) {
			super();
			this.email = email;
			this.comercial_name = comercial_name;
			this.password = password;
			this.ruc = ruc;
			this.ciiu = ciiu;
			this.representante = representante;
			this.provider = provider;
			this.general_activity = general_activity;
			this.provincia = provincia;
			this.canton = canton;
			this.parroquia = parroquia;
			this.telephone = telephone;
		}
		public CompanySignUpDTO() {

		}
	    
	    

}
